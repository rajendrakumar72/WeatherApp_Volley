package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherRecyclerHolder> {
    private ArrayList<WeatherModel> weatherModelArrayList;
    private Context context;

    public WeatherAdapter(ArrayList<WeatherModel> weatherModelArrayList, Context context) {
        this.weatherModelArrayList = weatherModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_list,parent,false);

        return new WeatherRecyclerHolder(view);
    }

    @Override
    public int getItemCount() {
        return weatherModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRecyclerHolder holder, int position) {
        WeatherModel model=weatherModelArrayList.get(position);
        double data=model.getTemp()-273.15;
        holder.txtTemp.setText(String.valueOf(data));

    }


    //b53af9536f03fec21811fbb5ffdc8a5f

    public class WeatherRecyclerHolder extends RecyclerView.ViewHolder{
        TextView txtTemp;
        public WeatherRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtTemp= itemView.findViewById(R.id.txtTempData);
        }
    }
}
