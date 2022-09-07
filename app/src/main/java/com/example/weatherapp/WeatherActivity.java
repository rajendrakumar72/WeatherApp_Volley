package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    private RequestQueue requestQueue;

    private WeatherAdapter adapter;
    RecyclerView rvList;
    ArrayList<WeatherModel> weatherModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        rvList=findViewById(R.id.rvWeather);
        weatherModelArrayList=new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);

        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=13.0827&lon=80.2707&appid=b53af9536f03fec21811fbb5ffdc8a5f";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("TAG", "onResponse: "+response.toString());
                        try {
                            JSONObject responseData=new JSONObject(response.toString());
                            JSONArray jsonArray = responseData.getJSONArray("list");
                            Log.d("TAG", "onResponseData: "+responseData.getJSONArray("list").get(0).toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject main=jsonObject.getJSONObject("main");
                                Log.d("TAG", "insideObj: "+main.toString());
                                double temp=main.getDouble("temp");
                                double feels_temp=main.getDouble("feels_like");
                                Log.d("TAG", "temp: "+temp+" "+feels_temp);
                                weatherModelArrayList.add(new WeatherModel(temp,feels_temp));
                                rvList.setAdapter(new WeatherAdapter(weatherModelArrayList,WeatherActivity.this));

                            }

//                                adapter=new WeatherAdapter(responseData.getJSONArray("list"))
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }
}