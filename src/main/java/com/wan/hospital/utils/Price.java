package com.wan.hospital.utils;


public class Price {
    public  static float getPrice(String date) {
        float price;
        String d1 = "00:00";
        String d2 = "11:59";
        String d3 = "12:00";
        String d4 = "23:59";
        String d = date.substring(11);
        int r1 = d.compareTo(d1);
        int r2 = d.compareTo(d2);
        int r3 = d.compareTo(d3);
        int r4 = d.compareTo(d4);
        if (r1 >= 0 && r2 <= 0) {
            price = 5;
            return price;
        } else if(r3 >= 0 && r4 <=0) {
            price = 10;
            return price;
        } else {
           return 0;
        }
    }
}
