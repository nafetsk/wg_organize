package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class EditWg extends AppCompatActivity {

    EditText mitbewohniNameInput;

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
    }
    public void addMitbewohni(View v){
        DataBaseHelper db = new DataBaseHelper(EditWg.this);
        db.addMitbewohni(mitbewohniNameInput.getText().toString().trim());
        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }
}