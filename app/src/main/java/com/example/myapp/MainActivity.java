package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DataBaseHelper db = new DataBaseHelper(MainActivity.this);
    //ArrayList<String> wgNames = db.getWgNames();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        String[] arraySpinner = new String[] {
                "WG 1", "WG 2", "WG 3", "WG 4", "WG 5"
        };
        */

        // WG Spinner
        ArrayList<String> wgNames = getWgNames();
        Spinner wgSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, wgNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wgSpinner.setAdapter(adapter);


        // Mitbewohni Spinner
        ArrayList<String> mitbewohniNames = getMitbewohniNames();
        Spinner mitbewohniSpinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mitbewohniNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mitbewohniSpinner.setAdapter(adapter2);


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
        String mitbewohni = mitbewohniSpinner.getSelectedItem().toString();
        RoleManager.saveRole(this, wg, mitbewohni);

        // launch Homescreen
        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }


    public ArrayList<String> getWgNames(){
        Cursor cursor = db.readAllData();
        ArrayList<String> wgNames = new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                wgNames.add(cursor.getString(1));
            }
        }
        return wgNames;
        }
    public ArrayList<String> getMitbewohniNames() {
        Cursor cursor = db.readAllDataFromSecondTable();
        ArrayList<String> mitbewohniNames = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                mitbewohniNames.add(cursor.getString(1));
            }
        }
        return mitbewohniNames;
    }

/*
    public void handleText(View v){
        TextView inputField = findViewById(R.id.source);
        TextView outputField = findViewById(R.id.textView2);
        String input = inputField.getText().toString();
        Log.d("info", input);
        String prevOutput = outputField.getText().toString();
        String output = prevOutput + input;
        outputField.setText(output);
    }
*/
}