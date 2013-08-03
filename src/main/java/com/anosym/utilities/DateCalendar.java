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
public class DateCalendar extends FormattedCalendar{

    public DateCalendar(Calendar cal) {
        super(cal);
    }

    public DateCalendar() {
        super();
    }

    public DateCalendar(TimeZone zone) {
        super(zone);
    }

    public DateCalendar(Locale aLocale) {
        super(aLocale);
    }

    public DateCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    public DateCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth, 0, 0, 0);
    }

    public String toString(){
        return this.getDateString();
    }
}
