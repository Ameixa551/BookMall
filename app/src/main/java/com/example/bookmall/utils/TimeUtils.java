package com.example.bookmall.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getCurDate(String pattern) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date());
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static long getCurDateMills(){
        return new Date().getTime();
    }

}
