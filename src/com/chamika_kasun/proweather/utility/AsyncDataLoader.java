package com.chamika_kasun.proweather.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.chamika_kasun.proweather.base.BaseFragment;

import android.os.AsyncTask;

public class AsyncDataLoader extends AsyncTask<String, Integer, String> {
	
	private BaseFragment baseFragment;
	
	public AsyncDataLoader(BaseFragment baseFragment) {
		this.baseFragment = baseFragment;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		
		if(params[0] == null) {
			return null;
		}
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(URI.create(params[0]));
		HttpResponse response = client.execute(request);
		
		try {
			Ki
		} catch() {
			
		}
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		StringBuffer sb = new StringBuffer("");
		String l = "";
		String nl = System.getProperty("line.separator");
		while ((l = in.readLine()) != null) {
			sb.append(l + nl);
		}
		in.close();
		data = sb.toString();
		System.out.print(data);
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(baseFragment != null) {
			baseFragment.onTaskFinished(result);
		}
	}

}
