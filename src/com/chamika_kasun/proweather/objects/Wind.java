package com.chamika_kasun.proweather.objects;

/**
 * This class is used to hold the Location Wind Information
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
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
