package com.example.sarthaktaneja.moviebuff;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sarthaktaneja.moviebuff.RecyclerView.RecycleAdapter;
import com.example.sarthaktaneja.moviebuff.network.GsonRequest;
import com.example.sarthaktaneja.moviebuff.network.VolleyQueue;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = "MyTag";
    EditText search;
    ImageView button;
    String value;
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecycleAdapter recycleAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        button = (ImageView) findViewById(R.id.search_icon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapter);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        value = search.getText().toString();
        showProgress();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=63eb33950ea337fbdccd8195017f785c&query=" + value;
        VolleyQueue.getRequestQueue(this).add(new GsonRequest(0, url, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                hideProgress();
                if (response instanceof Pojo) {

                    setAdapter((Pojo) response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                Log.d("-------------", error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Pojo()));
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
    }

