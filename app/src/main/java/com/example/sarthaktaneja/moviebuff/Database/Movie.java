package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by sarthaktaneja on 30/04/18.
 */

public class Movie {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "movie_name")
    private String movieName;

    @ColumnInfo(name = "rating")
    private double rating;

}

