package com.vitzi.ringtonescheduler;

import android.media.AudioManager;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

	String name;
	private Calendar beginCalendar;
	private Calendar endCalender;
	private boolean isActive = false;
	private int duringRingerMode;
	private int afterRingerMode;

	static int nextId = 0;
	private int id;

	public Event(String name, Calendar beginCalendar, Calendar endCalender) {
		this.name = name;
		id = nextId++;

		this.beginCalendar = beginCalendar;
		this.endCalender = endCalender;
		duringRingerMode = AudioManager.RINGER_MODE_SILENT;
		afterRingerMode = AudioManager.RINGER_MODE_VIBRATE;

	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive() {
		isActive = true;
	}

	public int getDuringRingerMode() {
		return duringRingerMode;
	}

	public int getAfterRingerMode() {
		return afterRingerMode;
	}

	public Calendar getBeginCalendar() {
		return beginCalendar;
	}

	public Calendar getEndCalender() {
		return endCalender;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		String string = "Event {";
		string += "id: " + id;
		string += ", ";
		string += "name: " + name;
		string += ", ";
		string += "beginTime: " + beginCalendar.get(Calendar.HOUR_OF_DAY) + ":" + beginCalendar.get(Calendar.MINUTE);
		string += ", ";
		string += "endTime: " + endCalender.get(Calendar.HOUR_OF_DAY) + ":" + endCalender.get(Calendar.MINUTE);
		string += ", ";
		string += "duringRingerMode: " + duringRingerMode;
		string += ", ";
		string += "afterRingerMode: " + afterRingerMode;
		string += ", ";
		string += "isActive: " + isActive;
		string += "}";
		return string;
	}
}
