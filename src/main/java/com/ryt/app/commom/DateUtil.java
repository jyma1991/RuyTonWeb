package com.ryt.app.commom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	public static final SimpleDateFormat yhdFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * string -- date
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date getYMDDate(String strDate) throws ParseException{
		if(StringUtils.isEmpty(strDate)){
			return new Date();
		}
		return yhdFormat.parse(strDate);
	}
}
