/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.hardware;

import com.sun.jna.Library;

/**
 *
 * @author Administrator
 */
public interface DriveInformation extends Library {

    public static enum DriveType {

        DRIVE_UNKNOWN,
        DRIVE_NO_ROOT_DIR,
        DRIVE_REMOVABLE,
        DRIVE_FIXED,
        DRIVE_REMOTE,
        DRIVE_CDROM,
        DRIVE_RAMDISK;

        public static DriveType getDriveType(int index) {
            for (DriveType dt : values()) {
                if (dt.ordinal() == index) {
                    return dt;
                }
            }
            return null;
        }
    }
    public static final int DRIVE_UNKNOWN = 0;
    public static final int DRIVE_NO_ROOT_DIR = 1;
    public static final int DRIVE_REMOVABLE = 2;
    public static final int DRIVE_FIXED = 3;
    public static final int DRIVE_REMOTE = 4;
    public static final int DRIVE_CDROM = 5;
    public static final int DRIVE_RAMDISK = 6;

    /**
     * Returns the type of a drive given its logical mapping
     * @param driveLetter
     * @return
     * @see #DRIVE_CDROM
     * @see #DRIVE_FIXED
     * @see #DRIVE_NO_ROOT_DIR
     * @see #DRIVE_RAMDISK
     * @see #DRIVE_REMOTE
     * @see #DRIVE_REMOVABLE
     * @see #DRIVE_UNKNOWN
     */
    public int driveType(char driveLetter);

    /**
     * This function returns the volume serial number that the operating system assigns when a hard disk is formatted
     * This drive number is subjected to change when the drive is formatted
     * @param drive
     * @return
     */
    public long driveSerialNumber(char drive);

    /**
     * This function returns the volume serial number as uniquely assigned by the manufacturer
     * @param drive
     * @return 
     */
    public long manufacturerDriveSerialNumber(char drive);
}
