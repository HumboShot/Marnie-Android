package com.humboshot.marnie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.humboshot.marnie.Model.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> searchValues;
    private static final String SEARCH_VALUES = "SEARCH_VALUES";
//    private static final String ENDPOINT = "http://marnie-001-site1.atempurl.com/api/Route?from=Aalborg&to=Vejle&startTime=10:35:00";
//    private List<Route> routes = new ArrayList<>();
//    private RequestQueue requestQueue;
//    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        Button logOutButton = (Button) findViewById(R.id.log_out);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Toast.makeText(MainActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();

        EditText editTo = (EditText) findViewById(R.id.to);
        EditText editFrom = (EditText) findViewById(R.id.from);
        EditText editDate = (EditText) findViewById(R.id.date);
        EditText editTime = (EditText) findViewById(R.id.time);
        Button btnSearchTrain = (Button) findViewById(R.id.btn_TrainSearch);

        searchValues = new ArrayList<>();
        searchValues.add("Aalborg");
        searchValues.add("Hobro");
        searchValues.add("10:35:00");
//            requestQueue = Volley.newRequestQueue(getApplicationContext());
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
//            gson = gsonBuilder.create();
//
//            SetJourneyList();

        btnSearchTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrainFound.class);
                intent.putStringArrayListExtra(SEARCH_VALUES, searchValues);
                startActivity(intent);
            }
        });
    }

//    private void SetJourneyList() {
//        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, ENDPOINT, onRoutesLoaded, onRoutesError);
//
//        requestQueue.add(request);
//    }
//
//    private final com.android.volley.Response.Listener<String> onRoutesLoaded = new com.android.volley.Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            Log.d(MainActivity.class.getSimpleName(), response);
//
//            routes = Arrays.asList(gson.fromJson(response, Route[].class));
//            Log.d(MainActivity.class.getSimpleName(), routes.size() + " routes loaded.");
//
//            for (Route route : routes) {
//                Log.d(MainActivity.class.getSimpleName(), route.getId() + ": " + route.getName());
//            }
//        }
//    };
//
//    private final com.android.volley.Response.ErrorListener onRoutesError = new com.android.volley.Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.e(MainActivity.class.getSimpleName(), error.toString());
//        }
//    };

    private void logOut() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
