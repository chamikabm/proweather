package com.chamika_kasun.proweather;

import java.util.List;

import com.chamika_kasun.proweather.objects.DayWeather;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SevenDayAdapter extends ArrayAdapter<DayWeather>{
	
	private Context context;
	private int layoutResourceId;
	private List<DayWeather> objects;

	public SevenDayAdapter(Context context, int layoutResourceId,
			List<DayWeather> objects) {
		super(context, layoutResourceId, objects);

		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.objects = objects;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		DayHolder holder = null;
		
		if(view == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			view = inflater.inflate(layoutResourceId,parent, false);
			
			holder = new DayHolder();
			holder.dayTemperature = (TextView) view.findViewById(R.id.tvDayTemparature);
			holder.dayDescription = (TextView) view.findViewById(R.id.tvModayDescription);
			holder.dayName = (TextView) view.findViewById(R.id.tv_7Day_Monday);
			holder.dayTemperatureMax = (TextView) view.findViewById(R.id.tvMondayMaxTemparatureValue);
			holder.dayTemperatureMin = (TextView) view.findViewById(R.id.tvMondayMinTemparatureValue);
			holder.dayHumidity = (TextView) view.findViewById(R.id.tvMondayHumidityValue);
			holder.dayPressure = (TextView) view.findViewById(R.id.tvPressureValue);
			holder.dayWindSpeed = (TextView) view.findViewById(R.id.tvMondayWindSpeedValue);
			holder.dayWindDirection = (TextView) view.findViewById(R.id.tvMondayWindDirectionValue);
			
			view.setTag(holder);
			
		}else{
			holder = (DayHolder) view.getTag();
		}
		
		DayWeather dayWeather = objects.get(position);
		holder.dayTemperature.setText("" + getCorrectTemparature(dayWeather.getTemperature())+"\u2103");
		holder.dayDescription.setText(""+dayWeather.getDescription());
		holder.dayTemperatureMax.setText(""+getCorrectTemparature(dayWeather.getTemperatureMax())+"\u2103");
		holder.dayTemperatureMin.setText(""+getCorrectTemparature(dayWeather.getTemperatureMin())+"\u2103");
		holder.dayHumidity.setText(""+dayWeather.getHumidity()+"%");
		holder.dayPressure.setText(""+dayWeather.getPressure());
		holder.dayWindSpeed.setText(""+dayWeather.getWind().getWindSpeed()+"kmph");
		holder.dayWindDirection.setText(""+dayWeather.getWind().getDeg());
		holder.dayName.setText(dayWeather.getDay());
		
		return view;
	}
	
	public int getCorrectTemparature(float temparature) {

		int temparetureCelcius;
		long number = (long) temparature;
		float fraction = temparature - number;

		if (fraction >= 0.5) {
			temparetureCelcius = (int) (Math.floor(temparature));

		} else {
			temparetureCelcius = (int) (Math.ceil(temparature));
		}

		return temparetureCelcius;
	}
	
	
	
	
	
	private static class DayHolder{		

		TextView  dayName,dayDescription, dayTemperature,
		dayTemperatureMax, dayTemperatureMin, dayHumidity,
		dayWindSpeed, dayWindDirection, dayPressure;
		
	}
	

}
