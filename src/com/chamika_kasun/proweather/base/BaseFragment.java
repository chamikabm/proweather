package com.chamika_kasun.proweather.base;

import com.chamika_kasun.proweather.utility.AsyncDataLoader;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.Utils;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {

	private AsyncDataLoader asyncDataLoader;
	
	protected void executeBackgroundTask(String url) {
		
		if(Utils.isDataConnectionAvailable((Context) getActivity())) {
			asyncDataLoader = new AsyncDataLoader(this);
			asyncDataLoader.execute(Constants.LOCATION_WEATHER_URL);
		} else {
			Toast.makeText((Context) getActivity(), "Data Connection is not available", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onTaskFinished(String result) {
		
	}
	
}
