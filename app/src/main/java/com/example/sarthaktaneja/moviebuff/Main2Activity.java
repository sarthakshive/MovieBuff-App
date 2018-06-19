package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sarthaktaneja.moviebuff.Database.Movie;
import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;
import com.example.sarthaktaneja.moviebuff.Database.MyAsync;
import com.example.sarthaktaneja.moviebuff.Model.Pojo1;
import com.example.sarthaktaneja.moviebuff.Notifications.setReminderr;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView img = (ImageView) findViewById(R.id.movie_photo);
        ImageView wish=(ImageView) findViewById(R.id.heart);
        ImageView notify=(ImageView) findViewById(R.id.notify);
        final TextView time=(TextView) findViewById(R.id.movienam);
        TextView movDetails = (TextView) findViewById(R.id.summary);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView title = (TextView) findViewById(R.id.movienam);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.stars);

        final MovieDatabase mdb= Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"Movie").build();

        final Pojo1 pojo1= (Pojo1) getIntent().getSerializableExtra("Object");

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
                final int[] hour = {mcurrentTime.get(Calendar.HOUR_OF_DAY)};
                final int[] minute = {mcurrentTime.get(Calendar.MINUTE)};
                final int[] year = {mcurrentTime.get(Calendar.YEAR)};
                final int[] month = {mcurrentTime.get(Calendar.MONTH)};
                final int[] day = {mcurrentTime.get(Calendar.DAY_OF_MONTH)};
                final TimePickerDialog mTimePicker;
                final DatePickerDialog mDatePicker;
                mTimePicker = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                        hour[0] = selectedHour;
                        minute[0] = selectedMinute;
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, year[0]);
                        cal.set(Calendar.MONTH, month[0]);
                        cal.set(Calendar.DATE, day[0]);
                        cal.set(Calendar.HOUR_OF_DAY, hour[0]);
                        cal.set(Calendar.MINUTE, minute[0]);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        Date date = cal.getTime();

                        Date d = new Date();
                        String format="yyyy-MM-dd hh:mm:ss a";
                        Log.d("--------------", String.valueOf(date.after(d)));
                        Log.d("-----------------",new SimpleDateFormat(format).format(date));
                        Log.d("-----------------",new SimpleDateFormat(format).format(d));
                        setReminderr.Remind(date,pojo1.getTitle(),"Watch the movie now bitch!!",Main2Activity.this);
                    }
                }, hour[0], minute[0], false);//NO 24 hour time
                mTimePicker.setTitle("Select Time");


                mDatePicker = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        time.setText(i + " " + i1 + " " + i2);
                        day[0] = datePicker.getDayOfMonth();
                        month[0] = datePicker.getMonth();
                        year[0] = datePicker.getYear();
                        mTimePicker.show();
                    }
                }, year[0], month[0], day[0]);
                mDatePicker.show();

            }
        });

        movDetails.setText(pojo1.getOverview().toString());
        rating.setText(pojo1.getVoteAverage().toString());
        title.setText(pojo1.getOriginalTitle().toString());
        ratingBar.setRating(pojo1.getVoteAverage().floatValue());

    }


//        private Movie addMovie(MovieDatabase mdb, Movie movie) {
//        mdb.movieDao().insertAll(movie);
//        return movie;
//    }

}
