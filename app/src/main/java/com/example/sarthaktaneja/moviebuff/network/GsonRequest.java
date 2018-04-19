package com.example.sarthaktaneja.moviebuff.network;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by sarthaktaneja on 17/4/18.
 */

public class GsonRequest extends Request<Object> {

    private final Gson gson = new Gson();
    private Object clazz ;
    private  Map<String, String> headers ;
    private  Response.Listener<Object> listener;

    public GsonRequest(int method, String url, Response.Listener<Object> listener, Response.ErrorListener errorListenerlistener,Object model) {
        super(method, url, errorListenerlistener);
        Log.d("-------------", "GsonRequest: url"+url);
        this.clazz = model;
        this.listener = listener;
    }

    @Override
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.d("----------", String.valueOf(response));
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));



            return Response.success(
                    gson.fromJson(json,clazz.getClass()),
                   getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Object response) {
     listener.onResponse(response);
    }
}
