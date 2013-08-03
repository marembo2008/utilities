/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public interface BiosInformation {

    public Calendar getSystemBiosDate();

    public String getSystemBiosVersion();

    public String getVideoBiosVersion();
}
