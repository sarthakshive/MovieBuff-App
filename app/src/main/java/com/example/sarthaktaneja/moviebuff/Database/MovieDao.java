package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sarthaktaneja on 30/04/18.
 */

public interface MovieDao {
    @Query("SELECT * FROM user")
    List<Movie> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<Movie> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);
}
