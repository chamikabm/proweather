package com.chamika_kasun.proweather;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.HourlyWeather;
import com.chamika_kasun.proweather.objects.Location;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Class is Used to Retrive and Show data of the GPS Location Weather Data
 * @author Chamika
 * E-mail :  kasun.chamika@gmail.com
 */


public class Home extends BaseFragment {
	
	
	//Crete a Database Variable to Store Favouriets Data
	private ProWeatherDataBase database;

	// Constant for Map Activity Reuqest Code
	private final static int MAP_ACTIVITY_REQUEST_CODE = 0;

	//Varable to hold Location lattiude and longitude
	private Location locationCurrent;

	// Define all the TextViews that in the home XML
	private TextView updateTime, location, time, dayWord, day, temperature,
			temperatureMax, temperatureMin, humidity, sunrise, sunset,
			windSpeed, windDirection, pressure, tvTime12AMValue,
			tvTime3AMValue, tvTime6AMValue, tvTime9AMValue, tvTime12PMValue,
			tvTime3PMValue, tvTime6PMValue, tvTime9PMValue;

	// Define location Button
	private ImageView locationButton,weatherIcon,weatherIcon12AM,weatherIcon3AM,weatherIcon6AM,weatherIcon9AM,weatherIcon12PM,weatherIcon3PM,weatherIcon6PM,weatherIcon9PM;
	
	// timer to update UI
	private Timer uiUpdater; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

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
		
		//Setting for Weather Icon
		weatherIcon = (ImageView) convertView.findViewById(R.id.ivWeatherCondition);
		weatherIcon12AM = (ImageView) convertView.findViewById(R.id.iv12AM);
		weatherIcon3AM = (ImageView) convertView.findViewById(R.id.iv3AM);
		weatherIcon6AM = (ImageView) convertView.findViewById(R.id.iv6AM);
		weatherIcon9AM = (ImageView) convertView.findViewById(R.id.iv9AM);
		weatherIcon12PM = (ImageView) convertView.findViewById(R.id.iv12PM);
		weatherIcon3PM = (ImageView) convertView.findViewById(R.id.iv3PM);
		weatherIcon6PM = (ImageView) convertView.findViewById(R.id.iv6PM);
		weatherIcon9PM = (ImageView) convertView.findViewById(R.id.iv9PM);

		// Setting for Hourly Related TextViews.
		tvTime12AMValue = (TextView) convertView.findViewById(R.id.tvTime12AMValue);
		tvTime3AMValue = (TextView) convertView.findViewById(R.id.tvTime3AMValue);
		tvTime6AMValue = (TextView) convertView.findViewById(R.id.tvTime6AMValue);
		tvTime9AMValue = (TextView) convertView.findViewById(R.id.tvTime9AMValue);
		tvTime12PMValue = (TextView) convertView.findViewById(R.id.tvTime12PMValue);
		tvTime3PMValue = (TextView) convertView.findViewById(R.id.tvTime3PMValue);
		tvTime6PMValue = (TextView) convertView.findViewById(R.id.tvTime6PMValue);
		tvTime9PMValue = (TextView) convertView.findViewById(R.id.tvTime9PMValue);
		
		
		
		//Create Database to Store Favouriets Data
		database = new ProWeatherDataBase(getActivity());	
		
		//Initialize the location
		locationCurrent = new Location();

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

			/**
			 * This mthos is used to get the clicked action and according to that get the location details.
			 */
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
		
		
		//Code Start of Alarm
		
		 // setup an alarm
		// alarm will be triggered @ 11.00 p.m. everyday
//		Calendar updateTime = Calendar.getInstance();
//		updateTime.set(Calendar.HOUR_OF_DAY, 00);
//		updateTime.set(Calendar.MINUTE,24);
//			
//		Intent intent = new Intent(getActivity(), AlarmReceiver.class);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		
//		AlarmManager alarms = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
//		alarms.setRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
		
		//Code End of Alarm
		
		//Set language for the Notification
		

		// Code Sniffet for the Popup Window End

		// ################################################################################

