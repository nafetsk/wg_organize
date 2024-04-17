package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.model.database.AppDatabase;
import com.example.myapp.model.database.AppDatabaseFactory;
import com.example.myapp.model.database.Wohngemeinschaft;
import com.example.myapp.model.database.WohngemeinschaftDao;

public class AddWg extends AppCompatActivity {
    EditText wgnameInput, pswrdInput;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_wg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        wgnameInput = findViewById(R.id.editTextWGName);
        pswrdInput = findViewById(R.id.editTextWgPswrd);

    }
    public void addWg(View v){
        AppDatabase db = AppDatabaseFactory.getInstance(this).getDatabase();
        //DataBaseHelper db = new DataBaseHelper(AddWg.this);
        db.wohngemeinschaftDao().insertAll(new Wohngemeinschaft(wgnameInput.getText().toString().trim()));
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}