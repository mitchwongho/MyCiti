/**
 * 
 */
package com.touchswipeengage.myciti.domain;

import java.util.LinkedHashMap;

/**
 * @author mwho
 *
 */
public class RouteTimes {
	private LinkedHashMap<String, String> weekdays;
	private LinkedHashMap<String, String> saturdays;
	private LinkedHashMap<String, String> sundaysAndHolidays;
	
	/**
	 * Constructor
	 */
	public RouteTimes() {
		weekdays = new LinkedHashMap<String, String>();
		saturdays = new LinkedHashMap<String, String>();
		sundaysAndHolidays = new LinkedHashMap<String, String>();
	}

	/**
	 * @return the weekdays
	 */
	public LinkedHashMap<String, String> getWeekdays() {
		return weekdays;
	}

	/**
	 * @param weekdays the weekdays to set
	 */
	public void setWeekdays(LinkedHashMap<String, String> weekdays) {
		this.weekdays = weekdays;
	}

	/**
	 * @return the saturdays
	 */
	public LinkedHashMap<String, String> getSaturdays() {
		return saturdays;
	}

	/**
	 * @param saturdays the saturdays to set
	 */
	public void setSaturdays(LinkedHashMap<String, String> saturdays) {
		this.saturdays = saturdays;
	}

	/**
	 * @return the sundaysAndHolidays
	 */
	public LinkedHashMap<String, String> getSundaysAndHolidays() {
		return sundaysAndHolidays;
	}

	/**
	 * @param sundaysAndHolidays the sundaysAndHolidays to set
	 */
	public void setSundaysAndHolidays(LinkedHashMap<String, String> sundaysAndHolidays) {
		this.sundaysAndHolidays = sundaysAndHolidays;
	}
	
}
