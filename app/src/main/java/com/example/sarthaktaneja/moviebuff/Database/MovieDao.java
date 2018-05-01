package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sarthaktaneja.moviebuff.Model.Pojo1;

import java.util.List;

/**
 * Created by sarthaktaneja on 30/04/18.
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM Pojo1")
    List<Pojo1> getAll();



    @Insert
    void insertAll(Pojo1 movies);

    @Delete
    void delete(Pojo1 pojo1);
}
