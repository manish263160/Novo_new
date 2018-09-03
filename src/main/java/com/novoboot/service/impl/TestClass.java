package com.novoboot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		// substract 7 days
		// If we give 7 there it will give 8 days back
		for (int i = 1; i < 7; i++) {
			
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+i);
			Date myDate = cal.getTime();
			System.out.println("myDate===="+myDate);
		}

		// convert to date
*/	

		String format = "dd-MM-yyyy";
		
		String format1 = "dd-MM-yyyy hh:mm a";
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat sdf1 = new SimpleDateFormat(format1);

		Calendar cal = Calendar.getInstance();
		String date1 = sdf.format(cal.getTime());
		String time1 = "07:00 AM";
		String time2 = "07:00 PM";
		
		Date dateObj1;
		Date dateObj2;
		try {
			dateObj1 = sdf1.parse(date1 + " " + time1);
			dateObj2 = sdf1.parse(date1 + " " + time2);
		
		System.out.println("Date Start: "+dateObj1);
		System.out.println("Date End: "+dateObj2);

		SimpleDateFormat lal = new SimpleDateFormat("hh:mm a");

		
//		Long currentLocalTIme = cal.getTime().getTime();
		String originalString = "04-09-2018 09:00:02";
		Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(originalString);
		String newString = new SimpleDateFormat("hh:mm a").format(date); // 9:00
		System.out.println("new string : "+newString);
		
		
		Long currentLocalTIme = date.getTime();
		System.out.println("currentLocalTIme: "+new SimpleDateFormat("hh:mm a").format(date));
		//Date d = new Date(dateObj1.getTime() + 3600000);

		List<String> getStringDate = new ArrayList<>();
		long dif = dateObj1.getTime();
		long last = dateObj1.getTime();
		while (dif <= dateObj2.getTime()) {
			Long storeDiff= dif;
		    Date slot = new Date(dif);
		    System.out.println("Hour Slot --->" + lal.format(slot));
		    dif += (3600000 * 3);
		    if(dif <= dateObj2.getTime()) {
		    	if(currentLocalTIme.compareTo(storeDiff)  * currentLocalTIme.compareTo(dif) > 0) {
		    getStringDate.add(lal.format(slot)+"-"+lal.format(new Date(dif))+" ::"+"blocked");
		    	}else if(currentLocalTIme.compareTo(last-(3600*3)) > 0 && currentLocalTIme.compareTo(last)> 0 ) {
		    		getStringDate.add(lal.format(slot)+"-"+lal.format(new Date(dif))+" ::"+"blocked");		
		    	}
		    	else {
		    		getStringDate.add(lal.format(slot)+"-"+lal.format(new Date(dif))+" ::"+"open");
		    	}
		    }
		}
		System.out.println(getStringDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
