package com.chamika_kasun.proweather.objects;

/**
 * This class is used to Hold a any location weather information
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class Weather {

	private long sunrise;
	private long sunset;
	private String mainDescription;
	private String description;
	private float temperature;
	private float pressure;
	private int humidity;
	private float temperatureMin;
	private float temperatureMax;
	private Location location;
	private Wind wind;
	private float windSpeed;
	private float deg;
	private float latitude;
	private float longitude;
	private String country;
	private String city;
	private String id;


	public Weather(long sunrise, long sunset, String mainDescription,
			String description, float temperature, float pressure,
			int humidity, float temperatureMin, float temperatureMax, String id,
			Location location, Wind wind) {

		this.id =id;
		this.sunset = sunset;
		this.sunrise = sunrise;
		this.mainDescription = mainDescription;
		this.description = description;
		this.temperature = temperature;
		this.pressure = pressure;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
		this.location = location;
		this.wind = wind;
		this.windSpeed = wind.getWindSpeed();
		this.deg = wind.getDeg();
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.country = location.getCountry();
		this.city = location.getCity();
		this.humidity = humidity;

	}

	public String getId() {
		return id;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public float getDeg() {
		return deg;
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

	public Location getLocation() {
		return location;
	}

	public Wind getWind() {
		return wind;
	}

	public long getSunrise() {
		return sunrise;
	}

	public long getSunset() {
		return sunset;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public String getDescription() {
		return description;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public float getTemperatureMin() {
		return temperatureMin;
	}

	public float getTemperatureMax() {
		return temperatureMax;
	}

}
