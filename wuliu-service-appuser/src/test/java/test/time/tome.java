package test.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class tome {
	public static void main(String[] args) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		// calendar.set(Calendar.HOUR_OF_DAY, hour);
		// calendar.set(Calendar.MINUTE, minute);
		// calendar.set(Calendar.SECOND, second);
		Date maxDate = calendar.getTime();
		System.out.println(maxDate);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(dateFormater.format(maxDate));

	}
}
