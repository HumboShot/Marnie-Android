package com.humboshot.marnie.Logic;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.humboshot.marnie.MainActivity;
import com.humboshot.marnie.Model.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 17-05-2017.
 */

public class CallApi {
    private List<Route> routes = new ArrayList<>();
    private RequestQueue requestQueue;
    private Gson gson;

    public CallApi(Context context, String endpoint) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public void GetListFromApi(String endpoint) {
        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, endpoint, onLoaded, onError);

        requestQueue.add(request);
    }

    private final com.android.volley.Response.Listener<String> onLoaded = new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(MainActivity.class.getSimpleName(), response);

            routes = Arrays.asList(gson.fromJson(response, Route[].class));
            Log.d(MainActivity.class.getSimpleName(), routes.size() + " routes loaded.");
        }
    };

    private final com.android.volley.Response.ErrorListener onError = new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainActivity.class.getSimpleName(), error.toString());
        }
    };


}
