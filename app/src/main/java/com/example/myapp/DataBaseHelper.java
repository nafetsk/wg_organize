package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context contex;
    private final String DATABASE_NAME = "wg_organize.db";
    private final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "MAIN_TABLE";

    private final String COLUMN_ID ="_id";
    private final String COLUMN_WGNAME ="wg_name";
    private final String COLUMN_PSWRD ="pswrd";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "wg_organize.db", null, 1);
        this.contex = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WGNAME + " TEXT, " +
                COLUMN_PSWRD + " TEXT); ";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addWG(String wgName, String paswrd){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WGNAME, wgName);
        cv.put(COLUMN_PSWRD, paswrd);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1){
            Toast.makeText(contex, "Failed to add WG!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(contex, "WG was added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllData(){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if  (db != null){
            cursor = db.rawQuery(sqlQuery, null);
        }
        return cursor;
    }
}
