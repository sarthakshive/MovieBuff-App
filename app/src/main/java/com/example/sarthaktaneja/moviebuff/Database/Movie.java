package com.example.sarthaktaneja.moviebuff.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.sarthaktaneja.moviebuff.Model.Pojo1;

@Entity(tableName = "movie")
public class Movie {

    public Movie(Pojo1 pojo1)
    {
        firstName=pojo1.getOriginalTitle();
        rating=pojo1.getVoteAverage();
    }

    Movie()
    {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "movie_name")
    private String firstName;

    @ColumnInfo(name = "rating")
    private double rating;

}
