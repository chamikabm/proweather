package com.chamika_kasun.proweather.objects;

public class Location {

	private float latitude;
	private float longitude;
	private String country;
	private String city;

	public Location(float latitude, float longitude, String country, String city) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.city = city;

	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

}
