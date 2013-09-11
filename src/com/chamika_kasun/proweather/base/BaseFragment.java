package com.chamika_kasun.proweather.base;

import com.chamika_kasun.proweather.utility.AsyncDataLoader;
import com.chamika_kasun.proweather.utility.Constants;
import com.chamika_kasun.proweather.utility.Utils;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {

	private AsyncDataLoader asyncDataLoader;

	
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
