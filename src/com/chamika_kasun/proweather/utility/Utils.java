package com.chamika_kasun.proweather.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	public static boolean isDataConnectionAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}

	}

	public static String convertInToTime(long miliSeconds) {
		Date date = new Date(miliSeconds);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm",
				Locale.getDefault());
		return sDateFormat.format(date);
	}

}
