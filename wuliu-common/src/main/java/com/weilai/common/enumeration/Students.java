package com.weilai.common.enumeration;

import java.util.Calendar;

public class Students {

	public static void main(String[] args) {
//		int a =OrderType.派单.getCode();
//		
		Calendar c = Calendar.getInstance();
		int day  = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
	}

}
