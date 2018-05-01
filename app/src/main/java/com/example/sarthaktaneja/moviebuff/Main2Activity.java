package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;
import com.example.sarthaktaneja.moviebuff.Database.MyAsync;
import com.example.sarthaktaneja.moviebuff.Model.Pojo1;
import com.squareup.picasso.Picasso;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView img = (ImageView) findViewById(R.id.movie_photo);
        MovieDatabase mdb= Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"Movie-Database").build();


        MyAsync myTask=new MyAsync(this);
        myTask.execute(mdb,new Pojo1());


        Pojo1 pojo1= (Pojo1) getIntent().getSerializableExtra("Object");

        String imgUrl="http://image.tmdb.org/t/p/w500/" + pojo1.getPosterPath();
        Picasso.with(this).load(imgUrl).error(R.drawable.ic_error_outline_black_24dp).placeholder(R.drawable.rotate).into(img);

    }


}
