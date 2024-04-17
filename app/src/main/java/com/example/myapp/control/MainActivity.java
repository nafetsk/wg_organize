package com.example.myapp.control;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.model.RoleManager;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Mitbewohni;
import com.example.myapp.model.database.Wohngemeinschaft;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // WG Spinner
        ArrayList<String> wgNames = getWgNames();
        Spinner wgSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, wgNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wgSpinner.setAdapter(adapter);

        // Update Mitbewohni Spinner based on WG Decision
        wgSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedWg = wgSpinner.getSelectedItem().toString();
                updateMitbewohniSpinner(selectedWg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void updateMitbewohniSpinner(String selectedWg) {
        ArrayList<String> mitbewohniNames = getMitbewohniNames(selectedWg);
        Spinner mitbewohniSpinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mitbewohniNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mitbewohniSpinner.setAdapter(adapter);
    }

    public void addWg(View v){
        Intent i = new Intent(this, AddWg.class);
        startActivity(i);
    }

    public void selectWg(View v){
        // save WG name
        Spinner wgSpinner = findViewById(R.id.spinner);
        Spinner mitbewohniSpinner = findViewById(R.id.spinner2);
        String wg = wgSpinner.getSelectedItem().toString();
        String mitbewohni;
        if(mitbewohniSpinner.getSelectedItem() == null){
            mitbewohni = "Notfall Name";
        }else{
            mitbewohni = mitbewohniSpinner.getSelectedItem().toString();
        }

        RoleManager.saveRole(this, wg, mitbewohni);

        // launch Homescreen
        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }


    public ArrayList<String> getWgNames(){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        List<Wohngemeinschaft> wgs = db.wohngemeinschaftDao().getAll();
        ArrayList<String> wgNames = (ArrayList<String>) wgs.stream().map(Wohngemeinschaft::getName).collect(Collectors.toList());
        return wgNames;
    }
    public ArrayList<String> getMitbewohniNames(String wgName) {
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        List<Mitbewohni> mitbewohnis = db.mitbewohniDao().getMitbewohniByWgName(wgName);
        // Streams sind eine einfach Möglichkeit die Liste von Objekten durch eine Liste ihrer Namen zu ersetzen
        ArrayList<String> mitbewohniNames = (ArrayList<String>) mitbewohnis.stream().map(Mitbewohni::getName).collect(Collectors.toList());
        return mitbewohniNames;
    }

}