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

  private String accountSid;
  private String authToken;
  private String fromPhoneNumber;

  public TwilioConfig(String accountSid, String authToken, String fromPhoneNumber) {
    this.accountSid = accountSid;
    this.authToken = authToken;
    this.fromPhoneNumber = fromPhoneNumber;
  }

  public TwilioConfig() {
  }

  public String getAccountSid() {
    return accountSid;
  }

  public void setAccountSid(String accountSid) {
    this.accountSid = accountSid;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getFromPhoneNumber() {
    return fromPhoneNumber;
  }

  public void setFromPhoneNumber(String fromPhoneNumber) {
    this.fromPhoneNumber = fromPhoneNumber;
  }
}
