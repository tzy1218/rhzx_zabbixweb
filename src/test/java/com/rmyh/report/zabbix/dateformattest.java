package com.rmyh.report.zabbix;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateformattest {
	public static void main(String[] args) {
		SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(Long.parseLong("0000220000000"));
		String date2 = dateformat3.format(new Date(Long.parseLong("0000220001"+"000")));
	String date3 = dateformat3.format(date);
		System.out.println("date:"+date+";date2:"+date2+";date3:"+date3);
	}

}
