package com.example.myapp.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
@TypeConverters(DateConverter.class)
@Entity(tableName = "aufgaben_table")
public class Aufgaben {
    @PrimaryKey(autoGenerate = true)
    public int a_id;
    public String name;
    public String wg_name;
    public Date date_last_cleaned;

    public Aufgaben(){};

    public Aufgaben(String name, String wgName){
        this.name = name;
        this.wg_name = wgName;
    }
    public Aufgaben(String name, String wgName, Date date_last_cleaned){
        this.name = name;
        this.wg_name = wgName;
        this.date_last_cleaned = date_last_cleaned;
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

    public int getA_id() {
        return a_id;
    }
}
