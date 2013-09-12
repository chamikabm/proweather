package com.chamika_kasun.proweather;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

public class GoogleMapActivity extends MapActivity {

	// Google Map
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
	Double lattitude = 21.0;
	Double longitude = 7.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

			
		
		LocationLibrary.initialiseLibrary(getBaseContext(),
				"com.chamika_kasun.googlemaps");

		try {
			// Loading map
			initilizeMap();

			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			LocationInfo latestInfo = new LocationInfo(getBaseContext());
			lattitude = (double) latestInfo.lastLat;
			longitude = (double) latestInfo.lastLong;

			final Marker marker1 = googleMap.addMarker(new MarkerOptions()
					.position(new LatLng(lattitude, longitude)));
			marker1.setDraggable(true);

			googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng point) {
					// TODO Auto-generated method stub

					marker1.setPosition(point);
					position = marker1.getPosition();
					lattitude = position.latitude;
					longitude = position.longitude;
					Log.v("Location Change 1 ", "Location Change 1 :"
							+ lattitude.toString() + "" + longitude.toString());
					Toast.makeText(
							(Context) GoogleMapActivity.this,
							"Latitude :" + lattitude.toString()
									+ "Longitude : " + longitude.toString(),
							Toast.LENGTH_SHORT).show();

					Geocoder gcd = new Geocoder(GoogleMapActivity.this, Locale
							.getDefault());
					List<Address> addresses = null;
					try {
						addresses = gcd
								.getFromLocation(lattitude, longitude, 1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (addresses.size() > 0) {
						Log.v("Location | City", "City 2 : "
								+ addresses.get(0).getLocality());
						MarkerOptions marker = new MarkerOptions().position(
								new LatLng(lattitude, longitude)).title(""+ addresses.get(0).getLocality());
						Log.v("Location | Country", "Country 2 : "
								+ addresses.get(0).getCountryName());
					}

				}
			});

			
			getLocationWeather = (Button) findViewById(R.id.bGetLocationWeather);
			getLocationWeather.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Bundle bundleLocation = new Bundle();
					bundleLocation.putString("Lattitude", lattitude.toString());
					bundleLocation.putString("Longitude", longitude.toString());
					
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
			List<Address> addresses = gcd.getFromLocation(lat, longt,
					1);
			if (addresses.size() > 0) {
				System.out.println("City : " + addresses.get(0).getLocality());
				System.out.println("Country : "
						+ addresses.get(0).getCountryName());
				Log.v("Location | City", "City : "
						+ addresses.get(0).getLocality());
				Log.v("Location | Country", "Country : "
						+ addresses.get(0).getCountryName());
			}

			// create marker
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(lat, longt)).title("Your are Here!");

			// Changing marker icon
			marker.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

			// adding marker
			googleMap.addMarker(marker);

			// Add a Camera Position
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(lat, longt)).zoom(12).build();

			// Move camera with an Animation
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
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
		// TODO Auto-generated method stub
		return false;
	}

}
