package com.chamika_kasun.proweather.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chamika_kasun.proweather.objects.Location;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.objects.Wind;

public class JSONParser {

	public static Weather getLocationWeather(String json) {

		Weather weatherObj = null;
		Location locationObj = null;
		Wind windObj = null;

		try {
			// Create a main JSON object to get Current Location Weather
			// information. and get relevant data.
			JSONObject mainObj = new JSONObject(json);

			// Create Child Object to get location coordinates
			JSONObject coord = mainObj.getJSONObject("coord");
			float longitude = Float.parseFloat(coord.getString("lon"));
			float latitude = Float.parseFloat(coord.getString("lat"));

			// Create Child Object to get country , sunset and sunset. and get
			// relevant data.
			JSONObject sys = mainObj.getJSONObject("sys");
			String country = sys.getString("country");
			long sunrise = sys.optLong("sunrise");
			long sunset = sys.optLong("sunset");

			// Create Child Object to get weather descriptions and get relevant
			// data.
			JSONArray weather = mainObj.getJSONArray("weather");
			JSONObject weatherItem = weather.getJSONObject(0);
			String mainDescription = weatherItem.getString("main");
			String description = weatherItem.getString("description");

			// Create Child Object to get weather conditions. and get relevant
			// data.
			JSONObject main = mainObj.getJSONObject("main");
			float temperature = Float.parseFloat(main.getString("temp"));
			float pressure = Float.parseFloat(main.getString("pressure"));
			int humidity = main.getInt("humidity");
			float minTemperature = Float.parseFloat(main.getString("temp_min"));
			float maxTemperature = Float.parseFloat(main.getString("temp_max"));

			// Create a Child Object to get wind informations and get relevant
			// data.
			JSONObject wind = mainObj.getJSONObject("wind");
			float windSpeed = Float.parseFloat(wind.getString("speed"));
			float deg = Float.parseFloat(wind.getString("deg"));

			// get City Name
			String city_name = mainObj.getString("name");

			// Create a Location Object with Retrieved Data
			locationObj = new Location(latitude, longitude, country, city_name);
			windObj = new Wind(windSpeed, deg);
			weatherObj = new Weather(sunrise, sunset, mainDescription, description, temperature, pressure, humidity, minTemperature, maxTemperature, locationObj, windObj);
			

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weatherObj;
	}

}
