package com.chamika_kasun.proweather;

import java.util.ArrayList;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.DayWeather;
import com.chamika_kasun.proweather.utility.Constants;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This Class is Used to Retrive and Show data of the GPS Location Weather Data For Comming 7 Days (Weather Forecast)
 * @author Chamika
 * 		   E-mail :  kasun.chamika@gmail.com
 */

public class Day_7 extends BaseFragment {

	ListView listview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View converView = inflater.inflate(R.layout.day_7, container, false);

		listview = (ListView) converView.findViewById(R.id.lv7DayForcast);

		executeBackgroundTask(Constants.LOCATION_7DAY_WEATHER_FORCAST_URL, true);
		
		
		//########### Used For NUll Value and Other Testing
		//executeBackgroundTask(Constants.LOCATION_7DAY_WEATHER_FORCAST_URL, true);
		//
		//		DayWeather dywthr = new DayWeather();
		//		Wind windd = new Wind((float)1.41, (float)89);
		//		
		//		dywthr.setTemperature((float)25.6);
		//		dywthr.setTemperatureMax((float)27.6);
		//		dywthr.setTemperatureMin((float)23.5);
		//		dywthr.setHumidity(80);
		//		dywthr.setPressure((float)1002.5);
		//		dywthr.setDescription("Heavy Rain");
		//		dywthr.setIconCode("01d");
		//		dywthr.setWind(windd);
		//		
		//		ArrayList<DayWeather> arrayList = new ArrayList<DayWeather>();		
		//		arrayList.add(dywthr);
		//		
		//		SevenDayAdapter sevenDayAdapter = new SevenDayAdapter(
		//				getActivity(), R.layout.sevendayrow, arrayList);
		//		listview.setAdapter(sevenDayAdapter);
		
		return converView;

	}

	@Override
	public void onTaskFinished(String result) {
		super.onTaskFinished(result);
		
		Log.v("Result Day_7", "Result Day_7 : "+result);

		if (result != null && result.length() > 0) {

			ArrayList<DayWeather> arrayList = jsonParser.getSevendayForecast(result);

			if (arrayList != null) {

				SevenDayAdapter sevenDayAdapter = new SevenDayAdapter(getActivity(), R.layout.sevendayrow, arrayList);
				listview.setAdapter(sevenDayAdapter);

			}

		} else {
			Toast.makeText((Context) getActivity(), "Error Loading 7_Day Data",Toast.LENGTH_SHORT).show();
		}

	}

}
