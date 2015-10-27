package com.vitzi.ringtonescheduler;

import java.util.Calendar;
import java.util.HashMap;


public class Schedule extends HashMap<Integer, Event> {

	private Event nextEvent;
	private Event activeEvent;
	private Event currentEvent;
	private ScheduleListener listener;

	public Schedule() {
		nextEvent = null;
		activeEvent = null;
		currentEvent = null;
		listener = null;
	}
	public interface ScheduleListener {
		public void onNextEventChanged(Event newNextEvent);
	}

	public void setListener(ScheduleListener scheduleListener) {
		this.listener = scheduleListener;
	}

	public ScheduleListener getListener() {
		return listener;
	}

	public void updateNextEvent() {
		nextEvent = null;
		for (int i = 0; i < size(); i++) {
			if (get(i).getBeginCalendar().after(Calendar.getInstance())) {

				// if (i is first Event in future)
				if (nextEvent == null) {
					nextEvent = get(i);
				}

				// if (there is another Event)
				if (get(i + 1) != null

						//&&  i + 1 is in future
						&& get(i + 1).getBeginCalendar().after(Calendar.getInstance())

						//  i + 1 is before current nextEvent
						&& get(i + 1).getBeginCalendar().before(nextEvent.getBeginCalendar())) {
					nextEvent = get(i + i);
				}
			}
		}
	}

	public Event[] getSortedArray() {
		Event[] sortedArray = new Event[this.size()];
		for (int i = 0; i < this.size(); i++)
			sortedArray[i] = this.get(i);
		return sortedArray;
	}

	public void setActiveEvent(Event newActiveEvent) {
		//if newActiveEvent == null
		if(newActiveEvent == null) {
			// old active event end calendar has been set
			// old active event calendar needs to be set to new date
			Calendar calendar = Calendar.getInstance();

			calendar.set(Calendar.YEAR, activeEvent.getBeginCalendar().get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, activeEvent.getBeginCalendar().get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, activeEvent.getBeginCalendar().get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, activeEvent.getBeginCalendar().get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, activeEvent.getBeginCalendar().get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, 0);
			calendar.add(Calendar.DAY_OF_MONTH, activeEvent.getEveryXDay());
			activeEvent.setBeginCalendar(calendar);

			calendar.set(Calendar.YEAR, activeEvent.getEndCalender().get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, activeEvent.getEndCalender().get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, activeEvent.getEndCalender().get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, activeEvent.getEndCalender().get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, activeEvent.getEndCalender().get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, 0);
			calendar.add(Calendar.DAY_OF_MONTH, activeEvent.getEveryXDay());
			activeEvent.setEndCalendar(calendar);
		} else {
			activeEvent = newActiveEvent;
		}
	}

	public void updateCurrentEvent() {
		currentEvent = null;
		for (int i = 0; i < size(); i++) {
			if (get(i).getBeginCalendar().before(Calendar.getInstance()) && get(i).getEndCalender().after(Calendar.getInstance())) {
				currentEvent = get(i);
			}
		}
	}

	public Event getCurrentEvent() {
		return currentEvent;
	}

	public Event getActiveEvent() {
		return activeEvent;
	}

	public Event getNextEvent() {
		return nextEvent;
	}

	@Override
	public String toString() {
		String string = "Schedule{";
		string += "activeEvent: ";
		if(activeEvent == null)
			string += "null";
		else
			string += activeEvent.toString();
		string += ", ";
		string += "nextEvent: ";
		if(nextEvent == null)
			string += "null";
		else
			string += nextEvent.toString();
		string += ", ";
		string += "currentEvent: ";
		if(currentEvent == null)
			string += "null";
		else
			string += currentEvent.toString();
		string += ", Events: [";
		boolean separator = false;
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i) != null) {
				if (separator)
					string += ", ";
				else
					separator = true;

				string += get(i).toString();
			}
		}
		string += "]}";
		return string;
	}
}

/*
	//getNextEvent with Log
	public Event getNextEvent() {
		Log.d("Log", " ------------- getNextEvent ------------- ");
		Log.d("Log", this.toString());

		if(this.isEmpty()) {
			return null;
		} else {
			Event nextEvent = null;
			for (int i = 0; i < size(); i++) {
				Log.d("Log", "------------");
				Log.d("Log", "i: " + i);
				if (nextEvent == null)
					Log.d("Log", "nextEvent: null");
				else
					Log.d("Log", "nextEvent: " + nextEvent.toString());

				if(get(i).getBeginCalendar().after(Calendar.getInstance())) {

					if(nextEvent == null)
						nextEvent = get(i);
					else if (get(i + 1) != null && get(i + 1).getBeginCalendar().before(nextEvent.getBeginCalendar())) {
						Log.d("Log", "i    : " + get(i).toString());
						Log.d("Log", "i + 1: " + get(i + 1).toString());
						nextEvent = get(i + i);
						Log.d("Log", "nextEvent set to " + get(i + 1).toString());
					}
				}
			}
			Log.d("Log", "nextEvent: " + nextEvent.toString());
			return nextEvent;
		}
	}
*/
