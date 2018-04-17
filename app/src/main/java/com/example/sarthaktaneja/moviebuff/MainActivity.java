package com.example.sarthaktaneja.moviebuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sarthaktaneja.moviebuff.network.GsonRequest;
import com.example.sarthaktaneja.moviebuff.network.VolleyQueue;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MyTag";
    String title;
    TextView txt1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (TextView) findViewById(R.id.recyclename);


    String url = "https://api.themoviedb.org/3/search/movie?api_key=63eb33950ea337fbdccd8195017f785c&query=coco";

    VolleyQueue.getRequestQueue(this).add(new GsonRequest(0, url, new Response.Listener<Object>() {
        @Override
        public void onResponse(Object response) {
            if(response instanceof Pojo){
                ((Pojo)response).getResults();


            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("-------------",error.getMessage());
            Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
        }
    },new Pojo()));
    }



    @Override
    protected void onStop() {
        super.onStop();
    }
}
