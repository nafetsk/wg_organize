package com.example.myapp.model.database;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseFactory {
    private static AppDatabaseFactory instance;
    private static final Object LOCK = new Object();
    private final Context context;
    private final AppDatabase db;

    private AppDatabaseFactory(Context context){
        this.context = context.getApplicationContext();
        db = Room.databaseBuilder(this.context, AppDatabase.class, "my_database_name").allowMainThreadQueries().build();
    }
    public static AppDatabaseFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new AppDatabaseFactory(context);
                }
            }
        }
        return instance;
    }

    public AppDatabase getDatabase() {
        return db;
    }

}
