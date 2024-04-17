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

import com.example.myapp.MitbewohnisRecyclerViewAdapter;
import com.example.myapp.R;
import com.example.myapp.AufgabenRecyclerViewAdapter;
import com.example.myapp.model.RoleManager;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Aufgaben;
import com.example.myapp.model.database.Mitbewohni;

import java.util.ArrayList;
import java.util.Date;


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
        ArrayList<Aufgaben> aufgaben = getAufgaben();
        AufgabenRecyclerViewAdapter rvAdapterDirtyMeter = new AufgabenRecyclerViewAdapter(this, aufgaben,this);
        rvDirtyMeter.setAdapter(rvAdapterDirtyMeter);
        rvDirtyMeter.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<Mitbewohni> setUpMitbewohnis(){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        return (ArrayList<Mitbewohni>) db.mitbewohniDao().getMitbewohniByWgName(RoleManager.getWGName(this));
    }
    private ArrayList<Aufgaben> getAufgaben(){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        return (ArrayList<Aufgaben>) db.aufgabenDao().getAufgabeByWgName(RoleManager.getWGName(this));
    }
    public void editWG(View v){
        Intent i = new Intent(this, EditWg.class);
        startActivity(i);
    }

    public void cleanNow(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        Date currentDate = new Date();
        db.aufgabenDao().updateDateLastCleaned(1,currentDate);
        db.mitbewohniDao().decrementRing(RoleManager.getWGName(this), RoleManager.getMitbewohnerName(this));
        Intent i = getIntent();
        startActivity(i);
    }

    public void incrementRings(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        db.mitbewohniDao().incrementRings(RoleManager.getWGName(this));
        Intent i = getIntent();
        startActivity(i);
    }


    @Override
    public void onButtonClicked(Aufgaben aufgabe) {
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        db.aufgabenDao().delete(aufgabe);
        Intent i = getIntent();
        startActivity(i);
    }
}