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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import static com.vitzi.ringtonescheduler.R.layout.event;


public class MainActivity extends AppCompatActivity {

	static Schedule schedule;
	AudioManager audioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		schedule = new Schedule();
		schedule.setListener(new Schedule.ScheduleListener() {
			@Override
			public void onTest() {
				Log.d("Log", "Test");
			}

			@Override
			public void onNextEventChanged(Event newNextEvent) {

			}
		});

		if(!schedule.isEmpty()) {
			findViewById(R.id.noEventTextView).setVisibility(View.GONE);

			ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(MainActivity.this, R.layout.event, R.id.eventTextView, schedule.getSortedArray());

			ListView listView = (ListView) findViewById(R.id.eventsListView);
			listView.setVisibility(View.VISIBLE);
			listView.setAdapter(adapter);
		}

		findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewEvent.class);
				startActivity(intent);
			}
		});

		// start
		findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (schedule.isEmpty()) {
					Toast.makeText(MainActivity.this, "no Event set", Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(getApplicationContext(), Service.class);
					intent.setAction("START");
					startService(intent);
					Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// update
		findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onResume();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		if(!schedule.isEmpty()) {
			findViewById(R.id.noEventTextView).setVisibility(View.GONE);

			// update events ListView
			ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(MainActivity.this, R.layout.event, R.id.eventTextView, schedule.getSortedArray());
			ListView listView = (ListView) findViewById(R.id.eventsListView);
			listView.setVisibility(View.VISIBLE);
			listView.setAdapter(adapter);
			findViewById(R.id.eventsTextView).setVisibility(View.VISIBLE);

			// update current event TextView
			TextView currentEventEventTextView = (TextView) findViewById(R.id.currentEventEventTextView);
			schedule.updateCurrentEvent();
			Event currentEvent = schedule.getCurrentEvent();
			if(currentEvent == null)
				currentEventEventTextView.setText("no current Event");
			else
				currentEventEventTextView.setText(schedule.getCurrentEvent().toString());

			// update next event TextView
			TextView nextEventEventTextView = (TextView) findViewById(R.id.nextEventEventTextView);
			schedule.updateNextEvent();
			Event nextEvent = schedule.getNextEvent();
			if(nextEvent == null) {
				nextEventEventTextView.setText("no next Event");
			}
			else {
				nextEventEventTextView.setText(schedule.getNextEvent().toString());
			}
		}
	}
}