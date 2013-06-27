/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

/**
 *
 * @author Administrator
 */
public interface Registry {

    public static enum WINDOWS_OS {

        WINDOWS_XP("Windows XP"),
        WINDOWS_VISTA("Windows Vista"),
        WINDOWS_7("Windows 7"),
        WINDOWS_SERVER_2003("Windows Server 2003"),
        WINDOWS_SERVER_2008("Windows Server 2008");

        private WINDOWS_OS(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }

    public static enum LINUX_OS{

    }

    public BiosInformation getBiosInformation();

    public ProcessorInformation getProcessorInformation();

    public String[] macAddresses();

    /**
     * Returns a unique string identifying each mounted device 
     * @return
     */
    public String[] getFixedDrives();

    public String getComputerName();
}
