package com.chamika_kasun.proweather.objects;

/**
 * This class is used to hold the Day Weather informations
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class DayWeather {
	
	private long dayTemparature;
	private long nightTemparature;
	private String mainDescription;
	private String description;
	private float temperature;
	private float pressure;
	private int humidity;
	private float temperatureMin;
	private float temperatureMax;
	private Location location;
	private Wind wind;
	private String iconCode;
	private String day;
	
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public long getDayTemparature() {
		return dayTemparature;
	}
	public void setDayTemparature(long dayTemparature) {
		this.dayTemparature = dayTemparature;
	}
	public long getNightTemparature() {
		return nightTemparature;
	}
	public void setNightTemparature(long nightTemparature) {
		this.nightTemparature = nightTemparature;
	}
	public String getMainDescription() {
		return mainDescription;
	}
	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public float getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(float temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public float getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(float temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public String getIconCode() {
		return iconCode;
	}
	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}
	
	
	
	
	
	

}
