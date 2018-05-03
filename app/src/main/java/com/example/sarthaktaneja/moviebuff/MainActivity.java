package com.example.sarthaktaneja.moviebuff;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sarthaktaneja.moviebuff.Database.Movie;
import com.example.sarthaktaneja.moviebuff.Database.MovieDatabase;
import com.example.sarthaktaneja.moviebuff.Model.Pojo;
import com.example.sarthaktaneja.moviebuff.RecyclerView.RecycleAdapter;
import com.example.sarthaktaneja.moviebuff.network.GsonRequest;
import com.example.sarthaktaneja.moviebuff.network.VolleyQueue;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener {

    public final String TAG = "MyTag";
    EditText search;
    ImageView button;
    ImageView heart;
    String value;
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        search = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        button = (ImageView) findViewById(R.id.search_icon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapter);
        heart=(ImageView) findViewById(R.id.heart);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager1);
        button.setOnClickListener(this);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    networkRequest();
                    return true;
                }
                return false;
            }
        });
        search.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                        networkRequest();
                    }

                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    private Timer timer = new Timer();
                    private final long DELAY = 500; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
//                        timer.cancel();
//                        timer = new Timer();
//                        timer.schedule(
//                                new TimerTask() {
//                                    @Override
//                                    public void run() {
//                                        networkRequest();
//                                    }
//                                },
//                                DELAY
//                        );
                    }
                }
        );
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.search_icon:

            if (isOnline() == true) {
                networkRequest();
            } else {
                Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.heart:
                List<Movie> list = displayMovie();

                break;
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private List<Movie> displayMovie() {
        MovieDatabase mdb=null;
        return mdb.movieDao().getAll();
    }


    private void setAdapter(Pojo response) {
                recycleAdapter = new RecycleAdapter(response.getResults(),this);
                recyclerView.setAdapter(recycleAdapter);
            }

            private void showProgress() {
                findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
            }

            private void hideProgress() {
                findViewById(R.id.progressbar).setVisibility(View.INVISIBLE);
            }

            private void showRecycler()
            {
                findViewById(R.id.recycler_view).setVisibility(View.VISIBLE);
            }

            private void hideRecycler()
            {
                findViewById(R.id.recycler_view).setVisibility(View.INVISIBLE);
            }

            private void networkRequest()
            {
                value = search.getText().toString();
                showProgress();
                String url = "https://api.themoviedb.org/3/search/movie?api_key=63eb33950ea337fbdccd8195017f785c&query=" + value;

                Request customReq = new GsonRequest(0, url, new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object response) {
                        hideProgress();
                        if (response instanceof Pojo) {
                            setAdapter((Pojo) response);
                            showRecycler();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgress();
                        Log.d("-------------", error.getMessage());
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Pojo());

                VolleyQueue.getRequestQueue(this).add(customReq);
            }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }




}

