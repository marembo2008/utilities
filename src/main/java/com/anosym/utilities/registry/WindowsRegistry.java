/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

import com.anosym.hardware.DriveInformation;
import com.anosym.utilities.Application;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Administrator
 */
public final class WindowsRegistry implements Registry {

    public BiosInformation getBiosInformation() {
        return new BiosInformation() {

            public Calendar getSystemBiosDate() {
                String biosDate = readRegistry("HKEY_LOCAL_MACHINE\\HARDWARE\\DESCRIPTION\\System", "SystemBiosDate");
                if (biosDate != null) {
                    try {
                        DateFormat format = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                        DateFormatter formatter = new DateFormatter(format);
                        Date date = (Date) formatter.stringToValue(biosDate);
                        if (date != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            return cal;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }

            public String getSystemBiosVersion() {
                return readRegistry("HKEY_LOCAL_MACHINE\\HARDWARE\\DESCRIPTION\\System", "SystemBiosVersion");
            }

            public String getVideoBiosVersion() {
                return readRegistry("HKEY_LOCAL_MACHINE\\HARDWARE\\DESCRIPTION\\System", "VideoBiosVersion");
            }
        };
    }

    public ProcessorInformation getProcessorInformation() {
        return new ProcessorInformation() {

            private final transient List<Processor> processors = init();
            int i = 0;

            private List<Processor> init() {
                List<Processor> prcs = new ArrayList<Processor>();
                String[] procData = readRegistry("HKEY_LOCAL_MACHINE\\HARDWARE\\DESCRIPTION\\System\\CentralProcessor");
                for (String pr : procData) {
                    final String[] data = pr.split("\r\n");
                    //get a map decoder
                    final Map<String, String> key_value = new HashMap<String, String>();
                    for (String kv : data) {
                        String[] kvs = kv.split("\t");
                        if (kvs.length == 3) {
                            key_value.put(kvs[0].trim(), kvs[2].trim());
                        }
                    }
                    prcs.add(new Processor() {

                        public int getProcessorSpeed() {
                            try {
                                String s = key_value.get("~MHz");
                                if (s.contains("0x")) {
                                    s = s.substring(s.indexOf("0x") + 2);
                                }
                                return Integer.parseInt(s, 16);
                            } catch (Exception e) {
                                Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
                                return -1;
                            }
                        }

                        public String getComponentInformation() {
                            return key_value.get("Component Information");
                        }

                        public String getConfigurationData() {
                            return key_value.get("Configuration Data");
                        }

                        public long getFeatureSet() {
                            String s = key_value.get("FeatureSet");
                            if (s.contains("0x")) {
                                s = s.substring(s.indexOf("0x") + 2);
                            }
                            return Long.parseLong(s, 16);
                        }

                        public String getIdentifier() {
                            return key_value.get("Identifier");
                        }

                        public long getPlatformId() {
                            String s = key_value.get("Platform ID");
                            if (s.contains("0x")) {
                                s = s.substring(s.indexOf("0x") + 2);
                            }
                            if (!s.isEmpty()) {
                                return Long.parseLong(s, 16);
                            }
                            return -1;
                        }

                        public String getPreviousUpdateSignature() {
                            return key_value.get("Previous Update Signature");
                        }

                        public String getPreviousNameString() {
                            return key_value.get("ProcessorNameString");
                        }

                        public String getUpdateSignature() {
                            return key_value.get("Update Signature");
                        }

                        public int getUpdateStaus() {
                            String s = key_value.get("Update Status");
                            if (s.contains("0x")) {
                                s = s.substring(s.indexOf("0x") + 2);
                            }
                            return Integer.parseInt(s, 16);
                        }

                        public String getVendorIdentifier() {
                            return key_value.get("VendorIdentifier");
                        }

                        @Override
                        public String toString() {
                            return getProcessorSpeed() + "|" + getComponentInformation() + "|" + getConfigurationData() + "|" + getFeatureSet() + "|" + getIdentifier() + "|" + getPlatformId();
                        }
                    });
                }
                return prcs;
            }

            public int getNumberOfProcessors() {
                return processors.size();
            }

            public Processor[] getProcessors() {
                return processors.toArray(new Processor[0]);
            }
        };
    }

    public static String readRegistry(String location, String key) {
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query "
                    + '"' + location + "\" /v " + key);

            InputStream is = process.getInputStream();
            StringBuilder sw = new StringBuilder();

            try {
                int c;
                while ((c = is.read()) != -1) {
                    sw.append((char) c);
                }
            } catch (IOException e) {
                Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
            }

            String output = sw.toString();
            // \n<Version information>\n\n<key>    <registry type>    <value>\r\n\r\n
            output = output.trim();
            int li = output.lastIndexOf('\n');
            if (li > 0) {
                output = output.substring(li + 1).trim();
            }
            int indx = output.indexOf("\\0");
            if (indx > 0) {
                output = output.substring(0, indx);
            }
            String[] parts = output.split("\t");
            //should have three parts
            return parts[parts.length - 1];
        } catch (Exception e) {
            Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static String[] readRegistry(String location) {
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query "
                    + '"' + location + "\" /S ");

            InputStream is = process.getInputStream();
            StringBuilder sw = new StringBuilder();

            try {
                int c;
                while ((c = is.read()) != -1) {
                    sw.append((char) c);
                }
            } catch (IOException e) {
                Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
            }

            String output = sw.toString();
            output = output.substring(output.indexOf(location) + location.length()).trim();
            return output.split("\r\n\r\n");
        } catch (Exception e) {
            Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public String[] macAddresses() {
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("ipconfig /all");

            InputStream is = process.getInputStream();
            StringBuilder sw = new StringBuilder();

            int c;
            while ((c = is.read()) != -1) {
                sw.append((char) c);
            }

            String output = sw.toString();
            String[] outs = output.split("\r\n");
            List<String> addresses = new ArrayList<String>();
            for (String s : outs) {
                if (s.toLowerCase().contains("physical address")) {
                    String val = s.substring(s.indexOf(':') + 1);
                    addresses.add(val);
                }
            }
            return addresses.toArray(new String[0]);
        } catch (IOException e) {
            Logger.getLogger(WindowsRegistry.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public String[] getFixedDrives() {
        String[] data = readRegistry("HKEY_LOCAL_MACHINE\\SYSTEM\\MountedDevice1");
        DriveInformation di = Application.getDriveInformation();
        if (data.length == 1) {
            String[] values = data[0].trim().split("\r\n");
            int i = 0;
            for (String s : values) {
                String[] vals = s.trim().split("\t");
                if (vals.length == 3) {
                    String drive = vals[0].trim();
                    char c = drive.charAt(drive.length() - 2);
                    //add it only if its fixed
                    int driveType = di.driveType(c);
                    if (driveType != DriveInformation.DRIVE_REMOVABLE && driveType == DriveInformation.DRIVE_FIXED) {
                        values[i++] = vals[2].trim();
                    }
                }
            }
            return values;
        }
        return null;
    }

    public String[] getMountedDrives() {
        String[] data = readRegistry("HKEY_LOCAL_MACHINE\\SYSTEM\\MountedDevice1");
        if (data.length == 1) {
            String[] values = data[0].trim().split("\r\n");
            int i = 0;
            for (String s : values) {
                String[] vals = s.trim().split("\t");
                if (vals.length == 3) {
                    String drive = vals[0].trim();
                    char c = drive.charAt(drive.length() - 2);
                    values[i++] = vals[2].trim();
                }
            }
            return values;
        }
        return null;
    }

    public String getComputerName() {
        String data = readRegistry("HKEY_LOCAL_MACHINE\\SYSTEM\\ControlSet001\\Control\\ComputerName\\ActiveComputerName", "ComputerName");
        return data;
    }
}
