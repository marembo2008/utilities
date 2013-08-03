/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Luffy
 */
public class TimeCalendar extends FormattedCalendar {

  public TimeCalendar(Calendar cal) {
    super(cal);
  }

  public TimeCalendar() {
    this(Calendar.getInstance());
  }

  public TimeCalendar(TimeZone zone) {
    super(zone);
  }

  public TimeCalendar(Locale aLocale) {
    super(aLocale);
  }

  public TimeCalendar(TimeZone zone, Locale aLocale) {
    super(zone, aLocale);
  }

  public TimeCalendar(int hours, int mins, int seconds) {
    this();
    set(hours, mins, seconds, 0);
  }

  public TimeCalendar(int hours, int mins, float seconds) {
    this();
    set(hours, mins, seconds);
  }

  public TimeCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    super(year, month, dayOfMonth, hourOfDay, minute);
  }

  public TimeCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
    super(year, month, dayOfMonth, hourOfDay, minute, second);
  }

  public void set(int hours, int mins, int seconds, int millis) {
    set(Calendar.HOUR, hours);
    set(Calendar.MINUTE, mins);
    set(Calendar.SECOND, seconds);
    set(Calendar.MILLISECOND, millis);
  }

  public void set(int hours, int mins, float seconds) {
    set(Calendar.HOUR, hours);
    set(Calendar.MINUTE, mins);
    set(Calendar.SECOND, (int) seconds);
    set(Calendar.MILLISECOND, (int) (seconds * 1000));
  }

  @Override
  public String toString() {
    return this.getTimeString();
  }

  /**
   *
   * @param obj
   * @return
   */
  @Override
  @SuppressWarnings("unnecessary")
  public boolean equals(Object obj) {
    return toString().equals(obj.toString());
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
