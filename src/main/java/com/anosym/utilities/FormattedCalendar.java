/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Luffy
 */
public class FormattedCalendar extends GregorianCalendar implements Serializable {

  static final long serialVersionUID = 399789964525l;

  public static String getISODateString(Calendar calendar) {
    if (calendar == null) {
      calendar = Calendar.getInstance();
      calendar.set(1972, 0, 1);
    }
    String year = calendar.get(Calendar.YEAR) + "",
            mon = (calendar.get(Calendar.MONTH) + 1) + "",
            day = calendar.get(Calendar.DATE) + "";
    if (year.length() != 4) {
      year = "20" + year;
    }
    if (mon.length() != 2) {
      mon = "0" + mon;
    }
    if (day.length() != 2) {
      day = "0" + day;
    }
    String str = year + "-" + mon + "-" + day;
    return str;
  }

  public FormattedCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
    super(year, month, dayOfMonth, hourOfDay, minute, second);
  }

  public FormattedCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    super(year, month, dayOfMonth, hourOfDay, minute);
  }

  public FormattedCalendar(int year, int month, int dayOfMonth) {
    super(year, month, dayOfMonth);
  }

  public FormattedCalendar(TimeZone zone, Locale aLocale) {
    super(zone, aLocale);
  }

  public FormattedCalendar(Locale aLocale) {
    super(aLocale);
  }

  public FormattedCalendar(TimeZone zone) {
    super(zone);
  }

  public FormattedCalendar() {
    super();
  }

  public FormattedCalendar(Calendar cal) {
    this(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
  }

  public static FormattedCalendar createInstance() {
    return new FormattedCalendar(Calendar.getInstance());
  }

  public static FormattedCalendar newInstance() {
    return createInstance();
  }

  @Override
  public String toString() {
    return toISOString(this);
  }

  public String getTimeString() {
    try {
      DateFormat ff = SimpleDateFormat.getTimeInstance();
      DateFormatter f = new DateFormatter(ff);
      return f.valueToString(this.getTime());
    } catch (ParseException ex) {
      return "";
    }
  }

  public String getDateString() {
    try {
      DateFormat ff = SimpleDateFormat.getDateInstance();
      DateFormatter f = new DateFormatter(ff);
      return f.valueToString(this.getTime());
    } catch (ParseException ex) {
      return "";
    }
  }

  public String getShortDateString() {
    try {
      DateFormat ff = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
      DateFormatter f = new DateFormatter(ff);
      return f.valueToString(this.getTime());
    } catch (ParseException ex) {
      return "";
    }
  }

  public static String getShortDateString(Calendar c) {
    try {
      DateFormat ff = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
      DateFormatter f = new DateFormatter(ff);
      return f.valueToString(c.getTime());
    } catch (ParseException ex) {
      return "";
    }
  }

  public static String toString(Calendar cal) {
    if (cal == null) {
      return "";
    }
    return new FormattedCalendar(cal).toString();
  }

  private int dayDifference(Calendar start, Calendar end) {
    int yearDiff = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) + 1;
    Calendar ref = new FormattedCalendar(start);
    int numdays = 0;
    for (int i = 0; i < yearDiff; i++) {
      numdays += start.getActualMaximum(Calendar.DAY_OF_YEAR);
      start.set(Calendar.YEAR, start.get(Calendar.YEAR) + (i + 1));
    }
    numdays -= (ref.getActualMaximum(Calendar.DAY_OF_YEAR) - ref.get(Calendar.DAY_OF_YEAR));
    numdays += end.get(Calendar.DAY_OF_YEAR);
    return numdays;
  }

  public int dayDifference(Calendar cal) {
    return this.dayDifference(this, cal);
  }

  public static Calendar getDate(Calendar calendar) {
    Calendar date = Calendar.getInstance();
    date.setTimeInMillis(0l);
    date.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    return date;
  }

  public static String toISOString(Calendar calendar) {
    if (calendar == null) {
      calendar = Calendar.getInstance();
      calendar.set(1972, 0, 1);
    }
    String year = calendar.get(Calendar.YEAR) + "",
            mon = (calendar.get(Calendar.MONTH) + 1) + "",
            day = calendar.get(Calendar.DATE) + "",
            hrs = calendar.get(Calendar.HOUR_OF_DAY) + "",
            mins = calendar.get(Calendar.MINUTE) + "",
            secs = calendar.get(Calendar.SECOND) + "";
    if (year.length() != 4) {
      year = "20" + year;
    }
    if (mon.length() != 2) {
      mon = "0" + mon;
    }
    if (day.length() != 2) {
      day = "0" + day;
    }
    if (hrs.length() != 2) {
      hrs = "0" + hrs;
    }
    if (mins.length() != 2) {
      mins = "0" + mins;
    }
    if (secs.length() != 2) {
      secs = "0" + secs;
    }
    String str = year + "-" + mon + "-" + day + " " + hrs + ":" + mins + ":" + secs;
    return str;
  }

  public static String toISOTimeString(Calendar calendar) {
    if (calendar == null) {
      calendar = Calendar.getInstance();
      calendar.set(1972, 0, 1);
    }
    String hrs = calendar.get(Calendar.HOUR_OF_DAY) + "",
            mins = calendar.get(Calendar.MINUTE) + "",
            secs = calendar.get(Calendar.SECOND) + "";
    if (hrs.length() != 2) {
      hrs = "0" + hrs;
    }
    if (mins.length() != 2) {
      mins = "0" + mins;
    }
    if (secs.length() != 2) {
      secs = "0" + secs;
    }
    String str = hrs + ":" + mins + ":" + secs;
    return str;
  }

  public static Calendar createInstance(String value) {
    try {
      DateFormat ff = SimpleDateFormat.getDateTimeInstance();
      DateFormatter f = new DateFormatter(ff);
      return (Calendar) f.stringToValue(value);
    } catch (ParseException ex) {
      Logger.getLogger(FormattedCalendar.class.getName()).log(Level.SEVERE, value, ex);
      return null;
    }
  }

  public static Calendar createInstanceIso(String value) {
    try {
      DateFormat ff = SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
      DateFormatter f = new DateFormatter(ff);
      return (Calendar) f.stringToValue(value);
    } catch (ParseException ex) {
      Logger.getLogger(FormattedCalendar.class.getName()).log(Level.SEVERE, value, ex);
      return null;
    }
  }

  public static Calendar newDateInstance() {
    Calendar now = Calendar.getInstance();
    Calendar newInstance = Calendar.getInstance();
    newInstance.setTimeInMillis(0);
    newInstance.set(Calendar.YEAR, now.get(Calendar.YEAR));
    newInstance.set(Calendar.MONTH, now.get(Calendar.MONTH));
    newInstance.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
    return newInstance;
  }

  public static Calendar getDateInstance(Calendar date) {
    Calendar newInstance = Calendar.getInstance();
    newInstance.setTimeInMillis(0);
    newInstance.set(Calendar.YEAR, date.get(Calendar.YEAR));
    newInstance.set(Calendar.MONTH, date.get(Calendar.MONTH));
    newInstance.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
    return newInstance;
  }

  public static Calendar parseISODate(String value) {
    //iso date
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(0);
    String ss[] = (value.contains("T") || value.contains("t"))
            ? value.trim().toUpperCase().split("T")
            : value.trim().toUpperCase().split(" ");
    String date = ss[0];
    String parts[] = date.trim().split("-");
    if (parts.length != 3) {
      throw new IllegalArgumentException("ISO date wrongly formatted");
    }
    cal.set(Calendar.DATE, Integer.parseInt(parts[2]));
    cal.set(Calendar.MONTH, Integer.parseInt(parts[1]) - 1);
    cal.set(Calendar.YEAR, Integer.parseInt(parts[0]));
    if (ss.length > 1) {
      //do time
      String time = ss[1];
      String pps[] = time.trim().split(":");
      if (pps.length != 3) {
        throw new IllegalArgumentException("ISO time wrongly formatted");
      }
      cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(pps[0]));
      cal.set(Calendar.MINUTE, Integer.parseInt(pps[1]));
      if (!pps[2].contains("+")) {
        cal.set(Calendar.SECOND, Integer.parseInt(pps[2]));
      } else {
        cal.set(Calendar.SECOND, Integer.parseInt(pps[2].substring(0, pps[2].indexOf('+'))));
        cal.set(Calendar.MILLISECOND, Integer.parseInt(pps[2].substring(pps[2].indexOf('+') + 1)));
      }
    }
    return cal;
  }
}
