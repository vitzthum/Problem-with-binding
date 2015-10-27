package com.vitzi.ringtonescheduler;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewEvent extends AppCompatActivity {

	int newEventBeginHour, newEventBeginMinute, newEventEndHour, newEventEndMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newevent);

		newEventBeginHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		newEventBeginMinute = Calendar.getInstance().get(Calendar.MINUTE) + 1;

		final TextView newEventBeginTimeTextView = (TextView) findViewById(R.id.newEventBeginTimeTextView);
		newEventBeginTimeTextView.setText(newEventBeginHour + ":" + newEventBeginMinute);

		newEventBeginTimeTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(NewEvent.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						newEventBeginHour = hourOfDay;
						newEventBeginMinute = minute;
						newEventBeginTimeTextView.setText(hourOfDay + ":" + minute);
					}
				}, newEventBeginHour, newEventBeginMinute, true).show();
			}
		});

		newEventEndHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		newEventEndMinute = Calendar.getInstance().get(Calendar.MINUTE) + 2;

		final TextView newEventEndTimeTextView = (TextView) findViewById(R.id.newEventEndTimeTextView);
		newEventEndTimeTextView.setText(newEventEndHour + ":" + newEventEndMinute);

		newEventEndTimeTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(NewEvent.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						newEventEndHour = hourOfDay;
						newEventEndMinute = minute;
						newEventEndTimeTextView.setText(hourOfDay + ":" + minute);
					}
				}, newEventEndHour, newEventEndMinute, true).show();
			}
		});

		findViewById(R.id.newEventSaveButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar begin = Calendar.getInstance();
				begin.set(Calendar.HOUR_OF_DAY, newEventBeginHour);
				begin.set(Calendar.MINUTE, newEventBeginMinute);
				begin.set(Calendar.SECOND, 0);

				Calendar end = Calendar.getInstance();
				end.set(Calendar.HOUR_OF_DAY, newEventEndHour);
				end.set(Calendar.MINUTE, newEventEndMinute);
				end.set(Calendar.SECOND, 0);

				EditText everyXDayStringEditText = (EditText) findViewById(R.id.newEventEveryXDayEditText);
				Event event = new Event("Test", begin, end, Integer.parseInt(everyXDayStringEditText.getText().toString()));
				MainActivity.schedule.put(event.getId(), event);

				finish();
			}
		});
	}
}
