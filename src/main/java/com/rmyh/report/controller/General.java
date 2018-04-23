package com.rmyh.report.controller;

import java.util.regex.Pattern;

public class General {
	
//	public static void main(String[] args) {
//		System.out.println(isInteger("0.09"));
//	}

	public static boolean isInteger(String str) {
		
		if (str == null || str == "" || str.trim()=="") {
			return false;
		} else {
//			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
			Pattern pattern = Pattern.compile("^[-\\+]?([1-9][0-9]*\\.[0-9]+$|[1-9][0-9]*$|0+\\.[0-9]+|0)$");
			return pattern.matcher(str).matches();
		}
	}
	public static boolean isPercent(String str) {
		
		if (str == null || str == "" || str.trim()=="") {
			return false;
		} else {
//			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
			Pattern pattern = Pattern.compile("^[-\\+]?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)%$");
			return pattern.matcher(str).matches();
		}
	}

}
