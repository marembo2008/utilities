/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

/**
 *
 * @author Administrator
 */
public class LinuxRegistry implements Registry {

    public BiosInformation getBiosInformation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProcessorInformation getProcessorInformation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] macAddresses() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getFixedDrives() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getComputerName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
