package mx.axxib.aforedigitalgt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static String getNowStringDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(getNowDate());
	}
	
	public static Date getNowDate() {
		return Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City")).getTime();
	}
}
