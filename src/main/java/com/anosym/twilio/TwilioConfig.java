/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.twilio;

/**
 *
 * @author kenn
 */
public class TwilioConfig {

    public String ACCOUNT_SID = "";
    public String AUTH_TOKEN = "";
    public String FROM_PHONENUMBER = "";

    public TwilioConfig() {
    }

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getFROM_PHONENUMBER() {
        return FROM_PHONENUMBER;
    }

    public void setFROM_PHONENUMBER(String FROM_PHONENUMBER) {
        this.FROM_PHONENUMBER = FROM_PHONENUMBER;
    }
}
