package mx.axxib.aforedigitalgt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	/**
	 * Aplica un formato de fecha a la fecha actual   
	 * @param format Es el formato deseado
	 * @return Devuelve la fecha en formato string
	 */
	public static String getNowStringDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(getNowDate());
	}
	
	/**
	 * Obtiene la fecha actual en zona horario de México
	 * @return Devuelve en formato fecha
	 */
	public static Date getNowDate() {
		return Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City")).getTime();
	}
	
	/**
	 * Valida que la fecha final sea mayor o igual que la inicial
	 * @param ini Fecha inicial
	 * @param fin Fecha final
	 * @return Devuelve verdadero en caso de ser válido
	 */
	public static boolean isValidDates(Date ini, Date fin) {
		return ini.compareTo(fin) <= 0;
	}
}
