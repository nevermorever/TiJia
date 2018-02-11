package com.zyjd.tijia.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {

    public static String transferDatetimeToString(Date date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-m-d");
        String result = fmt.format(date);
        return result;
    }
}
