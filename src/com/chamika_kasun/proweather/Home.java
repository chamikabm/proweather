package com.chamika_kasun.proweather;

import java.io.IOException;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.HourlyWeather;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Class is Used to Retrive and Show data of the GPS Location Weather Data
 * @author Chamika
 * 		   E-mail :  kasun.chamika@gmail.com
 */


public class Home extends BaseFragment {

	// Constant for Map Activity Reuqest Code
	private final static int MAP_ACTIVITY_REQUEST_CODE = 0;

	//Varable to hold Location lattiude and longitude
	private double lattitude;
	private double longitude;

	// Define all the TextViews that in the home XML
	TextView updateTime, location, time, dayWord, day, temperature,
			temperatureMax, temperatureMin, humidity, sunrise, sunset,
			windSpeed, windDirection, pressure, tvTime12AMValue,
			tvTime3AMValue, tvTime6AMValue, tvTime9AMValue, tvTime12PMValue,
			tvTime3PMValue, tvTime6PMValue, tvTime9PMValue;

	// Define location Button
	ImageView locationButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		final View convertView = inflater.inflate(R.layout.home, container,false);
		
		setHasOptionsMenu(true);

		// Set all the TextView with the XML

		// Setting for Current Weather Related TextViews.
		updateTime = (TextView) convertView.findViewById(R.id.tvUpdateTime);
		location = (TextView) convertView.findViewById(R.id.tvLocation);
		time = (TextView) convertView.findViewById(R.id.tvTimeCurrent);
		dayWord = (TextView) convertView.findViewById(R.id.tvDayWord);
		day = (TextView) convertView.findViewById(R.id.tvDay);
		temperature = (TextView) convertView.findViewById(R.id.tvTemparature);
		temperatureMin = (TextView) convertView.findViewById(R.id.tvTempMinValue);
		temperatureMax = (TextView) convertView.findViewById(R.id.tvTempMaxValue);
		humidity = (TextView) convertView.findViewById(R.id.tvHumidityValue);
		sunrise = (TextView) convertView.findViewById(R.id.tvSunriseValue);
		sunset = (TextView) convertView.findViewById(R.id.tvSunsetValue);
		windSpeed = (TextView) convertView.findViewById(R.id.tvWindValue);
		windDirection = (TextView) convertView.findViewById(R.id.tvWindDirectionValue);
		pressure = (TextView) convertView.findViewById(R.id.tvPressureValue);

		// Setting for Hourly Related TextViews.
		tvTime12AMValue = (TextView) convertView.findViewById(R.id.tvTime12AMValue);
		tvTime3AMValue = (TextView) convertView.findViewById(R.id.tvTime3AMValue);
		tvTime6AMValue = (TextView) convertView.findViewById(R.id.tvTime6AMValue);
		tvTime9AMValue = (TextView) convertView.findViewById(R.id.tvTime9AMValue);
		tvTime12PMValue = (TextView) convertView.findViewById(R.id.tvTime12PMValue);
		tvTime3PMValue = (TextView) convertView.findViewById(R.id.tvTime3PMValue);
		tvTime6PMValue = (TextView) convertView.findViewById(R.id.tvTime6PMValue);
		tvTime9PMValue = (TextView) convertView.findViewById(R.id.tvTime9PMValue);

		// Create a Timer
		final Handler h = new Handler();
		h.post(new Runnable() {
			@Override
			public void run() {
				setTimeAndDate();
				h.postDelayed(this, 1000);
			}
		});

		// Set Update Time
		setUpdateTime();

		// Code Sniffet for the Popup Window Start
		locationButton = (ImageView) convertView.findViewById(R.id.ivLocation);

		locationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String[] choices = new String[] {"Using Google Map","Manual Input"};
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				alert.setTitle("Get Location");
				alert.setItems(choices, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

						switch (which) {
						case 0:
							// Using google maps
							startActivityForResult(new Intent((Context) getActivity(),GoogleMapActivity.class),MAP_ACTIVITY_REQUEST_CODE);

							break;

						case 1:
							// Manual search
							final Dialog d = new Dialog((Context) getActivity());
							d.setContentView(R.layout.manualinputlocation);
							d.setTitle("Input Location");
							d.show();

							WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
							lp.copyFrom(d.getWindow().getAttributes());
							lp.width = WindowManager.LayoutParams.MATCH_PARENT;
							lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
							d.getWindow().setAttributes(lp);
							d.setCancelable(true);

							final EditText etCountry = (EditText) d.findViewById(R.id.etCountry);
							final EditText etCity = (EditText) d.findViewById(R.id.etCity);
							Button done = (Button) d.findViewById(R.id.bGetManulaInputData);
							done.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									
									d.dismiss();

									String city = etCity.getText().toString();
									// replaceAll("\\s+", ""); this methos is
									// use to remove white spaces
									// Between Country Names. Sri Lanka -->
									// SriLanka
									String country = etCountry.getText().toString().replaceAll("\\s+", "");

									executeBackgroundTask(Constants.LOCATION_WEATHER_URL + ""+ city + "," + country,true);
									executeBackgroundTask(Constants.LOCATION_WEATHER_HOURLY_URL+ "" + city + "," + country,false);
								}
							});

							break;
						}
					}
				});

				alert.show();

			}
		});

		// Code Sniffet for the Popup Window End

		// ################################################################################

		// This Code is not Working now. But Worked Earlier
		// LocationInfo latestInfo = new LocationInfo((Context) getActivity());
		//
		// lattitude = (double) latestInfo.lastLat;
		// Log.v("Latitude", "Latitude : "+lattitude);
		// longitude = (double) latestInfo.lastLong;
		// Log.v("Longitude", "Longitude : "+longitude);

		// ((FragmentsActivity)getActivity()).setLattitude((double)
		// latestInfo.lastLat);
		// ((FragmentsActivity)getActivity()).setLattitude((double)
		// latestInfo.lastLong);

		// #################################################################################

		GPSTracker gps = new GPSTracker((Context) getActivity());

		// Get Location Latitude and Longitude
		lattitude = gps.getLatitude();
		longitude = gps.getLongitude();

		Geocoder gcd = new Geocoder(getActivity().getBaseContext(),
				Locale.getDefault());
		List<Address> addresses = null;
		try {
			addresses = gcd.getFromLocation(lattitude, longitude, 1);
		} catch (IOException e) {

			e.printStackTrace();

			Toast.makeText((Context) getActivity(),"Data Connection is Not Available", Toast.LENGTH_SHORT).show();

			return convertView;
		}
		if (addresses.size() > 0) {

			Log.v("Location | City", "City: " + addresses.get(0).getLocality());
			Log.v("Location | Country", "Country: "+addresses.get(0).getCountryName());
		}

		runBackgroudTasks(lattitude, longitude);

		return convertView;

	}

	@Override
	public void onTaskFinished(String result) {
		super.onTaskFinished(result);

		Log.v("Result Check", result);

		if (result != null && result.length() > 0) {

			// Method Call to Set the Time and the Date.

			// Get the current Weather information through the JSON parse
			Weather weatherInfo = jsonParser.getLocationWeather(result);

			int temp, maxTemp, minTemp;

			// Convert Kelvin Temperature to Celcius
			temp = (int) (weatherInfo.getTemperature() - 272.15);

			// Assign Values to TextViews.
			temperature.setText(String.valueOf(temp) + "\u2103");

			minTemp = (int) (weatherInfo.getTemperatureMin() - 272.15);
			temperatureMin.setText(String.valueOf(minTemp) + "\u2103");

			maxTemp = (int) (weatherInfo.getTemperatureMax() - 272.15);
			temperatureMax.setText(String.valueOf(maxTemp) + "\u2103");

			humidity.setText(String.valueOf(weatherInfo.getHumidity()));
			sunrise.setText(Utils.convertInToTime(weatherInfo.getSunrise()));
			sunset.setText(Utils.convertInToTime(weatherInfo.getSunset()));
			windSpeed.setText(String.valueOf(weatherInfo.getWindSpeed())
					+ "kmph");
			windDirection.setText(String.valueOf(weatherInfo.getDeg()));
			location.setText(weatherInfo.getCity());
			humidity.setText(String.valueOf(weatherInfo.getHumidity() + "%"));
			pressure.setText(String.valueOf(weatherInfo.getPressure()));

		} else {

			// Used to display an Error message if something went wrong while  recieving the data
			Toast.makeText((Context) getActivity(), "Error occured",Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onSubTaskFinished(String result) {
		
		super.onSubTaskFinished(result);

		if (result != null && result.length() > 0) {

			HourlyWeather hwinfo = jsonParser.getLocationHorlyWeather(result);

			// Assign valuse to TextViews.
			tvTime12AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime12AMValue()))+ "\u2103");
			// Log.v("12AM ", String.valueOf(hwinfo.getTvTime12AMValue()));
			
			tvTime3AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime3AMValue()))+ "\u2103");
			// Log.v("3AM ", String.valueOf(hwinfo.getTvTime3AMValue()));
			
			tvTime6AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6AMValue()))+ "\u2103");
			// Log.v("6AM ", String.valueOf(hwinfo.getTvTime6AMValue()));
			
			tvTime9AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime9AMValue()))+ "\u2103");
			// Log.v("9AM ", String.valueOf(hwinfo.getTvTime9AMValue()));
			
			tvTime12PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime12PMValue()))+ "\u2103");
			// Log.v("12PM", String.valueOf(hwinfo.getTvTime12PMValue()));
			
			tvTime3PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime3PMValue()))+ "\u2103");
			// Log.v("3PM ", String.valueOf(hwinfo.getTvTime3PMValue()));
			
			tvTime6PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6PMValue()))+ "\u2103");
			// Log.v("6PM ", String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6PMValue()));
			
			tvTime9PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime9PMValue()))+ "\u2103");
			// Log.v("9PM ",String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime9PMValue()));
			
		} else {
			
			// Used to display an Error message if something went wrong while recieving the data
			Toast.makeText((Context) getActivity(), "Error occured",Toast.LENGTH_SHORT).show();
		}
	}


	/**
	 * This method is used to Obtain the Current Date and the Time
	 */
	private void setTimeAndDate() {

		// In built Java Function used to get the Current Date and the Time.
		Calendar c = Calendar.getInstance();

		String sYear = "" + c.get(Calendar.YEAR);
		String smonth = " " + c.get(Calendar.MONTH);
		String sday = " " + c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String sminute = "" + c.get(Calendar.MINUTE);
		String txt = "";
		int dayWeek = c.get(Calendar.DAY_OF_WEEK);
		String dayText = "";

		// Switch Case to Map the Relevant Date Name
		switch (dayWeek) {

		case 1:
			dayText = "Monday";
			break;

		case 2:
			dayText = "Tuesday";
			break;

		case 3:
			dayText = "Wednesday";
			break;

		case 4:
			dayText = "Thursday";
			break;

		case 5:
			dayText = "Friday";
			break;

		case 6:
			dayText = "Saturday";
			break;

		case 7:
			dayText = "Sunday";
			break;

		}

		// Used to Convert the 24Hr time to 12Hr System.
		if (hour > 12) {
			txt = "PM";
			hour = hour - 12;
		} else if (hour == 12) {
			txt = "PM";
		} else {
			txt = "AM";
		}

		// Set Text to Date and the Time TextViews
		dayWord.setText("" + dayText);
		time.setText(hour + ":" + sminute + "" + txt);
		day.setText(smonth + "/" + sday + "/" + sYear);

	}
	
	/**
	 * This method is use to Convert the Kelvin Temparature to Rounded Celcius
	 * @param temparature - This takes the Kelvin temapature in float
	 * @return - Returns the rounded Celcius Temparature
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
	 * This method is used to Convert the 24 Hour time to 12 Hour time
	 */
	public void setUpdateTime() {
		// In built Java Function used to get the Current Date and the Time.
		String txt = "";

		Calendar c = Calendar.getInstance();

		int hour = c.get(Calendar.HOUR_OF_DAY);
		String sminute = "" + c.get(Calendar.MINUTE);

		if (hour > 12) {
			txt = "PM";
			hour = hour - 12;
		} else if (hour == 12) {
			txt = "PM";
		} else {
			txt = "AM";
		}

		updateTime.setText("Updated Time : " + hour + " : " + sminute + ""
				+ txt);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == MAP_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {

				Bundle bundleLocation = new Bundle();
				bundleLocation = data.getExtras();

				String newLattitude = bundleLocation.getString("Lattitude");
				String newLongitude = bundleLocation.getString("Longitude");

				executeBackgroundTask(
						Constants.LOCATION_WEATHER_URL_ON_COORDINATES + "lat="+ newLattitude + "&lon=" +newLongitude, true);
				executeBackgroundTask(
						Constants.LOCATION_WEATHER_HOURLY_URL_ON_COORDINATES+ "lat=" + newLattitude + "&lon="+newLongitude, false);

			} else {
				Toast.makeText((Context) getActivity(), "Error occured",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * This method is used  to run the JSONParser to get Location Data
	 * @param lattitude2 - It takes the location lattitude in double 
	 * @param longitude2 - Ti takes the location longitude in double
	 */
	public void runBackgroudTasks(double lattitude2, double longitude2) {

		executeBackgroundTask(Constants.LOCATION_WEATHER_URL_ON_COORDINATES+ "lat=" + lattitude2 + "&lon=" + longitude2, true);
		executeBackgroundTask(Constants.LOCATION_WEATHER_HOURLY_URL_ON_COORDINATES + "lat="+ lattitude2 + "&lon=" + longitude2, false);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_home, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
