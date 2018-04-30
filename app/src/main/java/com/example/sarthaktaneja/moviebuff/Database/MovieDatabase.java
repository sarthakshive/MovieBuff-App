package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by sarthaktaneja on 30/04/18.
 */

    @Database(entities = {Movie.class}, version = 1)
    public abstract class MovieDatabase extends RoomDatabase {
        public abstract MovieDao movieDao();
    }
