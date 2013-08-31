package com.chamika_kasun.proweather;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.JSONParser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View convertView = inflater.inflate(R.layout.home, container, false);

		updateTime = (TextView) convertView.findViewById(R.id.tvUpdateTime);
		location = (TextView) convertView.findViewById(R.id.tvLocation);
		time = (TextView) convertView.findViewById(R.id.tvTimeCurrent);
		dayWord = (TextView) convertView.findViewById(R.id.tvDayWord);
		day = (TextView) convertView.findViewById(R.id.tvDay);
		temperature = (TextView) convertView.findViewById(R.id.tvTemparature);
		temperatureMin = (TextView) convertView.findViewById(R.id.tvTempMinValue);
		temperatureMax = (TextView) convertView.findViewById(R.id.tvtempMax);
		humidity = (TextView) convertView.findViewById(R.id.tvHumidityValue);
		sunrise = (TextView) convertView.findViewById(R.id.tvSunriseValue);
		sunset = (TextView) convertView.findViewById(R.id.tvSunsetValue);
		windSpeed = (TextView) convertView.findViewById(R.id.tvWindValue);
		windDirection = (TextView) convertView.findViewById(R.id.tvWindDirectionValue);
		pressure = (TextView) convertView.findViewById(R.id.tvPressureValue);
		
		// calling background process
		executeBackgroundTask(Constants.LOCATION_WEATHER_URL);

		return convertView;

	}

	@Override
	public void onTaskFinished(String result) {
		super.onTaskFinished(result);

		if (result != null) {
			Weather weatherInfo = JSONParser.getLocationWeather(result);
//			temperature.setText(String.valueOf(weatherInfo.getTemperature()));
//			temperatureMin.setText(String.valueOf(weatherInfo.getTemperatureMin()));
//			temperatureMax.setText(String.valueOf(weatherInfo.getTemperatureMax()));
//			humidity.setText(String.valueOf(weatherInfo.getHumidity()));
//			sunrise.setText(String.valueOf(weatherInfo.getSunrise()));
//			sunset.setText(String.valueOf(weatherInfo.getSunset()));
//			windSpeed.setText(String.valueOf(weatherInfo.getWindSpeed()));
//			windDirection.setText(String.valueOf(weatherInfo.getDeg()));
//			location.setText(weatherInfo.getCity());
//			humidity.setText(String.valueOf(weatherInfo.getHumidity()));
//			pressure.setText(String.valueOf(weatherInfo.getPressure()));

		} else {
			Toast.makeText((Context) getActivity(), "Error occured", Toast.LENGTH_SHORT).show();
		}

	}

}
