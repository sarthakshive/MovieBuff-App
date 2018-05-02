package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sarthaktaneja.moviebuff.Database.Movie;
import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;
import com.example.sarthaktaneja.moviebuff.Database.MyAsync;
import com.example.sarthaktaneja.moviebuff.Model.Pojo1;
import com.squareup.picasso.Picasso;

public class Main2Activity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView img = (ImageView) findViewById(R.id.movie_photo);
        ImageView wish=(ImageView) findViewById(R.id.heart);
        final MovieDatabase mdb= Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"Movie").build();




        Pojo1 pojo1= (Pojo1) getIntent().getSerializableExtra("Object");

        String imgUrl="http://image.tmdb.org/t/p/w500/" + pojo1.getPosterPath();
        Picasso.with(this).load(imgUrl).error(R.drawable.ic_error_outline_black_24dp).placeholder(R.drawable.rotate).into(img);
        final Movie movie=new Movie(pojo1);
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final MyAsync myTask=new MyAsync(Main2Activity.this);
                    myTask.execute(mdb,movie);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                Toast.makeText(Main2Activity.this, "Added to Database", Toast.LENGTH_SHORT).show();
            }
        });
        }

//        private Movie addMovie(MovieDatabase mdb, Movie movie) {
//        mdb.movieDao().insertAll(movie);
//        return movie;
//    }

}
