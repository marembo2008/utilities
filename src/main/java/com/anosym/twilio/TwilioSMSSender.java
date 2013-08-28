/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.twilio;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenn
 */
public class TwilioSMSSender {

  /**
   * This is just to provide an easire access to the loading of the configs.
   */
  public static final String TWILIO_CONFIG_PROPERTY = TwilioConfigUtil.TWILIO_CONFIG_PROPERTY;
  private TwilioConfig config;
  private static TwilioSMSSender sender;

  private TwilioSMSSender(TwilioConfig config) {
    this.config = config;
  }

  private TwilioSMSSender() {
    this.config = TwilioConfigUtil.getTwilioConfig();//load default;
    if (this.config == null) {
      throw new RuntimeException("twilio config has not been specified: "
              + "Twilio config path=" + System.getProperty(TWILIO_CONFIG_PROPERTY, System.getProperty("user.home")));
    }
  }

  public static TwilioSMSSender getInstance() throws IllegalStateException {
    if (sender == null) {
      sender = new TwilioSMSSender();
    }
    return sender;
  }

  public static TwilioSMSSender getInstance(TwilioConfig config) {
    if (sender == null) {
      sender = new TwilioSMSSender(config);
    }
    return sender;
  }

  public boolean sendSMS(String number, String msg) {
    //if the message is more than 160 character, truncate it!!
    msg = msg.length() > 160 ? msg.substring(0, 160) : msg;
    TwilioRestClient client = new TwilioRestClient(config.getAccountSid(), config.getAuthToken());
    Map<String, String> params = new HashMap<String, String>();
    params.put("Body", msg);
    params.put("To", number);
    params.put("From", config.getFromPhoneNumber());
    SmsFactory messageFactory = client.getAccount().getSmsFactory();
    Sms message = null;
    try {
      message = messageFactory.create(params);
      Logger.getLogger(TwilioSMSSender.class.getName()).log(Level.INFO, "Sent SMS: {0}", number + ", " + msg + ", " + message);
    } catch (TwilioRestException e) {
      Logger.getLogger(TwilioSMSSender.class.getName()).log(Level.SEVERE, "Send SMS FAIL: " + number + ", " + msg + ", " + message, e);
      return false;
    }
    return true;
  }
}