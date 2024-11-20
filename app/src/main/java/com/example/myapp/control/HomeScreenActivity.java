package com.example.myapp.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.model.sync.PostCallBack;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import com.example.myapp.MitbewohnisRecyclerViewAdapter;
import com.example.myapp.R;
import com.example.myapp.AufgabenRecyclerViewAdapter;
import com.example.myapp.model.RoleManager;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Aufgaben;
import com.example.myapp.model.database.Mitbewohni;
import com.example.myapp.model.sync.SyncService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeScreenActivity extends AppCompatActivity implements DeleteAufgabeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get Role
        TextView wgName = findViewById(R.id.wgname);
        TextView mitbewohniName = findViewById(R.id.roleMitbewohni);
        String wgRole = RoleManager.getWGName(this);
        String mitbewohniRole = RoleManager.getMitbewohnerName(this);
        wgName.setText(wgRole);
        mitbewohniName.setText(mitbewohniRole);


        // Mitbewohni Recycler VieW
        RecyclerView rvMitbewohnis = findViewById(R.id.rv_mitbewohnis);
        ArrayList<Mitbewohni> mitbewohnis = setUpMitbewohnis();
        MitbewohnisRecyclerViewAdapter rvAdapter = new MitbewohnisRecyclerViewAdapter(this, mitbewohnis);
        rvMitbewohnis.setAdapter(rvAdapter);
        rvMitbewohnis.setLayoutManager(new LinearLayoutManager(this));

        // DirtyMeter Recycler View
        RecyclerView rvDirtyMeter = findViewById(R.id.rv_dirtymeter);
        ArrayList<Aufgaben> aufgaben = getAufgaben(RoleManager.getWGName(this));
        AufgabenRecyclerViewAdapter rvAdapterDirtyMeter = new AufgabenRecyclerViewAdapter(this, aufgaben,this);
        rvDirtyMeter.setAdapter(rvAdapterDirtyMeter);
        rvDirtyMeter.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<Mitbewohni> setUpMitbewohnis(){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        return (ArrayList<Mitbewohni>) db.mitbewohniDao().getMitbewohniByWgName(RoleManager.getWGName(this));
    }
    private ArrayList<Aufgaben> getAufgaben(String wgName){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        return (ArrayList<Aufgaben>) db.aufgabenDao().getAufgabeByWgName(wgName);
    }
    public void editWG(View v){
        Intent i = new Intent(this, EditWg.class);
        startActivity(i);
    }

    public void cleanNow(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        Date currentDate = new Date();
        db.aufgabenDao().updateDateLastCleaned(currentDate);
        Aufgaben updated_aufgabe = db.aufgabenDao().getUpdatedAufgabe(currentDate);
        String a_id = updated_aufgabe.getA_id();
        db.mitbewohniDao().decrementRing(RoleManager.getWGName(this), RoleManager.getMitbewohnerName(this));

        System.out.println(a_id);
        // syncing
        SyncService.cleanNow(RoleManager.getMitbewohnerName(this), currentDate,a_id  , new PostCallBack());

        Intent i = getIntent();
        startActivity(i);
    }

    public void incrementRings(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        db.mitbewohniDao().incrementRings(RoleManager.getWGName(this));

        SyncService.incrementRings(RoleManager.getWGName(this), new PostCallBack() {
        });

        Intent i = getIntent();
        startActivity(i);
    }

    public void sync_db(View v){
        String wgRole = RoleManager.getWGName(this);
        SyncService.syncMitbewohni(wgRole, new SyncService.Callback() {
            @Override
            public void onSuccess(String result) {
                AppDatabase db = AppDatabaseFactory.getInstance(getApplicationContext()).getDatabase();

                // Assuming you have a class Mitbewohni corresponding to your data model
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Mitbewohni>>(){}.getType();
                List<Mitbewohni> mitbewohniList = gson.fromJson(result, listType);

                // Replace local DB entries with server data
                db.runInTransaction(() -> {
                    db.mitbewohniDao().clearByWgName(RoleManager.getWGName(getApplicationContext())); // Clear old data
                    db.mitbewohniDao().insertAll(mitbewohniList.toArray(new Mitbewohni[0])); // Insert new data
                });

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        SyncService.syncAufgaben(wgRole, new SyncService.Callback() {
            @Override
            public void onSuccess(String result) {
                AppDatabase db = AppDatabaseFactory.getInstance(getApplicationContext()).getDatabase();

                // Assuming you have a class Mitbewohni corresponding to your data model
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Aufgaben>>(){}.getType();
                List<Aufgaben> aufgabenList = gson.fromJson(result, listType);

                // Replace local DB entries with server data
                db.runInTransaction(() -> {
                    db.aufgabenDao().clearByWgName(RoleManager.getWGName(getApplicationContext())); // Clear old data
                    db.aufgabenDao().insertAll(aufgabenList.toArray(new Aufgaben[0])); // Insert new data
                });

                // Refresh the activity after data sync
                Intent i = getIntent();
                finish();
                startActivity(i);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();

            }
        });

    }


    @Override
    public void onButtonClicked(Aufgaben aufgabe) {
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        db.aufgabenDao().delete(aufgabe);

        //sync
        SyncService.deleteAufgabe(aufgabe.getA_id(), new PostCallBack());

        Intent i = getIntent();
        startActivity(i);
    }
}