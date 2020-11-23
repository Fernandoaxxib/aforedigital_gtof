package mx.axxib.aforedigitalgt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	public static String getNowStringDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(Calendar.getInstance().getTime());
	}
}
