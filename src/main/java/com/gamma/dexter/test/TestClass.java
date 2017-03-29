package com.gamma.dexter.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class TimeStampExample {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public static void main(String[] args) {

        //method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setTime(1479249799770L);
        System.out.println(sdf.format(timestamp));

    }

}
