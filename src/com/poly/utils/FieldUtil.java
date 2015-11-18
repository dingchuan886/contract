package com.poly.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FieldUtil {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy��MM��dd��");

	private static final SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");

	private static FieldUtil util = new FieldUtil();

	public static int diffHours(Date d) {
		if (d == null) {
			return 0;
		}

		long now = Calendar.getInstance().getTimeInMillis();
		long past = d.getTime();

		return (int) ((now - past) / 1000 / 3600);
	}

	public static String formatDate(String f, Date d) {
		return new SimpleDateFormat(f).format(d);
	}

	/**
	 * return a Date instance by hour and minute, the date parts is ignored
	 * 
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getDate(int hour, int minute) {
		Date date = null;

		Calendar cal = Calendar.getInstance();
		cal.set(2001, 1, 1, hour, minute);
		date = cal.getTime();

		return date;
	}

	/**
	 * return a Date instance by year and month and day
	 * 
	 * @param year
	 * @param month
	 *            , from 1 to 12
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		Date date = null;

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		date = cal.getTime();

		return date;
	}

	public static FieldUtil getInstance() {
		return util;
	}

/**
	 * check whether string has html tag(special character) such as ' <', '>',
	 * '"', '&', ' '
	 * 
	 * @param a
	 * @return
	 */
	public static boolean hasHtmlTag(String str) {
		if (FieldUtil.isEmpty(str)) {
			return false;
		}

		if (str.indexOf('>') != -1 || str.indexOf('<') != -1 || str.indexOf('"') != -1 || str.indexOf('&') != -1
				|| str.indexOf(' ') != -1) {
			return true;
		}

		return false;
	}

	/**
	 * whether a string can be parse to int
	 * 
	 * @param d
	 * @return
	 */
	public static boolean isDigit(String d) {
		if (FieldUtil.isEmpty(d) || d.matches("\\d+") == false) {
			return false;
		}

		return true;
	}

	/**
	 * check whether a string is a email format like xxx@xxx.xxx
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}

		return email.matches(".*@.*\\.+.*");
	}

	/**
	 * check whether a string is null or empty with 0 length
	 * 
	 * @param test
	 * @return
	 */
	public static boolean isEmpty(String test) {
		if (test == null || test.length() == 0 || test.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * check whether a int is a valid day between 1 and 31
	 * 
	 * @param day
	 * @return
	 */
	public static boolean isValidDay(int day) {
		if (day < 1 || day > 31) {
			return false;
		}

		return true;
	}

	/**
	 * check whether hour is a valid hour between 0 and 23
	 * 
	 * @param hour
	 * @return
	 */
	public static boolean isValidHour(int hour) {
		if (hour < 0 || hour > 23) {
			return false;
		}

		return true;
	}

	/**
	 * check whether m is a valid minute between 0 and 59
	 * 
	 * @param m
	 * @return
	 */
	public static boolean isValidMinute(int m) {
		if (m < 0 || m > 59) {
			return false;
		}

		return true;
	}

	/**
	 * check whether a int is a valid month between 1 and 12
	 * 
	 * @param month
	 * @return
	 */
	public static boolean isValidMonth(int month) {
		if (month < 1 || month > 12) {
			return false;
		}

		return true;
	}

	/**
	 * check whether a int is a valid year between 1900 and 2046
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isValidYear(int year) {
		if (year < 1900 || year > 2150) {
			return false;
		}

		return true;
	}

	/**
	 * check whether date1 is later than date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean laterThan(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		if (date1.compareTo(date2) > 0) {
			return true;
		}

		return false;
	}

	/**
	 * convert date to a string
	 * 
	 * @param date
	 * @return
	 */
	public static String toDate(Date date) {
		if (date == null) {
			return "";
		}

		return sdfdate.format(date);
	}

	public static String toDate2(Date date) {
		if (date == null) {
			return "";
		}

		return sdf.format(date);
	}

	/**
	 * convert date to a string
	 * 
	 * @param date
	 * @return
	 */
	public static String toDatetime(Date date) {
		return toDate(date) + " " + toTime(date);
	}

	/**
	 * convert size to xxKB or xxMB format
	 * 
	 * @param size
	 * @return
	 */
	public static String toSize(int size) {
		String str = "";

		if (size >= 0 && size < 1024) {
			str = "" + size + "bytes";
		} else if (size < 1024 * 1024) {
			str = "" + size / 1024 + "K";
		} else if (size < 1024 * 1024 * 1024) {
			str = "" + (float) size / 1024 / 1024;
			str = str.substring(0, str.indexOf('.') + 2) + "M";
		}

		return str;
	}

	/**
	 * convert date to a string only shows hour and minute, HH:MM
	 * 
	 * @param date
	 * @return
	 */
	public static String toTime(Date date) {
		if (date == null) {
			return "";
		}

		return sdftime.format(date);
	}

	/**
	 * @return ��ǰʱ��
	 */
	public static String getNowStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeStart = System.currentTimeMillis();
		Date now = new Date(timeStart);
		String str = sdf.format(now);
		return str;
	}

	private FieldUtil() {

	}

	public static void main(String args[]) {
		String ss = "dsfasdfsdkjflsakj";
		System.out.println("=====" + getNowStr());
	}
}
