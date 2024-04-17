package com.example.myapp.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mitbewohni.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MitbewohniDao mitbewohniDao();
}
