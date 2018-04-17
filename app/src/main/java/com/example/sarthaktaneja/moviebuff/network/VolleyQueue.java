package com.example.sarthaktaneja.moviebuff.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by sarthaktaneja on 17/4/18.
 */

public class VolleyQueue {

    // Instantiate the RequestQueue.
    static RequestQueue queue ;

    public static RequestQueue getRequestQueue(Context context){
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return queue;
    }

    void add(Request request){
        queue.add(request);
    }
}
