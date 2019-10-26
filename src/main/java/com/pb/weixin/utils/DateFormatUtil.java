package com.pb.weixin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public static Date getNowDateShort() throws ParseException {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
       // ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString);
        return currentTime_2;
    }
	
}