		// This Code is not Working now. But Worked Earlier
//		 LocationInfo latestInfo = new LocationInfo((Context) getActivity());
//		
//		 lattitude = (double) latestInfo.lastLat;
//		 Log.v("Latitude", "Latitude : "+lattitude);
//		 longitude = (double) latestInfo.lastLong;
//		 Log.v("Longitude", "Longitude : "+longitude);

		// ((FragmentsActivity)getActivity()).setLattitude((double)
		// latestInfo.lastLat);
		// ((FragmentsActivity)getActivity()).setLattitude((double)
		// latestInfo.lastLong);

		// #################################################################################

		GPSTracker gps = new GPSTracker((Context) getActivity());

		// Get Location Latitude and Longitude
		locationCurrent.setLatitude((float)gps.getLatitude());
		locationCurrent.setLongitude((float)gps.getLongitude());

		Geocoder gcd = new Geocoder(getActivity().getBaseContext(),Locale.getDefault());
		List<Address> addresses = null;
		try {
			addresses = gcd.getFromLocation(locationCurrent.getLatitude(), locationCurrent.getLongitude(), 1);
		} catch (IOException e) {

			e.printStackTrace();

			Toast.makeText((Context) getActivity(),"Data Connection is Not Available", Toast.LENGTH_SHORT).show();

			return convertView;
		}
		if (addresses.size() > 0) {

			locationCurrent.setCity(addresses.get(0).getLocality());
			locationCurrent.setCountry( addresses.get(0).getCountryName());
			Log.v("Location | City", "City: " + locationCurrent.getCity() );
			Log.v("Location | Country", "Country: "+ locationCurrent.getCountry());
			
		}

		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				Log.v("Timer Execute", "Timer Executed");
				runBackgroudTasks(locationCurrent.getLatitude(), locationCurrent.getLongitude());
				
			}
		};
		
		uiUpdater = new Timer();
		uiUpdater.scheduleAtFixedRate(timerTask, 0, 60000);
		
		return convertView;

	}

	/**
	 * This method is used to Update current location weather on the Home Screen
	 */
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
			windSpeed.setText(String.valueOf(weatherInfo.getWindSpeed())+ "mph");
			windDirection.setText(String.valueOf(weatherInfo.getDeg()));
			location.setText(weatherInfo.getCity());
			humidity.setText(String.valueOf(weatherInfo.getHumidity() + "%"));
			pressure.setText(String.valueOf(weatherInfo.getPressure()));
			
			String wid = weatherInfo.getId();
			
			Log.v("Weather Icon Code", "Weather Icon Code :"+wid);
			weatherIcon.setImageResource(getDrawable(wid));			
			

		} else {

			// Used to display an Error message if something went wrong while  recieving the data
			Toast.makeText((Context) getActivity(), "Error occured",Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * This method is used to update the Hourly Weather information on the Home screen.
	 */
	@Override
	public void onSubTaskFinished(String result) {
		
		super.onSubTaskFinished(result);

		if (result != null && result.length() > 0) {

			HourlyWeather hwinfo = jsonParser.getLocationHorlyWeather(result);

			// Assign valuse to TextViews.
			tvTime12AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime12AMValue()))+ "\u2103");
			String id12AM = hwinfo.getTvTime12AMICON();
			Log.v("Hourly Weather Icon : ", "12AM Hourly Weather Icon : "+id12AM);
			weatherIcon12AM.setImageResource(getDrawable(id12AM));
			// Log.v("12AM ", String.valueOf(hwinfo.getTvTime12AMValue()));
			
			tvTime3AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime3AMValue()))+ "\u2103");
			String id3AM = hwinfo.getTvTime3AMICON();
			Log.v("Hourly Weather Icon : ", "3AM Hourly Weather Icon : "+id3AM);
			weatherIcon3AM.setImageResource(getDrawable(id3AM));
			// Log.v("3AM ", String.valueOf(hwinfo.getTvTime3AMValue()));
			
			tvTime6AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6AMValue()))+ "\u2103");
			String id6AM = hwinfo.getTvTime6AMICON();
			Log.v("Hourly Weather Icon : ", "6AM Hourly Weather Icon : "+id6AM);
			weatherIcon6AM.setImageResource(getDrawable(id6AM));
			// Log.v("6AM ", String.valueOf(hwinfo.getTvTime6AMValue()));
			
			tvTime9AMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime9AMValue()))+ "\u2103");
			String id9AM = hwinfo.getTvTime9AMICON();
			Log.v("Hourly Weather Icon : ", "9AM Hourly Weather Icon : "+id9AM);
			weatherIcon9AM.setImageResource(getDrawable(id9AM));
			// Log.v("9AM ", String.valueOf(hwinfo.getTvTime9AMValue()));
			
			tvTime12PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime12PMValue()))+ "\u2103");
			String id12PM = hwinfo.getTvTime12PMICON();
			Log.v("Hourly Weather Icon : ", "12PM Hourly Weather Icon : "+id12PM);
			weatherIcon12PM.setImageResource(getDrawable(id12PM));
			// Log.v("12PM", String.valueOf(hwinfo.getTvTime12PMValue()));
			
			tvTime3PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime3PMValue()))+ "\u2103");
			String id3PM = hwinfo.getTvTime3PMICON();
			Log.v("Hourly Weather Icon : ", "3PM Hourly Weather Icon : "+id3PM);
			weatherIcon3PM.setImageResource(getDrawable(id3PM));
			// Log.v("3PM ", String.valueOf(hwinfo.getTvTime3PMValue()));
			
			tvTime6PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6PMValue()))+ "\u2103");
			String id6PM = hwinfo.getTvTime6PMICON();
			Log.v("Hourly Weather Icon : ", "6PM Hourly Weather Icon : "+id6PM);
			weatherIcon6PM.setImageResource(getDrawable(id6PM));
			// Log.v("6PM ", String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime6PMValue()));
			
			tvTime9PMValue.setText(String.valueOf(getCorrectTemparatureInCelcius(hwinfo.getTvTime9PMValue()))+ "\u2103");
			String id9PM = hwinfo.getTvTime9PMICON();
			Log.v("Hourly Weather Icon : ", "9PM Hourly Weather Icon : "+id9PM);
			weatherIcon9PM.setImageResource(getDrawable(id9PM));
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
			dayText = "Sunday";
			break;
			
		case 2:
			dayText = "Monday";
			break;

		case 3:
			dayText = "Tuesday";
			break;

		case 4:
			dayText = "Wednesday";
			break;

		case 5:
			dayText = "Thursday";
			break;

		case 6:
			dayText = "Friday";
			break;

		case 7:
			dayText = "Saturday";
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

		updateTime.setText("Updated Time : " + hour + " : " + sminute + ""+ txt);

	}

	/**
	 * This method is used to update the location weather 
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == MAP_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				

				Location locationNew = new Location();

				Bundle bundleLocation = new Bundle();
				bundleLocation = data.getExtras();

				locationNew.setLatitude(Float.parseFloat(bundleLocation.getString("Lattitude")));
				locationNew.setLongitude( Float.parseFloat( bundleLocation.getString("Longitude")));
				locationNew.setCity( bundleLocation.getString("City"));
				locationNew.setCountry(bundleLocation.getString("Country"));
				
				locationCurrent = locationNew;

				executeBackgroundTask(Constants.LOCATION_WEATHER_URL_ON_COORDINATES + "lat="+ locationNew.getLatitude() + "&lon=" +locationNew.getLongitude(), true);
				executeBackgroundTask(Constants.LOCATION_WEATHER_HOURLY_URL_ON_COORDINATES+ "lat=" + locationNew.getLatitude() + "&lon="+locationNew.getLongitude(), false);

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
	
	/**
	 * This method is used to Create the Homw menu
	 */

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.menu_home, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	

	/**
	 * This methos is used to add a save button to home screen
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {	
		
		if(item.getItemId() == R.id.action_add_to_favourits){
			
			try {
				database.open();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			boolean isExisting = database.isExisting(locationCurrent.getCity(), locationCurrent.getCountry());

			if(isExisting){
				Toast.makeText((Context) getActivity(), "It is Already in the Database.",Toast.LENGTH_SHORT).show();
			}else{
				
				database.createEntrey(locationCurrent);
				
				Toast.makeText((Context) getActivity(), "Data Base Create Call Executed.",Toast.LENGTH_SHORT).show();
				
				ArrayList<Location> locaznz = new ArrayList<Location>();
				
				locaznz = database.getData();
				
				if(locaznz.size()>0){
				
					for(int i = 0; i<locaznz.size();i++){
						
						Toast.makeText((Context) getActivity(), "For Loop"+i,Toast.LENGTH_SHORT).show();
						
						Log.v("Location"+(i+1)+" ID", "Location"+i+" ID : "+locaznz.get(i).getId());
						Log.v("Location"+(i+1)+" City Name", "Location"+i+" City Name : "+locaznz.get(i).getCity());
						Log.v("Location"+(i+1)+" Country Name", "Location"+i+" Country Name : "+locaznz.get(i).getCountry());
						Log.v("Location"+(i+1)+" Longitude", "Location"+i+" Longitude : "+locaznz.get(i).getLongitude());
						Log.v("Location"+(i+1)+" Lattitude", "Location"+i+" Lattitude : "+locaznz.get(i).getLatitude());
						
					}
				}
				
			}
			
			database.close();
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This method is used to update the saved configuration 
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		//Log.v("Home", "On Destroy View");
		
		// disabling ui updater
		uiUpdater.cancel();
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
			Log.v("Weather Icon Code", "Weather Icon Code : 500");
			returnDrwanleId = R.drawable.q500;
			break;
			
		case 501: //moderate rain
			Log.v("Weather Icon Code", "Weather Icon Code : 501");
			returnDrwanleId = R.drawable.q501;
			break;
			
		case 502: //heavy intensity rain
			Log.v("Weather Icon Code", "Weather Icon Code : 502");
			returnDrwanleId = R.drawable.q502;
			break;

		case 503: //very heavy rain
			Log.v("Weather Icon Code", "Weather Icon Code : 503");
			returnDrwanleId = R.drawable.q503;
			break;

		case 504: //extreme rain
			Log.v("Weather Icon Code", "Weather Icon Code : 504");
			returnDrwanleId = R.drawable.q504;
			break;
			
		case 511: //freezing rain
			Log.v("Weather Icon Code", "Weather Icon Code : 511");
			returnDrwanleId = R.drawable.q511;
			break;
			
		case 520: //light intensity shower rain
			Log.v("Weather Icon Code", "Weather Icon Code : 520");
			returnDrwanleId = R.drawable.q520;
			break;
			
		case 521: //shower rain
			Log.v("Weather Icon Code", "Weather Icon Code : 521");
			returnDrwanleId = R.drawable.q521;
			break;
			
		case 522: //heavy intensity shower rain
			Log.v("Weather Icon Code", "Weather Icon Code : 522");
			returnDrwanleId = R.drawable.q522;
			break;
			
		case 800: //Sky is Clear
			Log.v("Weather Icon Code", "Weather Icon Code : 800");
			returnDrwanleId = R.drawable.q800;
			break;

		case 801: //few clouds
			Log.v("Weather Icon Code", "Weather Icon Code : 801");
			returnDrwanleId = R.drawable.q801;
			break;

		case 802: //few clouds
			Log.v("Weather Icon Code", "Weather Icon Code : 802");
			returnDrwanleId = R.drawable.q802;
			break;
			
		case 803: //scattered clouds 
			Log.v("Weather Icon Code", "Weather Icon Code : 803");
			returnDrwanleId = R.drawable.q803;
			break;
			
		case 804: //broken clouds  
			Log.v("Weather Icon Code", "Weather Icon Code : 804");
			returnDrwanleId = R.drawable.q804;
			break;
			
		case 805: //overcast clouds  
			Log.v("Weather Icon Code", "Weather Icon Code : 805");
			returnDrwanleId = R.drawable.q805;
			break;
		}
		
		
		return returnDrwanleId;
	}
	
	
}
