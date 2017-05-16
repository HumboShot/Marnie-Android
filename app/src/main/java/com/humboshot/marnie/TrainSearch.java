package com.humboshot.marnie;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.humboshot.marnie.Model.Route;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.RequestLine;

public class TrainSearch extends AppCompatActivity {
    private Button btnSearchTrain;
    private EditText editTo;
    private EditText editFrom;
    private EditText editDate;
    private EditText editTime;
    private Response apiResponse;
    private List<Route> routeList;
    Route route;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);

        editTo = (EditText) findViewById(R.id.to);
        editFrom = (EditText) findViewById(R.id.from);
        editDate = (EditText) findViewById(R.id.date);
        editTime = (EditText) findViewById(R.id.time);
        btnSearchTrain = (Button) findViewById(R.id.btn_TrainSearch);

        //goes in onclick for train

        routeList = new ArrayList<>();
        getRouteList();
        btnSearchTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getRouteList() {
        OkHttpClient client = new OkHttpClient();
        String txtFrom;// = editFrom.getText().toString().trim();
        String txtTo;// = editTo.getText().toString().trim();
        String txtDate = new Date().toString();

        txtFrom = "Aalborg";
        txtTo = "Århus";

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://marnie-001-site1.atempurl.com/api/Route").newBuilder();
        //urlBuilder.addQueryParameter("from", txtFrom);
        //urlBuilder.addQueryParameter("to", txtTo);
        //urlBuilder.addQueryParameter("startTime", txtDate);
        String url = urlBuilder.build().toString();

        try {
            Request request = new Request.Builder().url(url).build();

            // Get a handler that can be used to post to the main thread
            client.newCall(request).enqueue(new Callback() {
                                                @Override
                                                public void onFailure(Call call, IOException e) {
                                                    e.printStackTrace();
                                                }

                                                @Override
                                                public void onResponse(Call call, final Response response) throws IOException {
                                                    if (!response.isSuccessful()) {
                                                        throw new IOException("Unexpected code " + response);
                                                    }else {
                                                         apiResponse = response;
                                                        // Create new gson object
                                                        final Gson gson = new Gson();
                                                        Type listType = new TypeToken<ArrayList<Route>>(){}.getType();
                                                        routeList = new Gson().fromJson(response.body().charStream(), listType);
                                                        String hej = "hej";
                                                    }
                                                }
                                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
       String test;
        test = "breakpoint";
    }
}
