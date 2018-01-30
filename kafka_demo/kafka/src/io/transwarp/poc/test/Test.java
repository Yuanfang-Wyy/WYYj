package io.transwarp.poc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test
{
	public static void main(String[] args) throws ParseException
	{
		String base = "2017-08-04 08:00:00";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.parse(base);
		Calendar c = Calendar.getInstance();
		System.out.println(formatter.format(date));
		c.setTime(date);
		c.add(Calendar.SECOND, 1);
		date = c.getTime();
		System.out.println(formatter.format(date));
	}

	public static String getTime(String time) throws ParseException
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.parse(time);
		Calendar c = Calendar.getInstance();
		System.out.println(formatter.format(date));
		c.setTime(date);
		c.add(Calendar.SECOND, 1);
		date = c.getTime();
		return formatter.format(date);
	}
}
