package com.vitzi.ringtonescheduler;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import static com.vitzi.ringtonescheduler.R.layout.event;


public class MainActivity extends AppCompatActivity {

	static Schedule schedule;
	AudioManager audioManager;
	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		schedule = new Schedule();
		Event event = new Event("Test1", Calendar.getInstance(), Calendar.getInstance());
		Event event2 = new Event("Test2", Calendar.getInstance(), Calendar.getInstance());
		schedule.put(event.getId(), event);
		schedule.put(event2.getId(), event2);

		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(MainActivity.this, R.layout.event, R.id.eventTextView, schedule.values().toArray(new Event[0]));

		ListView listView;
		listView = (ListView) findViewById(R.id.eventsListView);

		listView.setAdapter(adapter);

		findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewEvent.class);
				startActivity(intent);
			}
		});

/*
		// new Event
		findViewById(R.id.newButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final TimePickerDialog beginDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						Calendar beginCalendar = Calendar.getInstance();
						beginCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
						beginCalendar.set(Calendar.MINUTE, minute);
						beginCalendar.set(Calendar.SECOND, 0);

						final TimePickerDialog endDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								Calendar endCalendar = Calendar.getInstance();
								endCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
								endCalendar.set(Calendar.MINUTE, minute);
								endCalendar.set(Calendar.SECOND, 0);
							}
						}, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);

						endDialog.show();
					}
				}, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
				beginDialog.show();
			}
		});


		// start
		findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (schedule.hasEvent()) {
					Intent intent = new Intent(getApplicationContext(), Service.class);
					intent.setAction("START");
					startService(intent);
					Log.d("Log", Schedule.currentInstance.toString());
					Log.d("Log", "----- Service started -----");
				} else {
					Toast.makeText(MainActivity.this, "no Event set", Toast.LENGTH_SHORT).show();
					Log.d("Log", "Schedule has no Event");
				}
			}
		});
*/

	}

	public void add() {
/*
		if(schedule.push(new Event(beginCalendar, endCalendar)))
			Toast.makeText(MainActivity.this, "Event added", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(MainActivity.this, "Event not added", Toast.LENGTH_SHORT).show();
		Log.d("Log", schedule.toString());

		LinearLayout linearLayout = new LinearLayout(MainActivity.this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		EventView linearLayout = new EventView(MainActivity.this);

		TextView textView = new TextView(MainActivity.this);
		textView.setText("Begin:");

		TextView eventView = new TextView(MainActivity.this);
		eventView.setText("Zeit");

		linearLayout.addView(textView);
		linearLayout.addView(eventView);
		layout.addView(linearLayout);
*/

	}
}