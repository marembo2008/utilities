/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Administrator
 */
public class CalendarBindingConverter extends Converter<Calendar, String> {

    @Override
    public String convertForward(Calendar value) {
        return FormattedCalendar.toISOString(value);
    }

    @Override
    public Calendar convertReverse(String value) {
        return FormattedCalendar.createInstance(value);
    }
}
