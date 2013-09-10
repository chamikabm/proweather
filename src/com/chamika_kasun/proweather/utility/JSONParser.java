package com.chamika_kasun.proweather.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.chamika_kasun.proweather.objects.HourlyWeather;
import com.chamika_kasun.proweather.objects.Location;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.objects.Wind;

public class JSONParser {
	
	
//Method for get Location Weather Updates
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
			weatherObj = new Weather(sunrise, sunset, mainDescription,
					description, temperature, pressure, humidity,
					minTemperature, maxTemperature, locationObj, windObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Return the Current Weather Object.
		return weatherObj;
	}

	//Method for get Current Location Hourly Weather Information
	public static HourlyWeather getLocationHorlyWeather(String json2) {

		HourlyWeather hwObj = null;

		try {
			// Create a JSON Object to get Current Day Hourly Weather
			// Information.
			JSONObject mainObj2 = new JSONObject(json2);

			// Create a JOSONArray to get All the Hourly Data in to an Array.
			JSONArray list = mainObj2.getJSONArray("list");

			//Create JSONObjects to Extract Infomation From the JSONArray.
			JSONObject obj12AM = list.getJSONObject(0);
			JSONObject main12AM = obj12AM.getJSONObject("main");
			float obj12AMTemarature = Float.parseFloat(main12AM.getString("temp"));
			
			JSONArray wetherIcon12AMArray = obj12AM.getJSONArray("weather");
			JSONObject wetherIcon12AM = wetherIcon12AMArray.getJSONObject(0);
			String obj12AMICON = wetherIcon12AM.getString("icon");

			JSONObject obj3AM = list.getJSONObject(1);
			JSONObject main3AM = obj3AM.getJSONObject("main");
			float obj3AMTemarature = Float.parseFloat(main3AM.getString("temp"));
			JSONArray wetherIcon3AMArray = obj3AM.getJSONArray("weather");
			JSONObject wetherIcon3AM = wetherIcon3AMArray.getJSONObject(0);
			String obj3AMICON = wetherIcon3AM.getString("icon");

			JSONObject obj6AM = list.getJSONObject(2);
			JSONObject main6AM = obj6AM.getJSONObject("main");
			float obj6AMTemarature = Float.parseFloat(main6AM.getString("temp"));
			JSONArray wetherIcon6AMArray = obj6AM.getJSONArray("weather");
			JSONObject wetherIcon6AM = wetherIcon6AMArray.getJSONObject(0);
			String obj6AMICON = wetherIcon6AM.getString("icon");

			JSONObject obj9AM = list.getJSONObject(3);
			JSONObject main9AM = obj9AM.getJSONObject("main");
			float obj9AMTemarature = Float.parseFloat(main9AM.getString("temp"));
			JSONArray wetherIcon9AMArray = obj9AM.getJSONArray("weather");
			JSONObject wetherIcon9AM = wetherIcon9AMArray.getJSONObject(0);
			String obj9AMICON = wetherIcon9AM.getString("icon");


			JSONObject obj12PM = list.getJSONObject(4);
			JSONObject main12PM = obj12PM.getJSONObject("main");
			float obj12PMTemarature = Float.parseFloat(main12PM.getString("temp"));
			JSONArray wetherIcon12PMArray = obj12PM.getJSONArray("weather");
			JSONObject wetherIcon12PM = wetherIcon12PMArray.getJSONObject(0);
			String obj12PMICON = wetherIcon12PM.getString("icon");

			JSONObject obj3PM = list.getJSONObject(5);
			JSONObject main3PM = obj3PM.getJSONObject("main");
			float obj3PMTemarature = Float.parseFloat(main3PM.getString("temp"));
			JSONArray wetherIcon3PMArray = obj3PM.getJSONArray("weather");
			JSONObject wetherIcon3PM = wetherIcon3PMArray.getJSONObject(0);
			String obj3PMICON = wetherIcon3PM.getString("icon");

			JSONObject obj6PM = list.getJSONObject(6);
			JSONObject main6PM = obj6PM.getJSONObject("main");
			float obj6PMTemarature = Float.parseFloat(main6PM.getString("temp"));
			JSONArray wetherIcon6PMArray = obj6PM.getJSONArray("weather");
			JSONObject wetherIcon6PM = wetherIcon6PMArray.getJSONObject(0);
			String obj6PMICON = wetherIcon6PM.getString("icon");

			JSONObject obj9PM = list.getJSONObject(7);
			JSONObject main9PM = obj9PM.getJSONObject("main");
			float obj9PMTemarature = Float.parseFloat(main9PM.getString("temp"));
			JSONArray wetherIcon9PMArray = obj9PM.getJSONArray("weather");
			JSONObject wetherIcon9PM = wetherIcon9PMArray.getJSONObject(0);
			String obj9PMICON = wetherIcon9PM.getString("icon");

			//Create a Hourly Weather Object to Pass the Relevant Data
			hwObj = new HourlyWeather(obj12AMTemarature, obj3AMTemarature,
					obj6AMTemarature, obj9AMTemarature, obj12PMTemarature,
					obj3PMTemarature, obj6PMTemarature, obj9PMTemarature,
					obj12AMICON, obj3AMICON, obj6AMICON, obj9AMICON,
					obj12PMICON, obj3PMICON, obj6PMICON, obj9PMICON);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Return the Hourly Weather Object
		return hwObj;
	}
	
	
}
