package com.humboshot.marnie;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.humboshot.marnie.Model.Route;

import org.json.JSONObject;

import java.util.ArrayList;

public class TrainFound extends Activity {

    public ArrayList<Route> routeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_found);

        routeList = getIntent().getParcelableArrayListExtra("routeList");

        ArrayAdapter<JSONObject> adapter = new ArrayAdapter<JSONObject>(this, android.R.layout.simple_list_item_1, android.R.id.text1, searchResultsArray);
        ListView lv = (ListView) findViewById(R.id.RouteList);
        lv.setAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
