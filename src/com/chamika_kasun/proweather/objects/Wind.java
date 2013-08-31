package com.chamika_kasun.proweather.objects;

public class Wind {

	private float windSpeed;
	private float deg;

	public Wind(float windSpeed, float deg) {

		this.windSpeed = windSpeed;
		this.deg = deg;

	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public float getDeg() {
		return deg;
	}

}
