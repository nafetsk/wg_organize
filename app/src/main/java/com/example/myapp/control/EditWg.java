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
import com.example.myapp.model.sync.PostCallBack;
import com.example.myapp.model.sync.SyncService;

import java.util.Date;


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
        Mitbewohni new_mitbewohni = new Mitbewohni(mitbewohniName, wgRole, 1);
        db.mitbewohniDao().insertAll(new_mitbewohni);

        // Request to server db
        SyncService.createMitbewohni(new_mitbewohni, new PostCallBack());

        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }
    public void addAufgabe(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        String aufgabeName = aufgabeNameInput.getText().toString().trim();
        String wgRole = RoleManager.getWGName(this);
        Aufgaben new_aufgabe = new Aufgaben(aufgabeName, wgRole, new Date());
        db.aufgabenDao().insertAll(new_aufgabe);

        // Request to server db
        SyncService.createAufgabe(new_aufgabe, new PostCallBack());


        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }

}