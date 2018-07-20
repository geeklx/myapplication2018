package com.example.shining.libutils.utilslib.etc;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Time格式管理
 */
public class TimeUtil {

	public static final String FORMATER_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATER_DB_MS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMATER_DB_S = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATER_DB_S_WEEK = "yyyy-MM-dd  HH:mm  EEEE";
	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param formatString
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String format(Date date, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		return df.format(date);
	}
	public static String getformatTableTime(){
		return format(new Date(),FORMATER_DB_MS);
	}
	public static String formatTableTime(Date time){
		return format(time,FORMATER_DB_MS);
	}
	public static String formatWeekTime(Date time){
		return format(time,FORMATER_DB_S_WEEK);
	}

	/**
	 * 转换成日期
	 *
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date parse(String dateString, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date parseTableTime(String dateString){
		return parse(dateString,FORMATER_DB_MS);
	}

}