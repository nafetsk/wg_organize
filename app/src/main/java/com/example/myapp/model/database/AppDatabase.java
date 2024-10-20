package com.example.myapp.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(DateConverter.class)
@Database(entities = {Mitbewohni.class, Wohngemeinschaft.class, Aufgaben.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MitbewohniDao mitbewohniDao();
    public abstract WohngemeinschaftDao wohngemeinschaftDao();
    public abstract AufgabenDao aufgabenDao();
}
