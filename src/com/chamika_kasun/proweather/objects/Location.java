package com.chamika_kasun.proweather.objects;

/**
 * This class is used to a Location Information
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class Location {

	private float latitude;
	private float longitude;
	private String country;
	private String city;
	private int id;

	public Location(float latitude, float longitude, String country, String city) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.city = city;

	}
	
	public Location(){
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
