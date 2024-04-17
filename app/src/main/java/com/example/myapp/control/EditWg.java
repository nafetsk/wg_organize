package com.example.myapp.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.R;
import com.example.myapp.model.RoleManager;
import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Aufgaben;
import com.example.myapp.model.database.Mitbewohni;


public class EditWg extends AppCompatActivity {

    EditText mitbewohniNameInput, aufgabeNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_wg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mitbewohniNameInput = findViewById(R.id.editTextMitbewohniName);
        aufgabeNameInput = findViewById(R.id.editTextAufgabeInput);
    }
    public void addMitbewohni(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        String mitbewohniName = mitbewohniNameInput.getText().toString().trim();
        String wgRole = RoleManager.getWGName(this);
        db.mitbewohniDao().insertAll(new Mitbewohni(mitbewohniName, wgRole, 1));

        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }
    public void addAufgabe(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        String aufgabeName = aufgabeNameInput.getText().toString().trim();
        String wgRole = RoleManager.getWGName(this);
        db.aufgabenDao().insertAll(new Aufgaben(aufgabeName, wgRole, null));
        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }

}