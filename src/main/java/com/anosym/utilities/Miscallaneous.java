/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;

/**
 *
 * @author Marembo
 */
public class Miscallaneous {

    public static int getNumberOfDays(Calendar start, Calendar end) {
        int days = -1;
        int startYear = start.get(Calendar.YEAR);
        int endYear = end.get(Calendar.YEAR);
        int yearDiff = endYear - startYear;
        //same year
        if (yearDiff == 0) {
            days = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
            if(days  >=0){
            return days;
            }
        }
        int remainingDays = start.getMaximum(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
        //more than 1 year difference
        Calendar cal = Calendar.getInstance();
        int year = start.get(Calendar.YEAR);
        int totalDays = 0;
        for (; yearDiff >1; yearDiff--) {
            //set cal to begining of year
            year++;
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, cal.getMinimum(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_YEAR, cal.getMinimum(Calendar.DAY_OF_YEAR));
            totalDays += (cal.getMaximum(Calendar.DAY_OF_YEAR) - cal.getMinimum(Calendar.DAY_OF_YEAR));
        }
        if (yearDiff == 1) {
            days = remainingDays + end.get(Calendar.DAY_OF_YEAR);
            return days;
        }
        return days;
    }
}
