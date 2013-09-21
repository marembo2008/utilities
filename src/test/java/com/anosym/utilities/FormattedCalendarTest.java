/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class FormattedCalendarTest {

  public FormattedCalendarTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void toDateStringMDYY() {
    System.out.println("toDateString..........");
    String expected = "6/6/12";
    Calendar c = Calendar.getInstance();
    c.set(2012, 5, 6);
    String actual = FormattedCalendar.toDateString(c, "m/d/yy");
    System.out.println("Actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test
  public void toDateStringDDMMYYYY() {
    System.out.println("toDateString..........");
    String expected = "04-07-2034";
    Calendar c = Calendar.getInstance();
    c.set(2034, 6, 4);
    String actual = FormattedCalendar.toDateString(c, "dd-mm-yyyy");
    System.out.println("Actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test
  public void parseDateMDYY() {
    System.out.println("parseDateMDYY...................");
    Calendar expected = FormattedCalendar.getDateInstance();
    expected.set(2056, 7, 12);
    String date = "8, 12, 56";
    Calendar actual = FormattedCalendar.parseDate(date, "m, d, yy");
    System.out.println(FormattedCalendar.toISOString(actual));
    System.out.println(FormattedCalendar.toISOString(expected));
    assertEquals(expected, actual);
  }
  @Test
  public void parseDateDDMMYYYY() {
    System.out.println("parseDateMDYY...................");
    Calendar expected = FormattedCalendar.getDateInstance();
    expected.set(2023, 8, 12);
    String date = "12-09-2023";
    Calendar actual = FormattedCalendar.parseDate(date, "dd-mm-yyyy");
    System.out.println(FormattedCalendar.toISOString(actual));
    System.out.println(FormattedCalendar.toISOString(expected));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetInstanceIso(){
    Calendar expected = Calendar.getInstance();
    expected.setTimeInMillis(0);
    expected.set(2000, 0, 01, 00, 00, 00);
    String clStr = "2000-01-01 00:00:00";
    Calendar actual = FormattedCalendar.parseISODate(clStr);
    assertEquals(expected, actual);
  }
}