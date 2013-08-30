package com.chamika_kasun.proweather.objects;

public class Wind {

	private float windSpeed;
	private float deg;
	private float var_beg;
	private float var_end;

	public Wind(float windSpeed, float deg, float var_beg, float var_end) {

		this.windSpeed = windSpeed;
		this.deg = deg;
		this.var_beg = var_beg;
		this.var_end = var_end;

	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public float getDeg() {
		return deg;
	}

	public float getVar_beg() {
		return var_beg;
	}

	public float getVar_end() {
		return var_end;
	}

}
