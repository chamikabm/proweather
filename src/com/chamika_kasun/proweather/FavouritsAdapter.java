package com.chamika_kasun.proweather;

import java.util.List;

import com.chamika_kasun.proweather.objects.City;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class is used to Collect Favouriets Locations Weather Informations
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class FavouritsAdapter extends ArrayAdapter<City> {

	private Context context;
	private int layoutResourceId;
	private List<City> objects;

	public FavouritsAdapter(Context context, int layoutResourceId,List<City> objects) {

		
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.objects = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		FavouritsHolder holder = null;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layoutResourceId, parent, false);

			holder = new FavouritsHolder();
			holder.cityTemperature = (TextView) view
					.findViewById(R.id.tvCityMinTemparatureValue);
			holder.cityDescription = (TextView) view
					.findViewById(R.id.tvCityDescription);
			holder.cityName = (TextView) view.findViewById(R.id.tvCityName);
			holder.cityTemperatureMax = (TextView) view
					.findViewById(R.id.tvCityMaxTemparatureValue);
			holder.cityTemperatureMin = (TextView) view
					.findViewById(R.id.tvCityMinTemparatureValue);
			holder.cityHumidity = (TextView) view
					.findViewById(R.id.tvCityHumidityValue);
			holder.cityPressure = (TextView) view
					.findViewById(R.id.tvCityPressureValue);
			holder.cityWindSpeed = (TextView) view
					.findViewById(R.id.tvCityWindSpeedValue);
			holder.cityWindDirection = (TextView) view
					.findViewById(R.id.tvCityWindDirectionValue);

			view.setTag(holder);

		} else {
			holder = (FavouritsHolder) view.getTag();
		}

		City city = objects.get(position);
		holder.cityTemperature.setText(""
				+ getCorrectTemparature(city.getTemperature()) + "\u2103");
		holder.cityDescription.setText("" + city.getDescription());
		holder.cityTemperatureMax.setText(""
				+ getCorrectTemparature(city.getTemperatureMax()) + "\u2103");
		holder.cityTemperatureMin.setText(""
				+ getCorrectTemparature(city.getTemperatureMin()) + "\u2103");
		holder.cityHumidity.setText("" + city.getHumidity() + "%");
		holder.cityPressure.setText("" + city.getPressure());
		holder.cityWindSpeed.setText("" + city.getWind().getWindSpeed()
				+ "kmph");
		holder.cityWindDirection.setText("" + city.getWind().getDeg());
		holder.cityName.setText(city.getLocation().getCity());

		return view;

	}

	/**
	 * This method is used to get the corrent temparature in Celcius format
	 * @param temparature - It takes the given temparature value in float
	 * @return - It reurns the relevant temparatute in Celcius
	 */
	private int getCorrectTemparature(float temparature) {

		int temparetureCelcius;
		long number = (long) temparature;
		float fraction = temparature - number;

		if (fraction >= 0.5) {
			temparetureCelcius = (int) (Math.floor(temparature));

		} else {
			temparetureCelcius = (int) (Math.ceil(temparature));
		}

		return temparetureCelcius;
	}

	/**
	 * This class is used to Hold the favouriets TextViews
	 * @author Chamika
	 *
	 */
	private static class FavouritsHolder {
		TextView cityName, cityDescription, cityTemperature,
				cityTemperatureMax, cityTemperatureMin, cityHumidity,
				cityWindSpeed, cityWindDirection, cityPressure;
	}

}
