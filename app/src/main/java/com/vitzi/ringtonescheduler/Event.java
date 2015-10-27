package com.vitzi.ringtonescheduler;

import android.media.AudioManager;

import java.util.Calendar;

public class Event {

	static int nextId = 0;

	String name;
	private int id;

	private Calendar beginCalendar;
	private Calendar endCalender;
	private int everyXDay;

	private int duringRingerMode;
	private int afterRingerMode;

	public Event(String name, Calendar beginCalendar, Calendar endCalender, int everyXDay) {
		this.name = name;
		id = nextId++;

		this.beginCalendar = beginCalendar;
		this.endCalender = endCalender;
		this.everyXDay = everyXDay;

		duringRingerMode = AudioManager.RINGER_MODE_SILENT;
		afterRingerMode = AudioManager.RINGER_MODE_VIBRATE;

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

	public void setBeginCalendar(Calendar beginCalendar) {
		this.beginCalendar = beginCalendar;
	}

	public void setEndCalendar(Calendar endCalender) {
		this.endCalender = endCalender;
	}

	public int getEveryXDay() {
		return everyXDay;
	}

	public static String calendarToString(Calendar calendar) {
		String string = "";
		string += calendar.get(Calendar.DAY_OF_MONTH);
		string += ". ";
		string += calendar.get(Calendar.MONTH);
		string += ".";
//		string += calendar.get(Calendar.YEAR);
		string += ", ";
		string += calendar.get(Calendar.HOUR_OF_DAY);
		string += ":";
		string += calendar.get(Calendar.MINUTE);

		return  string;
	}

	@Override
	public String toString() {
		String string = "Event {";
		string += "id: " + id;
		string += ", ";
//		string += "name: " + name;
//		string += ", ";
		string += "everyXDay: " + everyXDay;
		string += ", ";
		string += "beginTime: " + calendarToString(getBeginCalendar());
		string += ", ";
		string += "endTime: " + calendarToString(getEndCalender());
//		string += ", ";
//		string += "duringRingerMode: " + duringRingerMode;
//		string += ", ";
//		string += "afterRingerMode: " + afterRingerMode;
		string += "}";
		return string;
	}
}
