package com.chamika_kasun.proweather;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * This class is createrd to Generate Notifications in Sinhala and English Medium
 * @author Chamika
 * E-mail :  kasun.chamika@gmail.com
 *
 */

public class AlarmReceiver extends BroadcastReceiver {

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		String lang = "";
		int imageCode = 1;	
		
		Log.v("Message", "Alarm");
		
		Intent notificationIntent = new Intent(context, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		SharedPreferences  getPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		String timePeriod = getPrefs.getString("period", "2");
		Calendar calA = Calendar.getInstance();
        String language = getPrefs.getString("language","1");
		
		switch (Integer.parseInt(language)) {
		
		case 1:
			lang = "en";
			break;

		case 2:
			lang = "sin";
			break;
			
		}
		
		switch(Integer.parseInt(timePeriod)){
		
		case 1:
			   //<item>Every 30 Minutes</item>	
			  Log.v("Repeat Alrm", "Alarm Repeat Working");
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
			
			
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

		Bitmap bitmap =  BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);  
		long when = System.currentTimeMillis();
		Notification notification;
		
		Intent notificationIntent2 = new Intent(context, FragmentsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("notitficaiton", "Chamika Kasun Weather Notificaiton");
		PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, notificationIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
		
		
		switch(Integer.parseInt(timePeriod)){
		
		case 1:
			  //<item>Every 30 Minutes</item>
			  Log.v("Repeat Alrm", "Alarm Repeat Working 1");
		      calA.add(Calendar.MINUTE, 1);
		      alarmManager.cancel(pendingIntent2);		      
		      alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent2);			      
		     
			break;
		
		case 2:
			 //<item>Hourly</item>
		     calA.add(Calendar.MINUTE, 60);
		     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent2);	
			
			break;
			
		case 3:
			 //<item>Every 2 Hours</item>
			 calA.add(Calendar.MINUTE, 120);
		     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent2);
		        
			
			break;
			
		case 4:
			 //<item>Every 6 Hours</item>
			 calA.add(Calendar.MINUTE, 60*6);
		     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent2);
	        
			
			break;
			
		case 5:
			 //<item>Daily</item>
			 calA.add(Calendar.MINUTE, 60*24);
		     alarmManager.set(AlarmManager.RTC_WAKEUP, calA.getTimeInMillis(), pendingIntent2);
			
			break;
			
		}
	
				
		RemoteViews contentView = null;

		if (lang.equals("en")) {
			
			notification = new Notification(R.drawable.ic_launcher, "ProWeather - English", when);
			contentView = new RemoteViews(context.getPackageName(), R.layout.view);
			//contentView.setTextViewText(R.id.title, "English Notification");
			
			switch (imageCode) {
			case 1:
				Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.english);
				contentView.setImageViewBitmap(R.id.ivText, bitmap2);
				contentView.setTextViewText(R.id.tvDescrition, "Clear Sky");
				break;
				
			case 2:
				Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.english2);
				contentView.setImageViewBitmap(R.id.ivText, bitmap3);
				contentView.setTextViewText(R.id.tvDescrition, "Very Heavy Rain");
				break;
				
			case 3:
				Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.english3);
				contentView.setImageViewBitmap(R.id.ivText, bitmap4);
				contentView.setTextViewText(R.id.tvDescrition, "Thunderstorm with Very HEavy Rain");
				break;
				
			case 4:
				Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.english4);
				contentView.setImageViewBitmap(R.id.ivText, bitmap5);
				contentView.setTextViewText(R.id.tvDescrition, "Scattered Clouds");
				break;
				
			case 5:
				Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.english5);
				contentView.setImageViewBitmap(R.id.ivText, bitmap6);
				contentView.setTextViewText(R.id.tvDescrition, "Light Rain");
				break;
			}
			
		} else {
			
			notification = new Notification(R.drawable.ic_launcher, "ProWeather - Sinhala", when);
			contentView = new RemoteViews(context.getPackageName(), R.layout.view);		
			
			switch (imageCode) {
			case 1:
				Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinhala);
				contentView.setImageViewBitmap(R.id.ivText, bitmap2);
				contentView.setTextViewText(R.id.tvDescrition, "Clear Sky");
				break;
				
			case 2:
				Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinhala2);
				contentView.setImageViewBitmap(R.id.ivText, bitmap3);
				contentView.setTextViewText(R.id.tvDescrition, "Very Heavy Rain");
				break;
				
			case 3:
				Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinhala3);
				contentView.setImageViewBitmap(R.id.ivText, bitmap4);
				contentView.setTextViewText(R.id.tvDescrition, "Thunderstorm with Very HEavy Rain");
				break;
				
			case 4:
				Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinhala4);
				contentView.setImageViewBitmap(R.id.ivText, bitmap5);
				contentView.setTextViewText(R.id.tvDescrition, "Scattered Clouds");
				break;
				
			case 5:
				Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinhala5);
				contentView.setImageViewBitmap(R.id.ivText, bitmap6);
				contentView.setTextViewText(R.id.tvDescrition, "Light Rain");
				break;
			}
			
		}

		notification.contentView = contentView;		
		notification.flags = Notification.FLAG_AUTO_CANCEL;		
		notification.contentIntent = pendingIntent2;
		
		// Cancel the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
		notification.defaults |= Notification.DEFAULT_VIBRATE; // Vibration
		notification.defaults |= Notification.DEFAULT_SOUND; // Sound

		// setting id a constant will replace previous notification with the
		// new
		notificationManager.notify(100, notification);
		
		
	}

}
