package com.chamika_kasun.proweather;

import java.util.Calendar;
import com.chamika_kasun.proweather.utility.Constants;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * This Class is Used to Application Settings
 * @author Chamika
 * E-mail : kasun.chamika@gmail.com
 */
public class Prefs extends PreferenceActivity {

	private SharedPreferences prefs;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();		
		// setting notification state
		enableNotifications(prefs.getBoolean(Constants.NOTIFICATIONS, false));
		
	}
	
	/**
	 * This method is used to enable or disable notifications based on the user selection
	 * @param enable - It takes the user choise in boolean | enable - true
	 */
	private void enableNotifications(boolean enable) {
		
		
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		if(enable) {
			
			String timePeriod = prefs.getString("period", "2");
			Calendar calA = Calendar.getInstance();
			
			switch(Integer.parseInt(timePeriod)){
			
			case 1:
				   //<item>Every 30 Minutes</item>	
			      calA.add(Calendar.MINUTE, 2);
			      alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent);	
				break;
			
			case 2:
				 //<item>Hourly</item>
			     calA.add(Calendar.MINUTE, 60);
			     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent);					
				break;
				
			case 3:
				 //<item>Every 2 Hours</item>
				 calA.add(Calendar.MINUTE, 120);
			     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent);				
				break;
				
			case 4:
				 //<item>Every 6 Hours</item>
				 calA.add(Calendar.MINUTE, 60*6);
			     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent);
				break;
				
			case 5:
				 //<item>Daily</item>
				 calA.add(Calendar.MINUTE, 60*24);
			     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent);
				break;
	
			
			}			
		
		} else {
			alarmManager.cancel(pendingIntent);
		}
	}

}
