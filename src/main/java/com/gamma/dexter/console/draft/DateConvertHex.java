/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.console.draft;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConvertHex {

    private final static String HEX_DIGITS = "0123456789abcdef";

    public static String dateFormatConverter(String dateSample){


        String oldFormat = "MM/dd/yyyy hh:mm a";
        String newFormat = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String convertedDate = null;

        try {
            convertedDate= sdf2.format(sdf1.parse(dateSample));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String hhmmss(int frequency, String unit) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String stringDate = dateFormat.format(date);
  //      System.out.println("current date: " + stringDate);

        if (unit.equalsIgnoreCase("hours")) {
            cal.add(Calendar.HOUR_OF_DAY, -frequency *1);
        }else if (unit.equalsIgnoreCase("days")) {
            cal.add(Calendar.DATE, -frequency * 1);
        }else if (unit.equalsIgnoreCase("weeks")) {
            cal.add(Calendar.DATE, -frequency * 7);
        }else if (unit.equalsIgnoreCase("months")) {
            cal.add(Calendar.MONTH, -frequency * 1);
        }

         date = cal.getTime();
        stringDate = dateFormat.format(date);
//        System.out.println("subtracted date: " + stringDate);

        return stringDate;
    }

    public static String filterHex(String hexDate) {

        StringBuilder formatDate = new StringBuilder();
        for (int i = 0; i < hexDate.length(); i++) {
            if (HEX_DIGITS.contains(String.valueOf(hexDate.charAt(i)))) {
                formatDate.append(hexDate.charAt(i));
            }
        }
        return formatDate.toString();
    }

    public static String hexToAscii(String hex) {
        String formatHex = filterHex(hex.toLowerCase());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < formatHex.length(); i += 2) {
            String str = formatHex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return String.valueOf(output);
    }

    public static String convertDecimal(String hexDate) {

        String formatDate = filterHex(hexDate.toLowerCase());
        String year = formatDate.substring(0, 4);
        String month = formatDate.substring(4, 6);
        String day = formatDate.substring(6, 8);
        String hour = formatDate.substring(8, 10);
        String minute = formatDate.substring(10, 12);
        String second = formatDate.substring(12, 14);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year, 16));
        cal.set(Calendar.MONTH, (Integer.parseInt(month, 16) - 1));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day, 16));
        cal.set(Calendar.HOUR, Integer.parseInt(hour, 16));
        cal.set(Calendar.MINUTE, Integer.parseInt(minute, 16));
        cal.set(Calendar.SECOND, Integer.parseInt(second, 16));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(cal.getTime()).toString();
    }

    public static int durationToSeconds(String duration)
    {
        int days = 0;
//        System.out.println(duration);
        if(duration.contains("day"))
        {
             days = Integer.parseInt(duration.substring(0,duration.indexOf("day")-1));
            duration =duration.substring(duration.indexOf(",") + 2, duration.length());
//            System.out.println("days = " + days);
//            System.out.println(duration);
        }

        int hr = Integer.parseInt(duration.substring(0,duration.indexOf(":")));
//        System.out.println("hr = " + hr);
        duration =duration.substring(duration.indexOf(":") + 1, duration.length());
//        System.out.println(duration);
        int min = Integer.parseInt(duration.substring(0,duration.indexOf(":")));
//        System.out.println("min = "+min);
        int sec =Integer.parseInt(duration.substring(duration.indexOf(":")+1,duration.indexOf("."))) + 1;
//        System.out.println("sec ="+sec);

        return days * 24 *3600 + hr *3600 + min * 60 + sec;
    }

    public static void main(String args[]) throws IOException {

        DateConvertHex DateConvert = new DateConvertHex();
        String stringDate = DateConvert.filterHex("3c:97:0e:24:89:15");
        System.out.println(stringDate);
//        DateConvert.hhmmss(1, "weeks");
//        String stringDate1 = DateConvertHex.dateFormatConverter("3/3/2016 1:29 AM");
//        System.out.println(stringDate1);

//        System.out.println(DateConvertHex.durationToSeconds("1 day, 2:10:25.59"));
    }
}