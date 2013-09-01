package com.chamika_kasun.proweather;

import java.util.Calendar;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.JSONParser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends BaseFragment {

	TextView updateTime, location, time, dayWord, day, temperature,
			temperatureMax, temperatureMin, humidity, sunrise, sunset,
			windSpeed, windDirection, pressure;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View convertView = inflater.inflate(R.layout.home, container, false);

		updateTime = (TextView) convertView.findViewById(R.id.tvUpdateTime);
		location = (TextView) convertView.findViewById(R.id.tvLocation);
		time = (TextView) convertView.findViewById(R.id.tvTimeCurrent);
		dayWord = (TextView) convertView.findViewById(R.id.tvDayWord);
		day = (TextView) convertView.findViewById(R.id.tvDay);
		temperature = (TextView) convertView.findViewById(R.id.tvTemparature);
		temperatureMin = (TextView) convertView
				.findViewById(R.id.tvTempMinValue);
		temperatureMax = (TextView) convertView
				.findViewById(R.id.tvTempMaxValue);
		humidity = (TextView) convertView.findViewById(R.id.tvHumidityValue);
		sunrise = (TextView) convertView.findViewById(R.id.tvSunriseValue);
		sunset = (TextView) convertView.findViewById(R.id.tvSunsetValue);
		windSpeed = (TextView) convertView.findViewById(R.id.tvWindValue);
		windDirection = (TextView) convertView
				.findViewById(R.id.tvWindDirectionValue);
		pressure = (TextView) convertView.findViewById(R.id.tvPressureValue);

		// calling background process
		executeBackgroundTask(Constants.LOCATION_WEATHER_URL);

		return convertView;

	}

	@Override
	public void onTaskFinished(String result) {
		super.onTaskFinished(result);

		if (result != null) {

			setTimeAndDate();

			Weather weatherInfo = JSONParser.getLocationWeather(result);

			int temp, maxTemp, minTemp;

			temp = (int) (weatherInfo.getTemperature() - 272.15);

			temperature.setText(String.valueOf(temp) + "\u2103");

			minTemp = (int) (weatherInfo.getTemperatureMin() - 272.15);
			temperatureMin.setText(String.valueOf(minTemp) + "\u2103");

			maxTemp = (int) (weatherInfo.getTemperatureMax() - 272.15);
			temperatureMax.setText(String.valueOf(maxTemp) + "\u2103");

			humidity.setText(String.valueOf(weatherInfo.getHumidity()));
			sunrise.setText(String.valueOf(weatherInfo.getSunrise()));
			sunset.setText(String.valueOf(weatherInfo.getSunset()));
			windSpeed.setText(String.valueOf(weatherInfo.getWindSpeed())
					+ "kmph");
			windDirection.setText(String.valueOf(weatherInfo.getDeg()));
			location.setText(weatherInfo.getCity());
			humidity.setText(String.valueOf(weatherInfo.getHumidity()+"%"));
			pressure.setText(String.valueOf(weatherInfo.getPressure()));

		} else {
			Toast.makeText((Context) getActivity(), "Error occured",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void setTimeAndDate() {
		// TODO Auto-generated method stub
		
		Calendar c = Calendar.getInstance();

		String sYear =""+ c.get(Calendar.YEAR);
		String smonth = " "+ c.get(Calendar.MONTH);
		String sday = " " + c.get(Calendar.DAY_OF_MONTH) ;
		int hour =c.get(Calendar.HOUR_OF_DAY);
		String sminute =""+ c.get(Calendar.MINUTE);
		String txt = "";
		int dayWeek = c.get(Calendar.DAY_OF_WEEK);
		String dayText = "";
		
		switch(dayWeek){
		
		case 1 :
			dayText = "Monday";
			break;
		
		case 2 :
			dayText = "Tuesday";
			break;
			
		case 3 : 
			dayText = "Wednesday";
			break;
			
		case 4 :
			dayText = "Thursday";
			break;
		
		case 5 :
			dayText = "Friday";
			break;
			
		case 6 : 
			dayText = "Saturday";
			break;
			
		case 7 : 
			dayText = "Sunday";
			break;	
		
		}
		
		if(hour >=  12){
			txt = "PM";
			hour = hour-12;
		}else{
			txt = "AM";
		}
		
		dayWord.setText(""+dayText);
		time.setText(hour+":"+sminute+""+txt);
		day.setText(smonth+"/"+sday+"/"+sYear);
		
		
	}
}
