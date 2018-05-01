package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sarthaktaneja.moviebuff.Database.Movie;

import java.util.List;

/**
 * Created by sarthaktaneja on 30/04/18.
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();


    @Insert
    void insertAll(Movie movies);
}
