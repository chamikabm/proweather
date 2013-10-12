package com.chamika_kasun.proweather;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.City;
import com.chamika_kasun.proweather.objects.Location;
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
 * This Class is Used to Retrive and Show data of the Favourites Locations Weather Data
 * @author Chamika
 * E-mail :  kasun.chamika@gmail.com
 */

public class Favourites extends BaseFragment {

	//Create Data Types to Hold ListView, Cities, Locations and the locationIndex
	private ListView listView;
	private ArrayList<City> cities;
	private ArrayList<Location> locations;
	private int locationIndex;
	private ProWeatherDataBase dataBase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		View contentView = inflater.inflate(R.layout.favourites, container,false);

//		//Initialize the listview and City
		listView = (ListView) contentView.findViewById(R.id.lvFavourite);
		cities = new ArrayList<City>();
		
//		locations = new ArrayList<Location>();
		
		
		//Initialize the iterating variable and the Locations Array List and Database
		locationIndex = 0;
		locations = new ArrayList<Location>();
		dataBase = new ProWeatherDataBase(getActivity());
		
		try {
			dataBase.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		locations = dataBase.getData();
		
		dataBase.close();
		

//		Location matara = new Location((float)  80.5333, (float)5.9500 , "SriLanka","Matara");
//		Location jaffna = new Location((float) 80.0000, (float) 9.6667, "SriLanka","Jaffna");
//		Location panadura = new Location((float) 79.9042, (float) 6.7133, "SriLanka","Panadura");
//		Location moratuwa = new Location((float) 79.8767, (float) 6.7991, "SriLanka","Moratuwa");
//		Location colombo = new Location((float)79.8428 , (float) 6.9344, "SriLanka","Colombo");
//
//		locations.add(matara);
//		locations.add(colombo);
//		locations.add(moratuwa);
//		locations.add(panadura);
//		locations.add(jaffna);
//
		if(locations.size()>0){
		executeTasks(locations.get(locationIndex).getLongitude(),locations.get(locationIndex).getLatitude());
		}
		//Testes Codes Check The Data Pass 
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
		super.onTaskFinished(result);

		Log.v("Result Favouriets", "Result Favouriets : "+result);
		
		//This will execute if the returned result is not null and has elements.
		if (result != null && result.length() > 0) {
			
			//To check Weather the Data Retrived Properly
			Log.v("Return Result", "Return Result : "+result);

			//Get City Weather Data Through JSONParser
			City weatherCity = jsonParser.getCityWeather(result);
			cities.add(weatherCity);
			updateAdapter();
			locationIndex++;

			if (locationIndex < locations.size()) {
				executeTasks(locations.get(locationIndex).getLongitude(),locations.get(locationIndex).getLatitude());
			}

		} else {
			//User to make a  Toast if an Error Occcur
			Toast.makeText((Context) getActivity(), "Error Loading Favouriets Data",Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * This methos is Used to Initialise the Adaprt Class and To set the ListView to Adapter
	 */
	private void updateAdapter() {

		//Initialize the FavouriteAdapter
		FavouritsAdapter favourietsAdapter = new FavouritsAdapter(getActivity(), R.layout.favouriterow, cities);
		
		//Set favouriteadapter to listView for display them
		listView.setAdapter(favourietsAdapter);

	}

	/**
	 * This methos is used to execute URL to retrieve Data from API through JSONParser
	 * @param longitude - This takes longitude of the Location in float
	 * @param lattitude - This takes the lattiude of the Location in flaot
	 */
	private void executeTasks(float longitude, float lattitude) {
		
		executeBackgroundTask(Constants.LOCATION_WEATHER_URL_ON_COORDINATES	+ "lat=" + lattitude + "&lon=" +longitude , true);
	}

}
