package model;

import java.util.Random;

/**
 * This class is meant to be used to represent dates in each flight. As these dates are generated completely by random, some inexact dates such as February 31th may be generated. 
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - April/2019
 */
public class Date implements Comparable<Date> {
	
	//Constant fields.
	/**The time of the day before military hour reaches 12:00 */
	public static final String AM = "AM";
	
	/**The time of the day after military hour reaches 12:00 */
	public static final String PM = "PM";
	
	//Attributes
	
	/**Either AM or PM, depending on the moment of the day.*/
	private String dayMoment;
	
	/**Integer number representing the calendar day. Ranges 1 to 31*/
	private int day;
	
	/**Integer number representing the calendar month. Ranges 1 to 12*/
	private int month;
	
	/**Integer number representing the year. Ranges 2019 to 2021.*/
	private int year;
	
	/**Integer number representing the hour. Ranges 0 to 23*/
	private int hour;
	
	/**Integer number representing the minutes. Ranges 0 to 59.*/
	private int minutes;
	
	//Methods
	
	/**
	 * Creates a completely random date.
	 */
	public Date() {
		Random rnd = new Random();
		day = rnd.nextInt(30)+1; 
		month = rnd.nextInt(11)+1;
		year = rnd.nextInt(2) + 2019;
		hour = rnd.nextInt(24);
		minutes = rnd.nextInt(59);
		if(hour < 11 || hour == 11 && minutes <= 59) {
			dayMoment = AM;
		}else {
			dayMoment = PM;
		}
	}
	
	/**
	 * For testing purposes. Creates a date using the data received as parameters.
	 * @param d The date's day.
	 * @param m The date's month.
	 * @param y The date's year.
	 * @param h The date's hour.
	 * @param mn The date's minutes.
	 */
	public Date(int d, int m, int y, int h, int mn) {
		day = d;
		month = m;
		year = y;
		hour = h;
		minutes = mn;
		if(hour < 11 || hour == 11 && minutes <= 59) {
			dayMoment = AM;
		}else {
			dayMoment = PM;
		}
	}
	
	/**
	 * Generates a new date given a String holding a formated date.
	 * @param s String representing a formated date.
	 * @throws NumberFormatException when the given String is not a formated date.
	 */
	public Date(String s) throws NumberFormatException, IndexOutOfBoundsException{
		String[] parts = s.split(" ");
		String dateParts = parts[0];
		String hourParts = parts[1];
		String dayT = parts[2];
		
		String[] dp = dateParts.split("-");
		year = Integer.parseInt(dp[0]);
		month = Integer.parseInt(dp[1]);
		day = Integer.parseInt(dp[2]);
		
		String[] hp = hourParts.split(":");
		int preHour = Integer.parseInt(hp[0]);
		minutes = Integer.parseInt(hp[1]);
		
		if(dayT.equals(AM)) {
			dayMoment = AM;
			hour = preHour;
		}else if(dayT.equals(PM)){
			dayMoment = PM;
			hour = preHour +12;
		}
	}
	
	/**
	 * Returns the date in format YYYY-MM-DD
	 * @return The date in the specified format.
	 */
	public String getDate() {
		String msg = String.format("%4d-%02d-%02d", year, month, day);
		return msg;
	}
	
	/**
	 * Returns the time in format HH:MM DM
	 * @return The time in a string format.
	 */
	public String getTime() {
		int normalHour = 0;
		if(hour < 11 || hour == 11 && minutes <= 59) {
			normalHour = hour;
		}else {
			normalHour = hour-12;
		}
		String msg = String.format("%02d:%02d %s", normalHour, minutes, dayMoment);
		return msg;
	
	}
	
	/**
	 * Compares each date to the date received as a parameter
	 * @param d The date to be compared to.
	 * @return -1 if this date is before the one received as a parameter, 1 if it's later and 0 if they're at the same time.
	 */
	public int compareTo(Date d) {
		int compared = 0;
		if(d.getYear() == year) {
			if(d.getMonth() == month) {
				if(d.getDay() == day) {
					if(d.getHour() == hour) {
						if(minutes < d.getMinutes()) {
							compared = -1;
						}else if( minutes > d.getMinutes()) {
							compared = 1;
						}else {
							compared = 0;
						}
					}else if(hour < d.getHour()) {
						compared = -1;
					}else {
						compared = 1;
					}
				}else if(day < d.getDay()) {
					compared = -1;
				}else {
					compared = 1;
				}
			}else if(month < d.getMonth()) {
				compared = -1;
			}else {
				compared = 1;
			}
		}else if(year < d.getYear()) {
			compared = -1;
		}else {
			compared = 1;
		}
		return compared;
	}

	/**Returns the moment of the day.
	 * @return the dayMoment
	 */
	public String getDayMoment() {
		return dayMoment;
	}

	/**Returns the calendar day.
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**Returns the calendar numerical month.
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**Returns the year.
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**Returns an hour.
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**Returns minutes
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Returns a String representation of this object.
	 */
	public String toString() {
		return getDate() + "-" + getTime();
	}
	
}//End of class
