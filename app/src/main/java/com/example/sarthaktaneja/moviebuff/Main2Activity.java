package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sarthaktaneja.moviebuff.Database.Movie;
import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;
import com.example.sarthaktaneja.moviebuff.Database.MyAsync;
import com.example.sarthaktaneja.moviebuff.Model.Pojo1;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class Main2Activity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView img = (ImageView) findViewById(R.id.movie_photo);
        ImageView wish=(ImageView) findViewById(R.id.heart);
        ImageView notify=(ImageView) findViewById(R.id.notify);
        final TextView time=(TextView) findViewById(R.id.movienam);

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
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);
                final TimePickerDialog mTimePicker;
                final DatePickerDialog mDatePicker;
                mTimePicker = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//NO 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

                mDatePicker = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        time.setText(i + " " + i1 + " " + i2);
                    }
                },year,month,day);
                mDatePicker.show();

            }
        });


    }


//        private Movie addMovie(MovieDatabase mdb, Movie movie) {
//        mdb.movieDao().insertAll(movie);
//        return movie;
//    }

}
