package com.vitzi.ringtonescheduler;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Looper;
import android.util.Log;
import android.os.Handler;
import android.widget.Toast;

import java.util.Calendar;


public class Service extends IntentService {

	AlarmManager alarmManager;
	AudioManager audioManager;

	public Service() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

	}
/*
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("Log", "----- onHandleIntent -----");
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

		if (intent.getAction() == null) {
			Log.d("Log", "Action: no Action");

		} else if (intent.getAction() == "START") {
			Log.d("Log", "Action: START");
			Log.d("Log", "- START -");
			start();

		} else if (intent.getAction() == AudioManager.RINGER_MODE_SILENT + "" || intent.getAction() == AudioManager.RINGER_MODE_VIBRATE + "" || intent.getAction() == AudioManager.RINGER_MODE_NORMAL + "") {
			int x = Integer.parseInt(intent.getAction().substring(0, 1));
			audioManager.setRingerMode(x);
			Log.d("Log", "- Ringermode set to: " + audioManager.getRingerMode() + " -");
			Log.d("Log", "Time:  " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));

			Schedule schedule = Schedule.getCurrentInstance();

			if (schedule.hasEvent()) {

				if (schedule.getEvent(0).isActive()) {
					intent = new Intent(this, Service.class);
					intent.setAction(schedule.getEvent(0).getAfterRingerMode() + "");

					PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
					alarmManager.setExact(AlarmManager.RTC, schedule.getEvent(0).getEndCalender().getTimeInMillis(), pendingIntent);

					Log.d("Log", "- new Alarm -");
					Log.d("Log", "Type:       Endalarm");
					Log.d("Log", "Action:     " + intent.getAction());
					Log.d("Log", "Time:  " + schedule.getEvent(0).getEndCalender().get(Calendar.HOUR_OF_DAY) + ":" + schedule.getEvent(0).getEndCalender().get(Calendar.MINUTE));

				} else {
					intent = new Intent(this, Service.class);
					intent.setAction(schedule.getEvent(0).getDuringRingerMode() + "");

					PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
					alarmManager.setExact(AlarmManager.RTC, schedule.getEvent(0).getBeginCalendar().getTimeInMillis(), pendingIntent);

					schedule.getEvent(0).setActive();

					Log.d("Log", "- new Alarm -");
					Log.d("Log", "Type:       Beginalarm");
					Log.d("Log", "Action:     " + intent.getAction());
					Log.d("Log", "Time:  " + schedule.getEvent(0).getBeginCalendar().get(Calendar.HOUR_OF_DAY) + ":" + schedule.getEvent(0).getBeginCalendar().get(Calendar.MINUTE));

				}
			} else {
				Log.d("Log", "- no Event in Schedule -");
			}
		} else {

			Log.d("Log", "- Action not known -");

		}

		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Service.this, "RingerMode set to: " + audioManager.getRingerMode(), Toast.LENGTH_LONG).show();
			}
		});

		Log.d("Log", Schedule.currentInstance.toString());

	}

	public void start() {
		Event event = Schedule.getCurrentInstance().getEvent(0);

		Intent intent = new Intent(getApplicationContext(), Service.class);
		intent.setAction(event.getDuringRingerMode() + "");

		PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);

		alarmManager.setExact(AlarmManager.RTC, event.getBeginCalendar().getTimeInMillis(), pendingIntent);
		event.setActive();

		Log.d("Log", "- new Alarm -");
		Log.d("Log", "Action:     " + intent.getAction());
		Log.d("Log", "Type:       Beginalarm");
		Log.d("Log", "Time:  " + event.getBeginCalendar().get(Calendar.HOUR_OF_DAY) + ":" + event.getBeginCalendar().get(Calendar.MINUTE));
	}

	*/
}
