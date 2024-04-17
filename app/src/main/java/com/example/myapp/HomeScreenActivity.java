package com.example.myapp;

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

import com.example.myapp.model.RoleManager;
import com.example.myapp.model.RoomModel;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Mitbewohni;

import java.util.ArrayList;
import java.util.List;


public class HomeScreenActivity extends AppCompatActivity {
    ArrayList<RoomModel> rooms = new ArrayList<>();
    DataBaseHelper db = new DataBaseHelper(this);
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
        setUpRooms();
        RoomRecyclerViewAdapter rvAdapterDirtyMeter = new RoomRecyclerViewAdapter(this, rooms);
        rvDirtyMeter.setAdapter(rvAdapterDirtyMeter);
        rvDirtyMeter.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<Mitbewohni> setUpMitbewohnis(){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        return (ArrayList<Mitbewohni>) db.mitbewohniDao().getMitbewohniByWgName(RoleManager.getWGName(this));
    }
    private void setUpRooms(){
        String[] roomnames = {"Küche", "Wohnzimmer", "Badezimmer", "HWR", "Flur"};
        String[] dates = {"23.04", "11.03", "17.06", "19.9", "11.12"};
        for (int i = 0; i < roomnames.length; i++){
            rooms.add(new RoomModel(roomnames[i], dates[i]));
        }
    }
    public void editWG(View v){
        Intent i = new Intent(this, EditWg.class);
        startActivity(i);
    }
}