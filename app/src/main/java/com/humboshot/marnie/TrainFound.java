package com.humboshot.marnie;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.humboshot.marnie.Model.Route;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainFound extends Activity {
    private static final String ENDPOINT = "http://marnie-001-site1.atempurl.com/api/";
    private List<Route> routes = new ArrayList<>();
    private RequestQueue requestQueue;
    private Gson gson;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private String searchString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_found);
        ArrayList<String> searchValues = getIntent().getStringArrayListExtra("SEARCH_VALUES");

        StringBuilder end = new StringBuilder();
        end.append(ENDPOINT);
        end.append("Route?from=");
        end.append(searchValues.get(0));
        end.append("&to=");
        end.append(searchValues.get(1));
        end.append("&startTime=");
        end.append(searchValues.get(2));
        searchString = end.toString();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        listView = (ListView) findViewById(R.id.RouteList);;

        SetRouteList();



    }
//
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        String item = (String) getListAdapter().getItem(position);
//        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
//    }
private void SetRouteList() {
    StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, searchString, onRoutesLoaded, onRoutesError);

    requestQueue.add(request);
}

    private final com.android.volley.Response.Listener<String> onRoutesLoaded = new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(MainActivity.class.getSimpleName(), response);

            routes = Arrays.asList(gson.fromJson(response, Route[].class));
            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
            listView.setAdapter(arrayAdapter);
        }
    };

    private final com.android.volley.Response.ErrorListener onRoutesError = new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainActivity.class.getSimpleName(), error.toString());
        }
    };
}
