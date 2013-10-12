package com.chamika_kasun.proweather.objects;

/**
 * This class is used to hold the Hourlr Weather information for a particular location
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class HourlyWeather {

	private float tvTime12AMValue;
	private float tvTime3AMValue;
	private float tvTime6AMValue;
	private float tvTime9AMValue;
	private float tvTime12PMValue;
	private float tvTime3PMValue;
	private float tvTime6PMValue;
	private float tvTime9PMValue;
	private String tvTime12AMICON;
	private String tvTime3AMICON;
	private String tvTime6AMICON;
	private String tvTime9AMICON;
	private String tvTime12PMICON;
	private String tvTime3PMICON;
	private String tvTime6PMICON;
	private String tvTime9PMICON;

	public HourlyWeather(float tvTime12AMValue, float tvTime3AMValue,
			float tvTime6AMValue, float tvTime9AMValue, float tvTime12PMValue,
			float tvTime3PMValue, float tvTime6PMValue, float tvTime9PMValue,
			String tvTime12AMICON, String tvTime3AMICON, String tvTime6AMICON,
			String tvTime9AMICON, String tvTime12PMICON, String tvTime3PMICON,
			String tvTime6PMICON, String tvTime9PMICON) {

		this.tvTime12AMValue = tvTime12AMValue;
		this.tvTime3AMValue = tvTime3AMValue;
		this.tvTime6AMValue = tvTime6AMValue;
		this.tvTime9AMValue = tvTime9AMValue;
		this.tvTime12PMValue = tvTime12PMValue;
		this.tvTime3PMValue = tvTime3PMValue;
		this.tvTime6PMValue = tvTime6PMValue;
		this.tvTime9PMValue = tvTime9PMValue;
		this.tvTime12AMICON = tvTime12AMICON;
		this.tvTime3AMICON = tvTime3AMICON;
		this.tvTime6AMICON = tvTime6AMICON;
		this.tvTime9AMICON = tvTime9AMICON;
		this.tvTime12PMICON = tvTime12PMICON;
		this.tvTime3PMICON = tvTime3PMICON;
		this.tvTime6PMICON = tvTime6PMICON;
		this.tvTime9PMICON = tvTime9PMICON;

	}

	public float getTvTime12AMValue() {
		return tvTime12AMValue;
	}

	public float getTvTime3AMValue() {
		return tvTime3AMValue;
	}

	public float getTvTime6AMValue() {
		return tvTime6AMValue;
	}

	public float getTvTime9AMValue() {
		return tvTime9AMValue;
	}

	public float getTvTime12PMValue() {
		return tvTime12PMValue;
	}

	public float getTvTime3PMValue() {
		return tvTime3PMValue;
	}

	public float getTvTime6PMValue() {
		return tvTime6PMValue;
	}

	public float getTvTime9PMValue() {
		return tvTime9PMValue;
	}

	public String getTvTime12AMICON() {
		return tvTime12AMICON;
	}

	public String getTvTime3AMICON() {
		return tvTime3AMICON;
	}

	public String getTvTime6AMICON() {
		return tvTime6AMICON;
	}

	public String getTvTime9AMICON() {
		return tvTime9AMICON;
	}

	public String getTvTime12PMICON() {
		return tvTime12PMICON;
	}

	public String getTvTime3PMICON() {
		return tvTime3PMICON;
	}

	public String getTvTime6PMICON() {
		return tvTime6PMICON;
	}

	public String getTvTime9PMICON() {
		return tvTime9PMICON;
	}

}
