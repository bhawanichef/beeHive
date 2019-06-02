package com.deloitte.jnj.LegalHoldingAccelerator;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class StandardUtility {
	public static String getTimeStamp() {
		DecimalFormat formatter = new DecimalFormat("00");
		LocalDateTime now = LocalDateTime.now();
		return "" + now.getYear() + formatter.format(now.getMonthValue()) + formatter.format(now.getDayOfMonth())
				+ formatter.format(now.getHour()) + formatter.format(now.getMinute()) + formatter.format(now.getSecond());
	}

	public static void main(String[] args) {
		System.out.println(getTimeStamp());
	}
}
