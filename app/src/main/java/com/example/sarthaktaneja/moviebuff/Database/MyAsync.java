package com.example.sarthaktaneja.moviebuff.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sarthaktaneja.moviebuff.Model.Pojo1;

/**
 * Created by sarthaktaneja on 30/04/18.
 */

public class MyAsync extends AsyncTask<Object,Void,Boolean> {

    private Context context;

    public MyAsync(Context context)
    {
        this.context=context;
    }

    private boolean addMovie(MovieDatabase mdb, Pojo1 pojo1)
    {
        mdb.movieDao().insertAll(pojo1);
        return true;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        if(objects != null && objects[0] instanceof MovieDatabase){
            if(objects[1] instanceof Pojo1){
                return addMovie((MovieDatabase)objects[0],(Pojo1)objects[1]);
            }
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

    }
}
