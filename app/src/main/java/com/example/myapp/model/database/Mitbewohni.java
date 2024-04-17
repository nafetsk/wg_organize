package com.example.myapp.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mitbewohni_table")
public class Mitbewohni {
    @PrimaryKey(autoGenerate = true)
    public int m_id;

    public String name;
    public String wg_name;
    public int number_of_rings;


    public Mitbewohni(){};

    public Mitbewohni(String name, String wg_name, int rings){
        this.name = name;
        this.wg_name = wg_name;
        this.number_of_rings = rings;
    }

    public String getName() {
        return name;
    }
}
