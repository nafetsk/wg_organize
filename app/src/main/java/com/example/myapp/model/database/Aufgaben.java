package com.example.myapp.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.UUID;

@TypeConverters(DateConverter.class)
@Entity(tableName = "aufgaben_table")
public class Aufgaben {
    @PrimaryKey @NonNull
    public String a_id;
    public String name;
    public String wg_name;
    public Date date_last_cleaned;

    public Aufgaben(){
        this.a_id = UUID.randomUUID().toString();
    }
    public Aufgaben(String name, String wgName){
        this.name = name;
        this.wg_name = wgName;
        this.a_id = UUID.randomUUID().toString();
    }
    public Aufgaben(String name, String wgName, Date date_last_cleaned){
        this.name = name;
        this.wg_name = wgName;
        this.date_last_cleaned = date_last_cleaned;
        this.a_id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getWg_name() {
        return wg_name;
    }
    public Date getDate_last_cleaned(){
        return this.date_last_cleaned;
    }

    public String getA_id() {
        return a_id;
    }
}
