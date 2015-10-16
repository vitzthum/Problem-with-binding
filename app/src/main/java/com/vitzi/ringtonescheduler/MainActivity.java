package com.vitzi.ringtonescheduler;

import android.app.*;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	AudioManager audioManager;

	int beginHour, beginMinute, endHour, endMinute;
	TimePicker beginTimePicker;
	TextView beginTextView;
	TimePicker endTimePicker;
	TextView endTextView;

	Spinner beginModeSpinner;

	int inMinutes, inHours;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		Intent intent = new Intent(this, Service.class);
		final PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
		final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5 * 1000, 60 * 1000, pendingIntent);
				Log.d("Log", "Alarm set");
				Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_LONG).show();
			}
		});

		findViewById(R.id.endButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alarmManager.cancel(pendingIntent);
				Log.d("Log", "Alarm canceled");
				Toast.makeText(getApplicationContext(), "Alarm canceld", Toast.LENGTH_LONG).show();
			}
		});

/*
		beginModeSpinner = (Spinner) findViewById(R.id.beginModeSpinner);

		beginTextView = (TextView) findViewById(R.id.beginTextView);
		endTextView = (TextView) findViewById(R.id.beginTextView);

		beginTimePicker = (TimePicker) findViewById(R.id.beginTimePicker);
		beginTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				beginHour = hourOfDay;
				beginMinute = minute;
			}
		});
*/
	}

	private void doToastAt() {

/*
		final Handler handler = new Handler();
		handler.postAtTime(new Runnable() {
			@Override
			public void run() {
				switch (beginModeSpinner.getSelectedItemPosition()) {
					case 0:
						audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
						break;
					case 1:
						audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
						break;
					case 2:
						audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
						break;
					default:
						Toast.makeText(MainActivity.this, "no RingerMode", Toast.LENGTH_SHORT).show();
						break;
				}
				Toast.makeText(MainActivity.this, "RingerMode ist: " + audioManager.getRingerMode(), Toast.LENGTH_SHORT).show();
			}
		}, SystemClock.uptimeMillis() + inMinutes * 60 * 1000);
*/
	}

	@Override
	protected void onPause() {
		super.onPause();

		JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
		tm.cancelAll();
	}
}