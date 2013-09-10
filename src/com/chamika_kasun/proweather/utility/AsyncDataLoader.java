package com.chamika_kasun.proweather.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.chamika_kasun.proweather.base.BaseFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class AsyncDataLoader extends AsyncTask<String, Integer, String> {

	private BaseFragment baseFragment;
	private boolean isMainTask;

	public AsyncDataLoader(BaseFragment baseFragment, boolean isMainTask) {
		this.baseFragment = baseFragment;
		this.isMainTask = isMainTask;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {

		String url = null;
		if (params != null && params.length > 0) {
			url = params[0];
		} else {
			return null;
		}

		InputStream is = null;
		HttpPost httppost;
		String result = null;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result = sb.toString();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		
		if(result.contains("404")){
			return;
		}
						
		super.onPostExecute(result);
		if (baseFragment != null) {
			if (isMainTask) {
				baseFragment.onTaskFinished(result);
			} else {
				baseFragment.onSubTaskFinished(result);
			}
		}
	}

}
