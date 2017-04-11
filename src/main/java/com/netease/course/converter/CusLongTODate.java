package com.netease.course.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class CusLongTODate {
 
 // 短日期格式
 public static String DATE_FORMAT = "yyyy-MM-dd";
 
 // 长日期格式
 public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
 
 /**
  * 将日期格式的字符串转换为长整型
  * 
  * @param date
  * @param format
  * @return
  */
 public static long convert2long(String date, String format) {
 
   
     format = CusLongTODate.TIME_FORMAT;
    SimpleDateFormat sf = new SimpleDateFormat(format);
    try {
		return sf.parse(date).getTime();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
    return 01;
 }
 
 /**
  * 将长整型数字转换为日期格式的字符串
  * 
  * @param time
  * @param format
  * @return
  */
 public static String convert2String(long time, String format) {
  if (time > 0l) {
   SimpleDateFormat sf = new SimpleDateFormat(format);
   Date date = new Date(time);
   return sf.format(date);
  }
  return "";
 }
 
 /**
  * 获取当前系统的日期
  * 
  * @return
  */
 public static long curTimeMillis() {
  return System.currentTimeMillis();
 }
 
 
}