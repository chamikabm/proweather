package com.chamika_kasun.proweather.base;

import com.chamika_kasun.proweather.utility.AsyncDataLoader;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.JSONParser;
import com.chamika_kasun.proweather.utility.Utils;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BaseFragment extends Fragment {

	private AsyncDataLoader asyncDataLoader;
	protected JSONParser jsonParser;
	
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		jsonParser = new JSONParser();
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void executeBackgroundTask(String url,boolean isMain) {
		
		if(Utils.isDataConnectionAvailable((Context) getActivity())) {
			asyncDataLoader = new AsyncDataLoader(this,isMain,(Context)getActivity());
			asyncDataLoader.execute(url);
		} else {
			//Use to notify the user if ther is no Data Connection is available.
			Toast.makeText((Context) getActivity(), "Data Connection is not available", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onTaskFinished(String result) {
		
	}
	
	public void onSubTaskFinished(String result){
		
	}
	
}
