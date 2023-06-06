package com.wan.hospital.utils;

public class MyDate {
    public static String changTime(String time) {
        String[] s = time.split(" ");
        s[1] = s[1] + ":00";
        return s[0] + " " + s[1];
    }
}
