package com.chamika_kasun.proweather;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.chamika_kasun.proweather.objects.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;

/**
 * This Class is Used to Drow the Google Map and Deal with Functions( i.e Add Markers, Get Touch Details)
 * @author Chamika
 * 		   E-mail :  kasun.chamika@gmail.com
 */

public class GoogleMapActivity extends MapActivity {

	//Google Map
	private GoogleMap googleMap;

	// GPSTracker class
	GPSTracker gps;

	// Map View
	MapView mapView;
	
	//Button for Get Selected Location Weather
	Button getLocationWeather;

	// Data
	int radius;
	LatLng position;
	Location locatioUpdated;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initalize the Google Map
		setContentView(R.layout.activity_main);
		
		//Initialize the Location Variable
		locatioUpdated = new Location();

		//Initialize the Google Map Library
		LocationLibrary.initialiseLibrary(getBaseContext(),"com.chamika_kasun.googlemaps");

		try {
			//Call to Loading map
			initilizeMap();

			
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			//Get the User Current Location Lattitude and the Longitude
			LocationInfo latestInfo = new LocationInfo(getBaseContext());
			locatioUpdated.setLatitude(latestInfo.lastLat);
			locatioUpdated.setLongitude(latestInfo.lastLong);

			//Add a Marker on User Current Location
			final Marker marker1 = googleMap.addMarker(new MarkerOptions().position(new LatLng(locatioUpdated.getLatitude(), locatioUpdated.getLongitude())));
			marker1.setDraggable(true);

			/**
			 * This method is Used to Get Map Location Details on Map Clicks
			 */
			googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng point) {

					marker1.setPosition(point);
					position = marker1.getPosition();
					locatioUpdated.setLatitude((float)position.latitude);
					locatioUpdated.setLongitude((float) position.longitude);
					
					//Used to Check The Clicked Location Details
					Log.v("Location Change 1 ", "Location Change 1 :"+ locatioUpdated.getLatitude() + "" + locatioUpdated.getLongitude());
					
					//Used to make a Toast to Indicate Locaiton Details on the MAp
					Toast.makeText((Context) GoogleMapActivity.this,"Latitude :" + locatioUpdated.getLatitude()+ "Longitude : " + locatioUpdated.getLongitude(),Toast.LENGTH_SHORT).show();

					Geocoder gcd = new Geocoder(GoogleMapActivity.this, Locale.getDefault());
					List<Address> addresses = null;
					try {
						addresses = gcd.getFromLocation(locatioUpdated.getLatitude(), locatioUpdated.getLongitude(), 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (addresses.size() > 0) {
						//To View the User Clicked Location City and the Country
						locatioUpdated.setCity(addresses.get(0).getLocality());
						Log.v("Location | City 2", "City 2 : "+ locatioUpdated.getCity() );
						MarkerOptions marker = new MarkerOptions().position(new LatLng(locatioUpdated.getLatitude(), locatioUpdated.getLongitude())).title(""+ addresses.get(0).getLocality());
						locatioUpdated.setCountry(addresses.get(0).getCountryName());
						Log.v("Location | Country 2", "Country 2 : "+locatioUpdated.getCity() );
					}

				}
			});	
			
			getLocationWeather = (Button) findViewById(R.id.bGetLocationWeather);
			getLocationWeather.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Bundle bundleLocation = new Bundle();
					
					bundleLocation.putString("Lattitude", String.valueOf(locatioUpdated.getLatitude()));
					bundleLocation.putString("Longitude", String.valueOf(locatioUpdated.getLongitude()));
					bundleLocation.putString("City", locatioUpdated.getCity());
					bundleLocation.putString("Country", locatioUpdated.getCountry());					
					
					Intent intent = getIntent();
					intent.putExtras(bundleLocation);
					
					setResult(Activity.RESULT_OK, intent);
					
					finish();
					
				}
			});
			
			// Create Instance of GPSTracker to get Location
			gps = new GPSTracker(GoogleMapActivity.this);

			// Get Location Latitude and Longitude
			double lat= gps.getLatitude();
			double longt = gps.getLongitude();

			// Get City Name of Location
			Geocoder gcd = new Geocoder(this, Locale.getDefault());
			List<Address> addresses = gcd.getFromLocation(lat, longt,1);
			
			if (addresses.size() > 0) {
				
				System.out.println("City : " + addresses.get(0).getLocality());
				System.out.println("Country : "+ addresses.get(0).getCountryName());
				
				Log.v("Location | City", "City : "+ addresses.get(0).getLocality());
				Log.v("Location | Country", "Country : "+ addresses.get(0).getCountryName());
				
			}

			// create marker
			MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, longt)).title("Your are Here!");

			// Changing marker icon
			marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

			// adding marker
			googleMap.addMarker(marker);

			// Add a Camera Position
			CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat, longt)).zoom(12).build();

			// Move camera with an Animation
			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		
		if (googleMap == null) {
			
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				
				Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
		initilizeMap();
		
	}

	@Override
	protected boolean isRouteDisplayed() {

		return false;
		
	}
	

}
