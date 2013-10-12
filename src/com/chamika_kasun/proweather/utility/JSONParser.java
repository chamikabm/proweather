package com.chamika_kasun.proweather.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.chamika_kasun.proweather.objects.City;
import com.chamika_kasun.proweather.objects.DayWeather;
import com.chamika_kasun.proweather.objects.HourlyWeather;
import com.chamika_kasun.proweather.objects.Location;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.objects.Wind;

/**
 * This class is used to retrieve data from the Weather API
 * @author Chamika
 *
 */
public class JSONParser {


	/**
	 * This methos is used to get the Current location weather informations
	 * @param json - It takes the url to retrieve data
	 * @return - Retuns a weather object with Current Location Weather Data
	 */
	public Weather getLocationWeather(String json) {

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
			String id  = weatherItem.getString("id");

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
					minTemperature, maxTemperature,id, locationObj, windObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// Return the Current Weather Object.
		return weatherObj;
	}

	/**
	 * This methos is used to get the Current location Hourly weather information
	 * @param json2 - It takes the url in String format
	 * @return - It reurns the Horly weather Information Object
	 */
	public HourlyWeather getLocationHorlyWeather(String json2) {

		HourlyWeather hwObj = null;

		try {
			// Create a JSON Object to get Current Day Hourly Weather
			// Information.
			JSONObject mainObj2 = new JSONObject(json2);

			// Create a JOSONArray to get All the Hourly Data in to an Array.
			JSONArray list = mainObj2.getJSONArray("list");

			// Create JSONObjects to Extract Infomation From the JSONArray.
			JSONObject obj12AM = list.getJSONObject(0);
			JSONObject main12AM = obj12AM.getJSONObject("main");
			float obj12AMTemarature = Float.parseFloat(main12AM.getString("temp"));

			JSONArray wetherIcon12AMArray = obj12AM.getJSONArray("weather");
			JSONObject wetherIcon12AM = wetherIcon12AMArray.getJSONObject(0);
			String obj12AMICON = wetherIcon12AM.getString("id");

			JSONObject obj3AM = list.getJSONObject(1);
			JSONObject main3AM = obj3AM.getJSONObject("main");
			float obj3AMTemarature = Float.parseFloat(main3AM.getString("temp"));
			JSONArray wetherIcon3AMArray = obj3AM.getJSONArray("weather");
			JSONObject wetherIcon3AM = wetherIcon3AMArray.getJSONObject(0);
			String obj3AMICON = wetherIcon3AM.getString("id");

			JSONObject obj6AM = list.getJSONObject(2);
			JSONObject main6AM = obj6AM.getJSONObject("main");
			float obj6AMTemarature = Float.parseFloat(main6AM.getString("temp"));
			JSONArray wetherIcon6AMArray = obj6AM.getJSONArray("weather");
			JSONObject wetherIcon6AM = wetherIcon6AMArray.getJSONObject(0);
			String obj6AMICON = wetherIcon6AM.getString("id");

			JSONObject obj9AM = list.getJSONObject(3);
			JSONObject main9AM = obj9AM.getJSONObject("main");
			float obj9AMTemarature = Float.parseFloat(main9AM.getString("temp"));
			JSONArray wetherIcon9AMArray = obj9AM.getJSONArray("weather");
			JSONObject wetherIcon9AM = wetherIcon9AMArray.getJSONObject(0);
			String obj9AMICON = wetherIcon9AM.getString("id");

			JSONObject obj12PM = list.getJSONObject(4);
			JSONObject main12PM = obj12PM.getJSONObject("main");
			float obj12PMTemarature = Float.parseFloat(main12PM.getString("temp"));
			JSONArray wetherIcon12PMArray = obj12PM.getJSONArray("weather");
			JSONObject wetherIcon12PM = wetherIcon12PMArray.getJSONObject(0);
			String obj12PMICON = wetherIcon12PM.getString("id");

			JSONObject obj3PM = list.getJSONObject(5);
			JSONObject main3PM = obj3PM.getJSONObject("main");
			float obj3PMTemarature = Float.parseFloat(main3PM.getString("temp"));
			JSONArray wetherIcon3PMArray = obj3PM.getJSONArray("weather");
			JSONObject wetherIcon3PM = wetherIcon3PMArray.getJSONObject(0);
			String obj3PMICON = wetherIcon3PM.getString("id");

			JSONObject obj6PM = list.getJSONObject(6);
			JSONObject main6PM = obj6PM.getJSONObject("main");
			float obj6PMTemarature = Float.parseFloat(main6PM.getString("temp"));
			JSONArray wetherIcon6PMArray = obj6PM.getJSONArray("weather");
			JSONObject wetherIcon6PM = wetherIcon6PMArray.getJSONObject(0);
			String obj6PMICON = wetherIcon6PM.getString("id");

			JSONObject obj9PM = list.getJSONObject(7);
			JSONObject main9PM = obj9PM.getJSONObject("main");
			float obj9PMTemarature = Float.parseFloat(main9PM.getString("temp"));
			JSONArray wetherIcon9PMArray = obj9PM.getJSONArray("weather");
			JSONObject wetherIcon9PM = wetherIcon9PMArray.getJSONObject(0);
			String obj9PMICON = wetherIcon9PM.getString("id");

			// Create a Hourly Weather Object to Pass the Relevant Data
			hwObj = new HourlyWeather(obj12AMTemarature, obj3AMTemarature,
					obj6AMTemarature, obj9AMTemarature, obj12PMTemarature,
					obj3PMTemarature, obj6PMTemarature, obj9PMTemarature,
					obj12AMICON, obj3AMICON, obj6AMICON, obj9AMICON,
					obj12PMICON, obj3PMICON, obj6PMICON, obj9PMICON);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Return the Hourly Weather Object
		return hwObj;
	}

	
	/**
	 * This method is used to retrieve forecast weather information 
	 * @param josn3 - It takes the url in String format
	 * @return - It returns the ArrayList<DayWeather> 
	 */
	public ArrayList<DayWeather> getSevendayForecast(String josn3) {

		DayWeather dayWeather = null;
		Wind wind = null;
		ArrayList<DayWeather> arrayList = new ArrayList<DayWeather>();
		;

		try {

			JSONObject mainObj3 = new JSONObject(josn3);

			JSONArray list = mainObj3.getJSONArray("list");

			for (int i = 0; i < list.length(); i++) {

				JSONObject object = list.getJSONObject(i);

				String day = object.getString("dt");

				JSONObject temp = object.getJSONObject("temp");
				float temparature = Float.parseFloat(temp.getString("day"));
				float maxTemparature = Float.parseFloat(temp.getString("max"));
				float minTemparature = Float.parseFloat(temp.getString("min"));

				float pressure = Float.parseFloat(object.getString("pressure"));
				int humidity = object.getInt("humidity");

				JSONArray wetherData = object.getJSONArray("weather");
				JSONObject weatherObj = wetherData.getJSONObject(0);
				String id = weatherObj.getString("id");
				String main = weatherObj.getString("main");
				String description = weatherObj.getString("description");
				String icon = weatherObj.getString("icon");

				float windSpeed = Float.parseFloat(object.getString("speed"));
				float deg = Float.parseFloat(object.getString("deg"));

				// Log.v("Band", "########################################");
				// Log.v("Day " + (i + 1), "Day : " + (i + 1));
				// Log.v("Date", "Date : " + day);
				// Log.v("Temparature", "Temparature : " + temparature);
				// Log.v("MaxTemparature", "MaxTemparature : " +
				// maxTemparature);
				// Log.v("MinTemparature", "MinTemparature : " +
				// minTemparature);
				// Log.v("Pressure", "Pressure : " + pressure);
				// Log.v("Humidity", "Humidity : " + humidity);
				//
				// Log.v("ID", "ID : " + id);
				// Log.v("Main", "Main : " + main);
				// Log.v("Description", "Description : " + description);
				// Log.v("Icon", "Icon : " + icon);
				// Log.v("Wind Speed", "Wind Speed : " + windSpeed);
				// Log.v("Degree", "Degree : " + deg);

				dayWeather = new DayWeather();

				String dayWeek = getDateStringCurrentTimeZone(Long
						.parseLong(day));
				Log.v("Day of the Week", "Day of the Week : " + dayWeek);

				dayWeather.setDay(dayWeek);
				dayWeather.setTemperature(temparature);
				dayWeather.setTemperatureMax(maxTemparature);
				dayWeather.setTemperatureMin(minTemparature);
				dayWeather.setHumidity(humidity);
				dayWeather.setPressure(pressure);
				dayWeather.setDescription(description);
				dayWeather.setIconCode(icon);

				wind = new Wind(windSpeed, deg);
				dayWeather.setWind(wind);
				arrayList.add(dayWeather);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	/**
	 * This methos is used to get the city weather 
	 * @param json4 -  It takes the url in String format
	 * @return - It retuns a City object 
	 */
	public City getCityWeather(String json4) {

		Log.v("Result City ", "Result City : " + json4);

		City cityObj = null;
		Location locationObj = null;
		Wind windObj = null;

		try {
			// Create a main JSON object to get Current Location Weather
			// information. and get relevant data.
			JSONObject mainObj = new JSONObject(json4);

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
			String iconCode = weatherItem.getString("icon");

			// Create Child Object to get weather conditions. and get relevant
			// data.
			JSONObject main = mainObj.getJSONObject("main");
			float temperature = Float.parseFloat(main.getString("temp"));
			float pressure = Float.parseFloat(main.getString("pressure"));
			int humidity = main.getInt("humidity");
			float minTemperature = Float.parseFloat(main.getString("temp_min"));
			minTemperature = getCorrectTemparatureInCelcius(minTemperature);
			float maxTemperature = Float.parseFloat(main.getString("temp_max"));
			maxTemperature =  getCorrectTemparatureInCelcius(maxTemperature);

			// Create a Child Object to get wind informations and get relevant
			// data.
			JSONObject wind = mainObj.getJSONObject("wind");
			float windSpeed = Float.parseFloat(wind.getString("speed"));
			float deg = Float.parseFloat(wind.getString("deg"));

			// get City Name
			String city_name = mainObj.getString("name");

			Log.v("Band", "########################################");
			Log.v("City", "City : " + city_name);
			Log.v("Temparature", "Temparature : " + temperature);
			Log.v("MaxTemparature", "MaxTemparature : " + maxTemperature);
			Log.v("MinTemparature", "MinTemparature : " + minTemperature);
			Log.v("Pressure", "Pressure : " + pressure);
			Log.v("Humidity", "Humidity : " + humidity);

			Log.v("Country", "Country : " + country);
			Log.v("Main", "Main : " + main);
			Log.v("Description", "Description : " + description);
			Log.v("Icon", "Icon : " + iconCode);
			Log.v("Wind Speed", "Wind Speed : " + windSpeed);
			Log.v("Degree", "Degree : " + deg);

			// Create a Location Object with Retrieved Data
			locationObj = new Location(latitude, longitude, country, city_name);
			windObj = new Wind(windSpeed, deg);
			cityObj = new City();

			cityObj.setDescription(description);
			cityObj.setHumidity(humidity);
			cityObj.setIconCode(iconCode);
			cityObj.setLocation(locationObj);
			cityObj.setMainDescription(mainDescription);
			cityObj.setPressure(pressure);
			cityObj.setTemperature(temperature);
			cityObj.setTemperatureMax(maxTemperature);
			cityObj.setTemperatureMin(minTemperature);
			cityObj.setWind(windObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// Return the Current Weather Object.
		return cityObj;
	}

	/**
	 * This method is used to get the correct temparature in Celcius
	 * @param temparature - It taked the Current Farenhiet temparature in float
	 * @return - It returns the current temparature in Celcius
	 */
	public int getCorrectTemparatureInCelcius(float temparature) {

		int temparetureCelcius;
		long number = (long) temparature;
		float fraction = temparature - number;

		if (fraction >= 0.5) {
			temparetureCelcius = (int) (Math.floor(temparature) - 272.15);

		} else {
			temparetureCelcius = (int) (Math.ceil(temparature) - 272.15);
		}

		return temparetureCelcius;

	}

	/**
	 * This method is used to get the relevant Day of the Week
	 * @param timestamp - It get the current time stamp in long format
	 * @return -  It returns the Day of the Week
	 */
	@SuppressLint("NewApi")
	public static String getDateStringCurrentTimeZone(long timestamp) {

		Calendar calendar = Calendar.getInstance();
		TimeZone t = TimeZone.getDefault();

		calendar.setTimeInMillis(timestamp * 1000);
		calendar.add(Calendar.MILLISECOND,
				t.getOffset(calendar.getTimeInMillis()));

		return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,
				Locale.US);
	}

}
