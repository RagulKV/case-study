package com.cognizant.truyum.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date convertToUtilDateFromSqlFormat(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		Date d1=sdf.parse(date);
		return d1;
		
	}
	
	public static String convertToStringDateFromUtilFormat(Date date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
		return sdf.format(date);
		
	}
	
	public static Date convertToUtilDateFromStringFormat(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
		Date d1=sdf.parse(date);
		return d1;
	}
	
	public static String convertToSqlDateFromUtilDate(Date date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String date1=sdf.format(date);
		return date1;
		
	}
	
	public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate=new java.util.Date(sqlDate.getTime());
		return utilDate;
		
	}
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate){
		
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
	public static java.util.Date convertSqlDateToUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate=null;
		
		utilDate=new java.util.Date(sqlDate.getTime());
		
		return utilDate;
	}
	
	
}
