package com.chamika_kasun.proweather;

import java.util.ArrayList;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.City;
import com.chamika_kasun.proweather.objects.Location;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.objects.Wind;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.JSONParser;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class Favourites extends BaseFragment {

	private ListView listView;
	private ArrayList<City> cities;
	private ArrayList<Location> locations;
	private int locationIndex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		View contentView = inflater.inflate(R.layout.favourites, container,
				false);

		listView = (ListView) contentView.findViewById(R.id.lvFavourite);
		cities = new ArrayList<City>();
		locations = new ArrayList<Location>();
		locationIndex = 0;

		Location matara = new Location((float) 75.6, (float) 6.8, "SriLanka",
				"Matara");
		Location galle = new Location((float) 75.6, (float) 6.8, "SriLanka",
				"Galle");
		Location panadura = new Location((float) 75.6, (float) 6.8, "SriLanka",
				"Panadura");
		Location moratuwa = new Location((float) 75.6, (float) 6.8, "SriLanka",
				"Moratuwa");
		Location colombo = new Location((float) 75.6, (float) 6.8, "SriLanka",
				"Colombo");

		locations.add(matara);
		locations.add(colombo);
		locations.add(moratuwa);
		locations.add(panadura);
		locations.add(galle);

		executeTasks(locations.get(locationIndex).getLatitude(),
				locations.get(locationIndex).getLongitude());

		// City matara = new City();
		// matara.setDescription("Lite Rain");
		// matara.setHumidity(50);
		// matara.setIconCode("D056");
		// matara.setLocation(new Location((float)75.6,(float) 6.8,
		// "SriLanka","Matara"));
		// matara.setMainDescription("Cloudy");
		// matara.setPressure((float)1000.5);
		// matara.setTemperature((float)25.6);
		// matara.setTemperatureMax((float)28.3);
		// matara.setTemperatureMin((float)23.5);
		// matara.setWind(new Wind((float)4.78, (float)205.6));
		//
		// City galle = new City();
		// galle.setDescription("Lite Rain");
		// galle.setHumidity(50);
		// galle.setIconCode("D056");
		// galle.setLocation(new Location((float)75.6,(float) 6.8,
		// "SriLanka","galle"));
		// galle.setMainDescription("Cloudy");
		// galle.setPressure((float)1000.5);
		// galle.setTemperature((float)25.6);
		// galle.setTemperatureMax((float)28.3);
		// galle.setTemperatureMin((float)23.5);
		// galle.setWind(new Wind((float)4.78, (float)205.6));
		//
		// City panadura = new City();
		// panadura.setDescription("Lite Rain");
		// panadura.setHumidity(50);
		// panadura.setIconCode("D056");
		// panadura.setLocation(new Location((float)75.6,(float) 6.8,
		// "SriLanka","panadura"));
		// panadura.setMainDescription("Cloudy");
		// panadura.setPressure((float)1000.5);
		// panadura.setTemperature((float)25.6);
		// panadura.setTemperatureMax((float)28.3);
		// panadura.setTemperatureMin((float)23.5);
		// panadura.setWind(new Wind((float)4.78, (float)205.6));
		//
		// City Moratuwa = new City();
		// Moratuwa.setDescription("Lite Rain");
		// Moratuwa.setHumidity(50);
		// Moratuwa.setIconCode("D056");
		// Moratuwa.setLocation(new Location((float)75.6,(float) 6.8,
		// "SriLanka","Moratuwa"));
		// Moratuwa.setMainDescription("Cloudy");
		// Moratuwa.setPressure((float)1000.5);
		// Moratuwa.setTemperature((float)25.6);
		// Moratuwa.setTemperatureMax((float)28.3);
		// Moratuwa.setTemperatureMin((float)23.5);
		// Moratuwa.setWind(new Wind((float)4.78, (float)205.6));
		//
		// City Colombo = new City();
		// Colombo.setDescription("Lite Rain");
		// Colombo.setHumidity(50);
		// Colombo.setIconCode("D056");
		// Colombo.setLocation(new Location((float)75.6,(float) 6.8,
		// "SriLanka","Colombo"));
		// Colombo.setMainDescription("Cloudy");
		// Colombo.setPressure((float)1000.5);
		// Colombo.setTemperature((float)25.6);
		// Colombo.setTemperatureMax((float)28.3);
		// Colombo.setTemperatureMin((float)23.5);
		// Colombo.setWind(new Wind((float)4.78, (float)205.6));
		//
		// cities.add(matara);
		// cities.add(galle);
		// cities.add(panadura);
		// cities.add(Moratuwa);
		// cities.add(Colombo);

		// executeBackgroundTask(Constants.LOCATION_7DAY_WEATHER_FORCAST_URL,
		// true);

		return contentView;

	}

	@Override
	public void onTaskFinished(String result) {
		// TODO Auto-generated method stub
		super.onTaskFinished(result);

		if (result != null && result.length() > 0) {
			
			Log.v("Return Result", "Return Result : "+result);

			City weatherCity = jsonParser.getCityWeather(result);
			cities.add(weatherCity);
			updateAdapter();
			locationIndex++;

			if (locationIndex < locations.size()) {
				executeTasks(locations.get(locationIndex).getLatitude(),
						locations.get(locationIndex).getLongitude());
			}

		} else {
			Toast.makeText((Context) getActivity(), "Error occured",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void updateAdapter() {

		FavouritsAdapter favourietsAdapter = new FavouritsAdapter(
				getActivity(), R.layout.favouriterow, cities);
		listView.setAdapter(favourietsAdapter);

	}

	private void executeTasks(float longitude, float lattitude) {
	
		
		executeBackgroundTask(Constants.LOCATION_WEATHER_URL_ON_COORDINATES
				+ "lat=" + lattitude + "&lon=" +longitude , true);
	}

}
