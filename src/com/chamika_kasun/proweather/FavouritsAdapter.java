package com.chamika_kasun.proweather;

import java.util.List;

import com.chamika_kasun.proweather.objects.City;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
			holder.cityTemperature = (TextView) view.findViewById(R.id.tvCityMinTemparatureValue);
			holder.cityDescription = (TextView) view.findViewById(R.id.tvCityDescription);
			holder.cityName = (TextView) view.findViewById(R.id.tvCityName);
			holder.cityTemperatureMax = (TextView) view.findViewById(R.id.tvCityMaxTemparatureValue);
			holder.cityTemperatureMin = (TextView) view.findViewById(R.id.tvCityMinTemparatureValue);
			holder.cityHumidity = (TextView) view.findViewById(R.id.tvCityHumidityValue);
			holder.cityPressure = (TextView) view.findViewById(R.id.tvCityPressureValue);
			holder.cityWindSpeed = (TextView) view.findViewById(R.id.tvCityWindSpeedValue);
			holder.cityWindDirection = (TextView) view.findViewById(R.id.tvCityWindDirectionValue);
			holder.favWeatherImage = (ImageView) view.findViewById(R.id.ivFavWeatherImage);

			view.setTag(holder);

		} else {
			holder = (FavouritsHolder) view.getTag();
		}

		City city = objects.get(position);
		holder.cityTemperature.setText(""+ getCorrectTemparature(city.getTemperature()) + "\u2103");
		holder.cityDescription.setText("" + city.getDescription());
		holder.cityTemperatureMax.setText(""+ getCorrectTemparature(city.getTemperatureMax()) + "\u2103");
		holder.cityTemperatureMin.setText(""+ getCorrectTemparature(city.getTemperatureMin()) + "\u2103");
		holder.cityHumidity.setText("" + city.getHumidity() + "%");
		holder.cityPressure.setText("" + city.getPressure());
		holder.cityWindSpeed.setText("" + city.getWind().getWindSpeed()+ "kmph");
		holder.cityWindDirection.setText("" + city.getWind().getDeg());
		holder.cityName.setText(city.getLocation().getCity());
		holder.favWeatherImage.setImageResource(getDrawable(city.getIconCode()));

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
	 * This methos is used to get drwable icon according to the weather icon code
	 * @param weatherId - It takes the relevant weather condition pre defined value in String
	 * @return - Relevent drwable integer Value
	 */
	public int getDrawable(String weatherId){
		
		int id = Integer.parseInt(weatherId);
		int returnDrwanleId = 500;
		
		switch (id) {			
		
		case 500: //light rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 500");
			returnDrwanleId = R.drawable.q500;
			break;
			
		case 501: //moderate rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 501");
			returnDrwanleId = R.drawable.q501;
			break;
			
		case 502: //heavy intensity rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 502");
			returnDrwanleId = R.drawable.q502;
			break;

		case 503: //very heavy rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 503");
			returnDrwanleId = R.drawable.q503;
			break;

		case 504: //extreme rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 504");
			returnDrwanleId = R.drawable.q504;
			break;
			
		case 511: //freezing rain
			Log.v("Weather Icon Code", "Weather Icon Code : 511");
			returnDrwanleId = R.drawable.q511;
			break;
			
		case 520: //light intensity shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 520");
			returnDrwanleId = R.drawable.q520;
			break;
			
		case 521: //shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 521");
			returnDrwanleId = R.drawable.q521;
			break;
			
		case 522: //heavy intensity shower rain
			Log.v("Weather Icon Code", "7 Weather Icon Code : 522");
			returnDrwanleId = R.drawable.q522;
			break;
			
		case 800: //Sky is Clear
			Log.v("Weather Icon Code", "7 Weather Icon Code : 800");
			returnDrwanleId = R.drawable.q800;
			break;

		case 801: //few clouds
			Log.v("Weather Icon Code", "7 Weather Icon Code : 801");
			returnDrwanleId = R.drawable.q801;
			break;

		case 802: //few clouds
			Log.v("Weather Icon Code", "7 Weather Icon Code : 802");
			returnDrwanleId = R.drawable.q802;
			break;
			
		case 803: //scattered clouds 
			Log.v("Weather Icon Code", "7 Weather Icon Code : 803");
			returnDrwanleId = R.drawable.q803;
			break;
			
		case 804: //broken clouds  
			Log.v("Weather Icon Code", "7 Weather Icon Code : 804");
			returnDrwanleId = R.drawable.q804;
			break;
			
		case 805: //overcast clouds  
			Log.v("Weather Icon Code", "7  Weather Icon Code : 805");
			returnDrwanleId = R.drawable.q805;
			break;
		}
		
		
		return returnDrwanleId;
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
		
		ImageView favWeatherImage;
	}

}
