package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeScreenActivity extends AppCompatActivity {
    ArrayList<MitbewohniModel> mitbewohnis = new ArrayList<>();
    ArrayList<RoomModel> rooms = new ArrayList<>();
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

        // get Intent
        Intent i = getIntent();
        String name = i.getStringExtra("wg");

        TextView wgName = findViewById(R.id.wgname);
        wgName.setText(name);


        // Mitbewohni Recycler VieW
        RecyclerView rvMitbewohnis = findViewById(R.id.rv_mitbewohnis);
        setUpMitbewohnis();
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

    private void setUpMitbewohnis(){
        String[] mitbewohniNames = {"Stefan", "Sunny", "Marcel", "Theresa", "Otto", "Jana", "Niko", "Freyja"};
        for (int i = 0; i < mitbewohniNames.length; i++){
            mitbewohnis.add(new MitbewohniModel(mitbewohniNames[i], 1));
        }
    }
    private void setUpRooms(){
        String[] roomnames = {"Küche", "Wohnzimmer", "Badezimmer", "HWR", "Flur"};
        String[] dates = {"23.04", "11.03", "17.06", "19.9", "11.12"};
        for (int i = 0; i < roomnames.length; i++){
            rooms.add(new RoomModel(roomnames[i], dates[i]));
        }
    }
}