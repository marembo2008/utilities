/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Currently we only handle dates based on the GregorianCalendar
 * @author Ken
 */
public class VDuration {

    private int numYears;
    private int numLeapYears;
    private int num31Days;
    private int num30Days;
    private int num28Days;
    private int num29Days;
    private int numDays;

    private void numOfDays(Calendar start, Calendar end) {
        Calendar beg = new GregorianCalendar(start.get(Calendar.YEAR), 0, 1);
        while (beg.get(Calendar.MONTH) < start.get(Calendar.MONTH)) {
            int max = beg.getActualMaximum(Calendar.DAY_OF_MONTH);
            switch (max) {
                case 31:
                    num31Days--;
                    break;
                case 30:
                    num30Days--;
                    break;
                case 29:
                    num29Days--;
                    break;
                case 28:
                    num28Days--;
                    break;
            }
            beg.set(Calendar.MONTH, beg.get(Calendar.MONTH) + 1);
        }
        //remove days
        numDays -= (start.get(Calendar.DAY_OF_MONTH)); //count the first day
        while (start.get(Calendar.YEAR) < end.get(Calendar.YEAR)) {
            if (isLeapYear(start)) {
                numLeapYears++;
            } else {
                numYears++;
            }
            start.set(Calendar.YEAR, start.get(Calendar.YEAR) + 1);
        }
        //we have calculated years, calculate months
        start.set(Calendar.MONTH, 0); //from january to end month
        while (start.get(Calendar.MONTH) < end.get(Calendar.MONTH)) {
            int max = start.getActualMaximum(Calendar.DAY_OF_MONTH);
            switch (max) {
                case 31:
                    num31Days++;
                    break;
                case 30:
                    num30Days++;
                    break;
                case 29:
                    num29Days++;
                    break;
                case 28:
                    num28Days++;
                    break;
            }
            start.set(Calendar.MONTH, start.get(Calendar.MONTH) + 1);
        }
        //calculate days, days so far elapsed in the current month
        numDays += end.get(Calendar.DAY_OF_MONTH);
    }

    private boolean isLeapYear(Calendar cal) {
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) == 366;
    }

    private int getDuration(Calendar start, Calendar end) {
        Calendar tmp = start;
        start = (start.before(end)) ? start : end;
        end = (tmp.before(end)) ? end : tmp;
        numOfDays(start, end);
        int total = 0;
        total += (numYears * 365);
        total += (numLeapYears * 366);
        total += (num31Days * 31);
        total += (num30Days * 30);
        total += (num29Days * 29);
        total += (num28Days * 28);
        total += numDays;
        return total;
    }

    private int getDuration(Calendar start, Calendar end, boolean ignoreOrder) {
        if (!ignoreOrder && start.after(end)) {
            throw new IllegalArgumentException("Date Range is Invalid");
        }
        return this.getDuration(start, end);
    }

    public static int getDateDuration(Calendar start, Calendar end) {
        return (new VDuration()).getDuration(start, end);
    }

    public static int getDateDuration(Calendar start, Calendar end, boolean ignoreOrder) {
        VDuration d = new VDuration();
        return d.getDuration(start, end, ignoreOrder);
    }
}
