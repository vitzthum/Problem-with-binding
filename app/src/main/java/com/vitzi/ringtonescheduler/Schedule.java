package com.vitzi.ringtonescheduler;

import java.util.HashMap;

public class Schedule extends HashMap<Integer, Event> {

	public Schedule() {

	}

	@Override
	public String toString() {
		String string = "Schedule[";
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
		string += "]";
		return string;
	}
}
