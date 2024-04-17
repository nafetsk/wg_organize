package com.example.myapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;
@Dao
public interface AufgabenDao {
    @Query("SELECT * FROM aufgaben_table")
    List<Aufgaben> getAll();
    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);
    */
    @Query("SELECT * FROM aufgaben_table WHERE wg_name = :wgName")
    List<Aufgaben> getAufgabeByWgName(String wgName);

    @Query("UPDATE aufgaben_table SET date_last_cleaned = :newDate WHERE a_id = :a_id")
    void updateDateLastCleaned(int a_id, Date newDate);

    @Insert
    void insertAll(Aufgaben... aufgaben);

    @Delete
    void delete(Aufgaben aufgabe);
}
