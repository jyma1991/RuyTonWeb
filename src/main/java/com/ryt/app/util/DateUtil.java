package com.ryt.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String formateDate(long time) {
		java.util.Date dt = new Date(time);
		String sDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
		// 21:08:00
		System.out.println(sDateTime);

		return sDateTime;
	}

	/**
	 * long --->YMD 20140507
	 * 
	 * @param time
	 * @return
	 */
	public static String formateYMDDate(long time) {
		java.util.Date dt = new Date(time);
		String sDateTime = new SimpleDateFormat("yyyyMMdd").format(dt);
		return sDateTime;
	}

	public static String formateYMD(long time) {
		java.util.Date dt = new Date(time);
		String sDateTime = new SimpleDateFormat("yyyy-MM-dd").format(dt);
		return sDateTime;
	}

	public static Date parseYMD(String time) {
		if (StringUtils.isEmpty(time)) {
			return new Date();
		}
		try {
			return dateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();

	}

	/**
	 * �Ƿ�Ϊ����ʱ��
	 * 
	 * @return
	 */
	public static boolean isMoring() {
		GregorianCalendar ca = new GregorianCalendar();
		// ����
		if (ca.get(GregorianCalendar.AM_PM) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 在原基础上加减年数
	 * 
	 * @param year
	 * @return
	 */
	public static String addYear(int year, Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null) {
			date = new Date(System.currentTimeMillis());
		}
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		date = calendar.getTime();
		return formateYMD(date.getTime());
	}

	public static String dateToStr(Date time) {
		if (time == null) {
			time = new Date();
		}
		String sDateTime = new SimpleDateFormat("yyyy-MM-dd").format(time);
		return sDateTime;
	}

	// public static void main(String[] args) {
	// System.out.println(addYear(1));
	// }

}
