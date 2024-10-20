package com.example.myapp.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "wg_table")
public class Wohngemeinschaft {
    @PrimaryKey @NonNull
    public String wg_id;
    public String wg_name;

    public Wohngemeinschaft(){
        this.wg_id = UUID.randomUUID().toString();
    }
    public Wohngemeinschaft(String wgName){
        this.wg_name =wgName;
        this.wg_id = UUID.randomUUID().toString();
    };

    public String getName(){
        return this.wg_name;
    }

    public String getWg_id() { return this.wg_id;}
}
