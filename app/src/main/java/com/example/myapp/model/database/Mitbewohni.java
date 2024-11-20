package com.example.myapp.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.UUID;

@Entity(tableName = "mitbewohni_table")
public class Mitbewohni {
    @PrimaryKey @NonNull
    public String m_id;

    public String name;
    public String wg_name;
    public int number_of_rings;

    // No-args constructor for Room
    public Mitbewohni() {
        this.m_id = UUID.randomUUID().toString();  // Default UUID generation
    }
    public Mitbewohni(String name, String wg_name, int rings){
        this.name = name;
        this.wg_name = wg_name;
        this.number_of_rings = rings;
        this.m_id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getWg_name(){
        return wg_name;
    }

    public int getNumber_of_rings() {
        return number_of_rings;
    }

    public String getM_id(){
        return m_id;
    }

    public void setName(String mitbewohniName) {
        this.name = mitbewohniName;
    }

    public void setWgName(String s) {
        this.wg_name = s;
    }
}
