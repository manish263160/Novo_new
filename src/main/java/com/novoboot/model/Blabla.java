package com.novoboot.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Blabla {

	public static void main(String[] args) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String str = "2018-09-08,2018-09-09";
		List<String> list = new ArrayList<>();
		list = Arrays.asList(str.split("\\s*,\\s*"));

		System.out.println("list===" + list);

		List<String> date = new LinkedList<>();
		List<String> dateFinal = new LinkedList<>();
		for (int i = 0; i < 7; i++) {

			cal.add(Calendar.DATE, 1);
			System.out.println(sdf.format(cal.getTime()));
			if (!list.contains(sdf.format(cal.getTime())))
				date.add(sdf.format(cal.getTime()));
		}

		System.out.println("date===" + date.toString());

		String startTime = "13:00:00";
		String endTime = "18:00:00";

		SimpleDateFormat timeFormatAmPm = new SimpleDateFormat("hh:mm a");

		SimpleDateFormat timeFormat24 = new SimpleDateFormat("HH:mm:ss");
		
		Date de = new Date();
		String currenttime = timeFormat24.format(de.getTime());
	System.out.println("isTimeBetweenTwoTime==="+isTimeBetweenTwoTime(startTime,endTime,currenttime));

		
	}
	
	
	public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
            boolean valid = false;
            //Start Time
            java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(inTime);

            //Current Time
            java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkTime);

            //End Time
            java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(finTime);

            if (finalTime.compareTo(initialTime) < 0) {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
            }

            java.util.Date actualTime = calendar3.getTime();
            if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0) 
                    && actualTime.before(calendar2.getTime())) {
                valid = true;
            }
            return valid;
        } else {
            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
        }

    }
}
