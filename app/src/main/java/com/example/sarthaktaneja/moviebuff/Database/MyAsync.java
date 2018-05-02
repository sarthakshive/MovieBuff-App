package com.example.sarthaktaneja.moviebuff.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sarthaktaneja.moviebuff.Database.Movie;

/**
 * Created by sarthaktaneja on 30/04/18.
 */

public class MyAsync extends AsyncTask<Object,Void,Boolean> {

    private Context context;

    public MyAsync(Context context)
    {
        this.context=context;
    }

    public boolean addMovie(MovieDatabase mdb, Movie movie)
    {
        mdb.movieDao().insertAll(movie);
        return true;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        if(objects != null && objects[0] instanceof MovieDatabase){
            if(objects[1] instanceof Movie){
                return addMovie((MovieDatabase)objects[0],(Movie) objects[1]);
            }
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

    }
}
