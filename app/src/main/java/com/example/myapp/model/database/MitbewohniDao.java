package com.example.myapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MitbewohniDao {
    @Query("SELECT * FROM mitbewohni_table")
    List<Mitbewohni> getAll();
    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);
    */
    @Query("SELECT * FROM mitbewohni_table WHERE wg_name = :wgName")
    List<Mitbewohni> getMitbewohniByWgName(String wgName);

    @Query("UPDATE mitbewohni_table SET number_of_rings = number_of_rings +1 WHERE wg_name = :wgName")
    void incrementRings(String wgName);

    @Query("UPDATE mitbewohni_table SET number_of_rings = number_of_rings -1 WHERE wg_name = :wgName AND name = :name")
    void decrementRing(String wgName, String name);
    @Insert
    void insertAll(Mitbewohni... mitbewohnis);

    @Delete
    void delete(Mitbewohni mitbewohni);

    // Clear all mitbewohnis of a specific WG by wg_name
    @Query("DELETE FROM mitbewohni_table WHERE wg_name = :wgName")
    void clearByWgName(String wgName);

    @Query("DELETE FROM mitbewohni_table")
    void deleteAll();
}
