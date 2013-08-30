package com.chamika_kasun.proweather;

import com.chamika_kasun.proweather.base.BaseFragment;
import com.chamika_kasun.proweather.objects.Weather;
import com.chamika_kasun.proweather.utility.JSONParser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Home extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);

		View convertView = inflater.inflate(R.layout.home, container, false);

		return convertView;

	}

	@Override
	public void onTaskFinished(String result) {
		super.onTaskFinished(result);
		
		if(result != null) {
			Weather weatherInfo = JSONParser.getLocationWeather(result);	
		} else {
			Toast.makeText((Context) getActivity(), "Data Passing Error", Toast.LENGTH_SHORT).show();
		}
		
	}

}
