package com.chamika_kasun.proweather;

import java.util.List;

import com.chamika_kasun.proweather.objects.DayWeather;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This Class is Used to as an Adapter for Sevenday List
 * @author Chamika
 * E-mail :  kasun.chamika@gmail.com
 */

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

	/**
	 * This methos is used to initialize the 7 day Fragment variables and assing values to thoses variables
	 */
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
			holder.weatherImage = (ImageView) view.findViewById(R.id.ivWeatherImage7Day);
			
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
		holder.weatherImage.setImageResource(getDrawable(dayWeather.getIconCode()));
		
		return view;
	}
	
	
	/**
	 * This methos is used to round the temarature to an int.
	 * @param temparature - Takes temparature value in float
	 * @return - Rounded Temparature in int
	 */
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
	
	/**
	 * This methos is used to get drwable icon according to the weather icon code
	 * @param weatherId - It takes the relevant weather condition pre defined value in String
	 * @return - Relevent drwable integer Value
	 */
	public int getDrawable(String weatherId){
		
		int id = Integer.parseInt(weatherId);
		int returnDrwanleId = 500;
		
		switch (id) {			
		
		case 500: //light rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 500");
			returnDrwanleId = R.drawable.q500;
			break;
			
		case 501: //moderate rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 501");
			returnDrwanleId = R.drawable.q501;
			break;
			
		case 502: //heavy intensity rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 502");
			returnDrwanleId = R.drawable.q502;
			break;

		case 503: //very heavy rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 503");
			returnDrwanleId = R.drawable.q503;
			break;

		case 504: //extreme rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 504");
			returnDrwanleId = R.drawable.q504;
			break;
			
		case 511: //freezing rain
			Log.v("Weather Icon Code", "Weather Icon Code : 511");
			returnDrwanleId = R.drawable.q511;
			break;
			
		case 520: //light intensity shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 520");
			returnDrwanleId = R.drawable.q520;
			break;
			
		case 521: //shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 521");
			returnDrwanleId = R.drawable.q521;
			break;
			
		case 522: //heavy intensity shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 522");
			returnDrwanleId = R.drawable.q522;
			break;
			
		case 800: //Sky is Clear
			Log.v("Weather Icon Code", "7 Weather Icon Code : 800");
			returnDrwanleId = R.drawable.q800;
			break;

		case 801: //few clouds
			Log.v("Weather Icon Code", "7 Weather Icon Code : 801");
			returnDrwanleId = R.drawable.q801;
			break;

		case 802: //few clouds
			Log.v("Weather Icon Code", "7 Weather Icon Code : 802");
			returnDrwanleId = R.drawable.q802;
			break;
			
		case 803: //scattered clouds 
			Log.v("Weather Icon Code", "7 Weather Icon Code : 803");
			returnDrwanleId = R.drawable.q803;
			break;
			
		case 804: //broken clouds  
			Log.v("Weather Icon Code", "7 Weather Icon Code : 804");
			returnDrwanleId = R.drawable.q804;
			break;
			
		case 805: //overcast clouds  
			Log.v("Weather Icon Code", "7  Weather Icon Code : 805");
			returnDrwanleId = R.drawable.q805;
			break;
		}
		
		
		return returnDrwanleId;
	}
	
	
	
	
	/**
	 * This is a Inner Class Which use to Hold the TextViews
	 * @author Chamika
	 * E-mail : kasun.chamika@gmail.com
	 */
	private static class DayHolder{		

		TextView  dayName,dayDescription, dayTemperature,
		dayTemperatureMax, dayTemperatureMin, dayHumidity,
		dayWindSpeed, dayWindDirection, dayPressure;
		
		ImageView weatherImage;
		
	}
	
	
	

}
