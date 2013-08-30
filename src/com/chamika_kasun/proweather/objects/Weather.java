package com.chamika_kasun.proweather.objects;

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

	public Weather(long sunrise, long sunset, String mainDescription,
			String description, float temperature, float pressure,
			int humidity, float temperatureMin, float temperatureMax,
			Location location, Wind wind) {

		this.sunset = sunset;
		this.sunrise = sunrise;
		this.mainDescription = mainDescription;
		this.description = description;
		this.temperature = temperature;
		this.pressure = pressure;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;

	}

	public Location getLocation() {
		return location;
	}

	public Wind getWind() {
		return wind;
	}

	public long getSinrise() {
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
