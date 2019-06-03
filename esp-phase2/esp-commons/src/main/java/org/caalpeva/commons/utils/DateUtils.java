package org.caalpeva.commons.utils;

import java.text.SimpleDateFormat;

/**
 * Clase auxiliar que facilita la manipulación de fechas y tiempos.
 */
public class DateUtils {
	
	public static final SimpleDateFormat DATE_FORMAT_TO_ORDER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final String FORMAT_DDHHMMSS = "%d %02d:%02d:%02d%s";
	private static final String FORMAT_HHMMSS = "%02d:%02d:%02d%s";
    private static final String FORMAT_MMSS = "%02d:%02d%s";
    private static final String FORMAT_SS = "%02d%s";
    
    private static final long MILLIS_IN_A_SECOND = 1000;
    private static final long MILLIS_IN_A_MINUTE = 60 * MILLIS_IN_A_SECOND;
    private static final long MILLIS_IN_A_HOUR = 60 * MILLIS_IN_A_MINUTE;
    private static final long MILLIS_IN_A_DAY = 24 * MILLIS_IN_A_HOUR;
	
	/**
	 * Método encargado de formatear adecuadamente según el tiempo especificado
	 * @param timeInMillis Tiempo en milisegundos
	 * @return Formato de tiempo transcurrido
	 */
	public static String formatElapsedTime(long timeInMillis) {
		return formatElapsedTime(timeInMillis, false, true);
	}
	
	/**
	 * Método encargado de formatear adecuadamente según el tiempo especificado
	 * @param timeInMillis Tiempo en milisegundos
	 * @param showMillis
	 * @return Formato de tiempo transcurrido
	 */
	public static String formatElapsedTime(long timeInMillis, boolean showAlwaysHHMM, boolean showMillis) {
		long days = 0;
		long hours = 0;
	    long minutes = 0;
	    long seconds = 0;
	    
	    timeInMillis = Math.abs(timeInMillis);
	    if (timeInMillis >= MILLIS_IN_A_DAY) {
        	days = timeInMillis / MILLIS_IN_A_DAY;
        	timeInMillis -= days * MILLIS_IN_A_DAY;
        }
	    
        if (timeInMillis >= MILLIS_IN_A_HOUR) {
        	hours = timeInMillis / MILLIS_IN_A_HOUR;
        	timeInMillis -= hours * MILLIS_IN_A_HOUR;
        }
        
        if (timeInMillis >= MILLIS_IN_A_MINUTE) {
        	minutes = timeInMillis / MILLIS_IN_A_MINUTE;
        	timeInMillis -= minutes * MILLIS_IN_A_MINUTE;
        }
        
        if (timeInMillis > MILLIS_IN_A_SECOND) {
        	seconds = timeInMillis / MILLIS_IN_A_SECOND;
        	timeInMillis -= seconds * MILLIS_IN_A_SECOND; 
        }
        
        String millis = showMillis? String.format(".%03d", timeInMillis): "";
        if (days > 0) {
            return String.format(FORMAT_DDHHMMSS, days, hours, minutes, seconds, millis);
        } else if (hours > 0) {
            return String.format(FORMAT_HHMMSS, hours, minutes, seconds, millis);
        } else if (minutes > 0) {
            return String.format(FORMAT_MMSS, minutes, seconds, millis);
        } else {
            return String.format(showAlwaysHHMM? "00:%02d%s": FORMAT_SS, seconds, millis);
        }
    }
}