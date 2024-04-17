package com.example.myapp.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wg_table")
public class Wohngemeinschaft {
    @PrimaryKey(autoGenerate = true)
    public int wg_id;
    public String wg_name;

    public Wohngemeinschaft(){};

    public Wohngemeinschaft(String wgName){
        this.wg_name =wgName;
    };

    public String getName(){
        return this.wg_name;
    }
}
