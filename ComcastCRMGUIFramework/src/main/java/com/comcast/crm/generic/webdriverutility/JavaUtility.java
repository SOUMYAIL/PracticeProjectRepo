package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	Date dateObj;
	SimpleDateFormat sim;

	public int getRandomNumber() {
		Random ranDom = new Random();
		int randomInt = ranDom.nextInt(5000);
		return randomInt;

	}

	public String getSystemDateYYYYDDMM() {
		dateObj = new Date();
		sim = new SimpleDateFormat("YYYY-MM-dd");
		String actDate = sim.format(dateObj);
		return actDate;

	}

	public String getRequiredDateYYYYDDMM(int days) {
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sim.format(cal.getTime());

		return endDate;

	}

	public String captureTimeStamp() {
		//String time = new Date().toString().replace(" ", "_").replace(":", "_");
		String time = LocalDateTime.now().toString().replace(":", "_");

		return time;

	}

}
