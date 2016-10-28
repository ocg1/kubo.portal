package mx.com.kubo.managedbeans.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <code>BvCalendar</code> is an class based on Calendar for converting
 * between a <code>Date</code> object and a BvLong defined 
 * for all Applications developed as Visual Basic Specification (Day 0 = 30 DIC
 * 1989). This class is Client/Server side
 * 
 * <P>
 * 
 * Other features:
 * <ul>
 * <li>You must remember months from 0 to 11
 * <li>It convert hours/minutes/seconds to Double using method
 * <code>TimetoBvDouble()</code>
 * <li>It compares Dates
 * <li>It returns the difference in days between 2 dates
 * <li>It calculates the date in Julian format using method
 * <code>toJulianDate()</code>
 * <li>toBvDateTime() date & time in double format(BV format)
 * <li>etc.
 * </ul>
 * <P>
 * 
 * @author Angel Bartolome
 * @author Julio Zaragoza
 * @version 1.0 08/08/2000
 */

public class ConvertCalendar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//86400000
	/**    */
	private static final long MIN_IN_MILLISECONDS = 60 * 1000;
	private static final long HOUR_IN_MILLISECONDS = 60 * MIN_IN_MILLISECONDS;	
	private static final long DAY_IN_MILLISECONDS = 24 * HOUR_IN_MILLISECONDS;

	/**    */
	//private static final long HourMillis = 60 * 60 * 1000;
	//offset to apply
	//Java Date 0 = 1 JAN 1970
	//Visual Basic Date 0 = 30 DIC 1989
	private static final long VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS = 25569;

	private static final long JULIAN_OFFSET = 59666;

	private Calendar calendar;
	private long javaDayLong; // N�mero de dias desde el 01/01/1970
	private long bvDayLong; // N�mero de dias desde el 30/12/1899 
	private double timePart = 0; // Decimal entre 0 y 1 que representa hh:mm. Ej: 0.5 -> 12:00

	private static final String CR = System.getProperty("line.separator"); // carriage return

	//remember months from 0 to 11
	public final static int JANUARY = 0;
	public final static int FEBRUARY = 1;
	public final static int MARCH = 2;
	public final static int APRIL = 3;
	public final static int MAY = 4;
	public final static int JUNE = 5;
	public final static int JULY = 6;
	public final static int AUGUST = 7;
	public final static int SEPTEMBER = 8;
	public final static int OCTOBER = 9;
	public final static int NOVEMBER = 10;
	public final static int DECEMBER = 11;
	public final static int UNDECIMBER = 12;
	
	public final static int AM = 0;
	public final static int PM = 1;

	/**
	 * Set Time Zone
	 *  
	 */
	private void initializeCalendar() 
	{
		initializeCalendar(null);
	}

	private void initializeCalendar(String timeZoneID) 
	{
		TimeZone pdt = null;
		
		if (timeZoneID != null)
			pdt = TimeZone.getTimeZone(timeZoneID);
		else
			pdt = TimeZone.getDefault();
		
		calendar = Calendar.getInstance(pdt);
		calendar.clear();
	}
	
	/**
     * Constructs a ConvertCalendar with the default DateTime
     */
    public ConvertCalendar () {
    	this(new Date ());
    }
    
	/**
	 * Constructs a ConvertCalendar with the Time in double it doesn't matter the
	 * date
	 * 
	 * @param time
	 *            Time in double
	 */
	public ConvertCalendar(double time) {
		timePart = time;
		initializeCalendar();
		if (java.lang.Math.floor(time) > 0) {
			javaDayLong = (long) ((java.lang.Math.floor(time) - VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS + (time - java.lang.Math.floor(time))) * 
							DAY_IN_MILLISECONDS);
		} else {
			javaDayLong = (long) ((time) * DAY_IN_MILLISECONDS);
		}

		Date MyDate = new Date(javaDayLong);
		calendar.setTime(MyDate);

		//**** purge GTM and DayLight Saving Offset ****
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.ZONE_OFFSET));
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.DST_OFFSET));
		bvDayLong = 0;
	}

	/**
	 * Constructs a ConvertCalendar with the Time in BvLong (Business Specification
	 * DateTime
	 * 
	 * @param newBvDayLong
	 *            Time in BvLong
	 */
	public ConvertCalendar(long newBvDayLong) {
		initializeCalendar();
		bvDayLong = newBvDayLong; 
		javaDayLong = newBvDayLong - VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS;
		Date date = new Date(javaDayLong * DAY_IN_MILLISECONDS);
		timePart =  javaTimeToBvLongTime(date);
		calendar.setTime(date);		
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.ZONE_OFFSET));
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.DST_OFFSET));		
	}

	/**
	 * Constructs a ConvertCalendar with the Date
	 * 
	 * @param date
	 *            Time in Date
	 */
	public ConvertCalendar(Date date) {			
		ObjectNull(date);
		initializeCalendar();		
		timePart =  javaTimeToBvLongTime(date);
		long offsetInMilliSeconds = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.ZONE_OFFSET) + 
									Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DST_OFFSET);	
		calendar.setTime(date);
		bvDayLong = ((calendar.getTimeInMillis() + offsetInMilliSeconds) / DAY_IN_MILLISECONDS) + 
					VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS;
		javaDayLong = bvDayLong - VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS;
	}

	/**
	 * Constructs a ConvertCalendar with the Year, Month, Date remember months from 0
	 * to 11
	 * 
	 * @param aYear
	 *            Year in format <code>int</code>
	 * @param aMonth
	 *            Month in format <code>int</code>
	 * @param aDay
	 *            Date in format <code>int</code>
	 */
	public ConvertCalendar(int aYear, int aMonth, int aDay) {
		initializeCalendar();
		calendar.set(Calendar.YEAR, aYear);
		calendar.set(Calendar.MONTH, aMonth);
		calendar.set(Calendar.DATE, aDay);

		Date aDate = calendar.getTime();
		javaDayLong = aDate.getTime() / DAY_IN_MILLISECONDS;
		bvDayLong = javaDayLong + VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS;
		
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.ZONE_OFFSET));
		calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.DST_OFFSET));		
	}

	/**
	 * Gets this Calendar's current time.
	 * 
	 * @return the current time.
	 */
	public Date getDate() {
		return calendar.getTime();
	}

	/**
	 * Return a string representation of this ConvertCalendar based on
	 * <code>Calendar<code>. This method is intended to be
	 * used only for debugging purposes, and the
	 * format of the returned string may vary between implementations.
	 * The returned string may be empty but may not be <code>null</code>.
	 *
	 * @return a string representation of this ConvertCalendar.
	 */
	public String toString() {
		return calendar.getTime().toString();
	}

	/**
	 * Format a ConvertCalendar -> dd-mm-yyyy
	 * 
	 * @return ConvertCalendar formatted (string)
	 */
	public String formatLarge() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.getDate());
	}

	/**
	 * Format a ConvertCalendar -> dd-mm-yyyy
	 * 
	 * @return ConvertCalendar formatted (string)
	 */
	public String formatSmall() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		return formatter.format(this.getDate());
	}

	/**
	 * Format a ConvertCalendar -> Julian Date (long)
	 * 
	 * @return ConvertCalendar formatted (long)
	 */
	public long toJulianDate() {
		return this.toBvLong() + JULIAN_OFFSET;
	}

	/**
	 * Format a ConvertCalendar -> long
	 * 
	 * @return ConvertCalendar formatted long
	 */
	public long toLong() {
		return javaDayLong;
	}

	/**
	 * Format a ConvertCalendar -> BvLong
	 * 
	 * @return ConvertCalendar formatted BvLong
	 */
	public long toBvLong() {
		return bvDayLong;
	}

	/**
	 * Format a ConvertCalendar -> long
	 * 
	 * @return ConvertCalendar formatted long
	 */
	public long toUtilDateLong() {
		return javaDayLong * DAY_IN_MILLISECONDS;
	}	
	
	/**
	 * Format a ConvertCalendar -> double
	 * 
	 * @return ConvertCalendar formatted double
	 */
	public double toBvDateTime() {
		return (this.toBvLong() + this.TimetoBvDouble());
	}

	/**
	 * Format a ConvertCalendar -> double
	 * 
	 * @return ConvertCalendar formatted double
	 */
	public double TimetoBvDouble() {
		return timePart;
	}

	/**
	 * Days passed from
	 */
	public long DatePassInit() {
		return (javaDayLong / DAY_IN_MILLISECONDS);
	}

	/**
	 * Rest of time
	 */
	private double RestTime() {
		return ((double) (javaDayLong) / (double) DAY_IN_MILLISECONDS)
				- Math.floor((double) (javaDayLong) / (double) DAY_IN_MILLISECONDS);
	}

	/**
	 * Compares the aBvLong field.
	 * 
	 * @param when
	 *            the ConvertCalendar to be compared with this ConvertCalendar.
	 * @return true if the current time of this ConvertCalendar is after the time of
	 *         ConvertCalendar when; false otherwise.
	 */
	public boolean after(ConvertCalendar when) {
		return bvDayLong > when.toBvLong();
	}

	/**
	 * Compares the aBvLong field.
	 * 
	 * @param when
	 *            the ConvertCalendar to be compared with this ConvertCalendar.
	 * @return true if the current time of this ConvertCalendar is before the time of
	 *         ConvertCalendar when; false otherwise.
	 */
	public boolean before(ConvertCalendar when) {
		return bvDayLong < when.toBvLong();
	}

	/**
	 * Compares this ConvertCalendar to the other ConvertCalendar. The result are
	 * <code>-1</code> if <,<code>0</code> if = and <code>-1</code> if >
	 * that the ConvertCalendar to compare with.
	 * 
	 * @return <code>int</code> depends on the result.
	 */
	public int compareTo(ConvertCalendar anotherDate) {
		long mthisDate = bvDayLong;
		long manotherDate = anotherDate.toBvLong();
		return (mthisDate < manotherDate ? -1 : (mthisDate == manotherDate ? 0
				: 1));
	}

	/**
	 * Compares this ConvertCalendar to the other Object. The result are
	 * <code>-1</code> if <,<code>0</code> if = and <code>-1</code> if >
	 * that the ConvertCalendar to compare with.
	 * 
	 * @return <code>int</code> depends on the result.
	 */
	public int compareTo(Object o) {
		return compareTo((ConvertCalendar) o);
	}

	/**
	 * Calculates the difference in days between this ConvertCalendar and the other
	 * Object. The result can be positive or negative
	 * 
	 * @return <code>long</code> difference in days.
	 */
	public long Diff(ConvertCalendar anotherDate) {
		long mthisDate = bvDayLong;
		long manotherDate = anotherDate.toBvLong();
		return (mthisDate - manotherDate);
	}

	/**
	 * Return a string representation of this ConvertCalendar. This method is rather
	 * complete than <code>toString()</code>. It's used only for debugging
	 * purposes.
	 * 
	 * @return a string representation of this ConvertCalendar.
	 */
	public String moreInfo() {
		int h = 12;
		int m = 0;
		int s = 0;
		int millis = 0;
		long result = (((((h * 60) + m) * 60) + s) * 1000) + millis;
		return CR + "YEAR: " + calendar.get(Calendar.YEAR) + " | " + "MONTH: "
				+ calendar.get(Calendar.MONTH) + " | " + "DATE: "
				+ calendar.get(Calendar.DATE) + CR +

				"ERA: " + calendar.get(Calendar.ERA) + CR + "WEEK_OF_YEAR: "
				+ calendar.get(Calendar.WEEK_OF_YEAR) + CR + "WEEK_OF_MONTH: "
				+ calendar.get(Calendar.WEEK_OF_MONTH) + CR + "DAY_OF_MONTH: "
				+ calendar.get(Calendar.DAY_OF_MONTH) + CR + "DAY_OF_YEAR: "
				+ calendar.get(Calendar.DAY_OF_YEAR) + CR + "DAY_OF_WEEK: "
				+ calendar.get(Calendar.DAY_OF_WEEK) + CR
				+ "DAY_OF_WEEK_IN_MONTH: "
				+ calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + CR + "AM_PM: "
				+ calendar.get(Calendar.AM_PM) + CR +

				"HOUR: " + calendar.get(Calendar.HOUR) + " | " + "MINUTE: "
				+ calendar.get(Calendar.MINUTE) + " | " + "SECOND: "
				+ calendar.get(Calendar.SECOND) + " | " + "MILLISECOND: "
				+ calendar.get(Calendar.MILLISECOND) + CR +

				"HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY) + CR
				+ "ZONE_OFFSET: "
				+ (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)) + CR
				+ "DST_OFFSET: "
				+ (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)) + CR
				+ "REST: " + (this.RestTime()) + CR + "REST: " + (result) + CR;
	}

	/**
	 * Calculate age between initial an final date Fecha de creaci�n:
	 * (27/02/2001 11:34:19)
	 * 
	 * @return java.lang.Integer
	 * @param d1
	 *            java.util.Date Initial date
	 * @param d2
	 *            java.util.Date Final date
	 */
	public static Integer calculateAge(Date d1, Date d2) {
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		Calendar c3 = (Calendar) c2.clone();
		c3.setTime(d1);
		Calendar c1 = (Calendar) c2.clone();
		c1.set(Calendar.YEAR, c3.get(Calendar.YEAR));
		c1.set(Calendar.MONTH, c3.get(Calendar.MONTH));
		c1.set(Calendar.DAY_OF_MONTH, c3.get(Calendar.DAY_OF_MONTH));
		int age = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		c2.set(Calendar.YEAR, c1.get(Calendar.YEAR));
		if (c1.after(c2)) {
			age--;
		}
		return new Integer(age);
	}

	/**
	 * Inserte aqu� la descripcInitial�n del m�todo. Fecha de creacInitial�n:
	 * (27/02/2001 11:38:47)
	 * 
	 * @return java.lang.Integer
	 * @param d1
	 *            java.util.Date
	 * @param d2
	 *            java.util.Date
	 */
	public static Integer calculateMonths(Date d1, Date d2) {
		Calendar cFinal = Calendar.getInstance();
		cFinal.setTime(d2);
		Calendar cAux = (Calendar) cFinal.clone();
		cAux.setTime(d1);
		Calendar cInitial = (Calendar) cFinal.clone();
		cInitial.set(Calendar.YEAR, cAux.get(Calendar.YEAR));
		cInitial.set(Calendar.MONTH, cAux.get(Calendar.MONTH));
		cInitial.set(Calendar.DAY_OF_MONTH, cAux.get(Calendar.DAY_OF_MONTH));
		if (cInitial.after(cFinal)) {
			return new Integer(0);
		}
		// full years
		int y = cFinal.get(Calendar.YEAR) - cInitial.get(Calendar.YEAR);
		cFinal.set(Calendar.YEAR, cInitial.get(Calendar.YEAR));
		if (cInitial.after(cFinal)) {
			y--;
		}
		// full months
		int m = 0;
		if (cInitial.after(cFinal)) {
			m = 12 - (cInitial.get(Calendar.MONTH) - cFinal.get(Calendar.MONTH));
		} else {
			m = cFinal.get(Calendar.MONTH) - cInitial.get(Calendar.MONTH);
		}
		cFinal.set(Calendar.MONTH, cInitial.get(Calendar.MONTH));
		if (cInitial.after(cFinal)) {
			m--;
		}
		int months = 12 * y + m;
		return new Integer(months);
	}

	/**
	 * Calculate age between initial an final date Fecha de creaci�n:
	 * (27/02/2001 11:34:19)
	 * 
	 * @return java.lang.Integer
	 * @param d1
	 *            java.util.Date Initial date
	 * @param c2
	 *            Calendar Final date
	 */
	public static Integer calculateAge(Date d1, Calendar cPeriod) {
		Calendar c2 = (Calendar) cPeriod.clone();
		Calendar c3 = (Calendar) c2.clone();
		c3.setTime(d1);
		Calendar c1 = (Calendar) c2.clone();
		c1.set(Calendar.YEAR, c3.get(Calendar.YEAR));
		c1.set(Calendar.MONTH, c3.get(Calendar.MONTH));
		c1.set(Calendar.DAY_OF_MONTH, c3.get(Calendar.DAY_OF_MONTH));
		int age = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		c2.set(Calendar.YEAR, c1.get(Calendar.YEAR));
		if (c1.after(c2)) {
			age--;
		}
		return new Integer(age);
	}

	/**
	 * Inserte aqu� la descripcInitial�n del m�todo. Fecha de creacInitial�n:
	 * (27/02/2001 11:38:47)
	 * 
	 * @return java.lang.Integer
	 * @param d1
	 *            java.util.Date
	 * @param cFinal
	 *            Calendar
	 */
	public static Integer calculateMonths(Date d1, Calendar cF) {
		Calendar cFinal = (Calendar) cF.clone();
		Calendar cAux = (Calendar) cF.clone();
		cAux.setTime(d1);
		Calendar cInitial = (Calendar) cFinal.clone();
		cInitial.set(Calendar.YEAR, cAux.get(Calendar.YEAR));
		cInitial.set(Calendar.MONTH, cAux.get(Calendar.MONTH));
		cInitial.set(Calendar.DAY_OF_MONTH, cAux.get(Calendar.DAY_OF_MONTH));
		if (cInitial.after(cFinal)) {
			return new Integer(0);
		}
		// full years
		int y = cFinal.get(Calendar.YEAR) - cInitial.get(Calendar.YEAR);
		cFinal.set(Calendar.YEAR, cInitial.get(Calendar.YEAR));
		if (cInitial.after(cFinal)) {
			y--;
		}
		// full months
		int m = 0;
		if (cInitial.after(cFinal)) {
			m = 12 - (cInitial.get(Calendar.MONTH) - cFinal.get(Calendar.MONTH));
		} else {
			m = cFinal.get(Calendar.MONTH) - cInitial.get(Calendar.MONTH);
		}
		cFinal.set(Calendar.MONTH, cInitial.get(Calendar.MONTH));
		if (cInitial.after(cFinal)) {
			m--;
		}
		int months = 12 * y + m;
		return new Integer(months);
	}

	private void ObjectNull(Object mObj) {
		if (mObj == null) {
			throw new IllegalArgumentException();
		}
	}
	
	public static long javaDateLongToBvDateLong(long javaDateLong)
	{
		return javaDateLong + VISUALBASIC_TO_JAVA_OFFSET_IN_DAYS;
	}
	
	public static double javaTimeToBvLongTime(Date time)
	{
		TimeZone pdt = TimeZone.getDefault();
		Calendar calendar = Calendar.getInstance(pdt);
		calendar.setTime(time);
		calendar.add(Calendar.MILLISECOND, + calendar.get(Calendar.ZONE_OFFSET));
		calendar.add(Calendar.MILLISECOND, + calendar.get(Calendar.DST_OFFSET));
		time = calendar.getTime();
		long javaMilliSecondsLong = time.getTime();
		return ((double) (javaMilliSecondsLong) / (double) DAY_IN_MILLISECONDS)
				- Math.floor((double) (javaMilliSecondsLong) / (double) DAY_IN_MILLISECONDS);
	}
}