package com.vitzi.ringtonescheduler;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;

import java.util.Calendar;

public class Service extends IntentService {

	AlarmManager alarmManager;
	AudioManager audioManager;

	public Service() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

		String action = intent.getAction();
		if (action == "START") {
			newAlarm();

		} else {
			final int x = Integer.parseInt(action.substring(0, 1));
			if (x == AudioManager.RINGER_MODE_SILENT || x == AudioManager.RINGER_MODE_VIBRATE || x == AudioManager.RINGER_MODE_NORMAL) {
				audioManager.setRingerMode(x);
				Handler handler = new Handler(Looper.getMainLooper());

				handler.post(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(Service.this, "Ringermode set to " + x, Toast.LENGTH_LONG).show();
					}
				});

				newAlarm();
			}
		}
	}

	public void newAlarm() {
		Schedule schedule = MainActivity.schedule;
		schedule.updateNextEvent();
		Log.d("Log", schedule.toString());

		// make new alarm

		final Event activeEvent;
		final Event nextEvent;

		// exists active alarm?
		if ((activeEvent = schedule.getActiveEvent()) != null) {
			// make new endalarm

			final Intent intent = new Intent(this, Service.class);
			intent.setAction(activeEvent.getAfterRingerMode() + "");

			PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
			alarmManager.setExact(AlarmManager.RTC, activeEvent.getEndCalender().getTimeInMillis(), pendingIntent);

			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(Service.this, "will set Ringermode to " + intent.getAction() + " at " + Event.calendarToString(activeEvent.getEndCalender()), Toast.LENGTH_LONG).show();
				}
			});

			schedule.setActiveEvent(null);

		} else if((nextEvent = schedule.getNextEvent()) != null) {
			//make new beginalarm

			final Intent intent = new Intent(this, Service.class);
			intent.setAction(nextEvent.getDuringRingerMode() + "");

			PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
			alarmManager.setExact(AlarmManager.RTC, nextEvent.getBeginCalendar().getTimeInMillis(), pendingIntent);

			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(Service.this, "will set Ringermode to " + intent.getAction() + " at " + Event.calendarToString(nextEvent.getBeginCalendar()), Toast.LENGTH_LONG).show();
				}
			});

			schedule.setActiveEvent(nextEvent);
		}
	}
/*
	public void start() {
		Schedule schedule = MainActivity.schedule;
		schedule.updateNextEvent();
		final Event event = schedule.getNextEvent();

		if (event != null) {
			final Intent intent = new Intent(getApplicationContext(), Service.class);
			intent.setAction(event.getDuringRingerMode() + "");

			PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);

			alarmManager.setExact(AlarmManager.RTC, event.getBeginCalendar().getTimeInMillis(), pendingIntent);

			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(Service.this, "will set Ringermode to " + intent.getAction() + " at " + Event.calendarToString(event.getBeginCalendar()), Toast.LENGTH_LONG).show();
				}
			});

			schedule.setActiveEvent(event);

			Calendar newBeginCalender = Calendar.getInstance();
			newBeginCalender.add(Calendar.DAY_OF_MONTH, event.getEveryXDay());
			event.setBeginCalendar(newBeginCalender);
		}
	}
*/
}
