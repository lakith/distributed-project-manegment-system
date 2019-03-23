package com.itemsharing.cloudConfig.userService.component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GetToday {

    public static Date getToday(){

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Colombo");
        cal.setTimeZone(tz);
        return cal.getTime();
    }

}
