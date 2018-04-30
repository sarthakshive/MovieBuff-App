package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sarthaktaneja.moviebuff.Database.Movie;
import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

}
