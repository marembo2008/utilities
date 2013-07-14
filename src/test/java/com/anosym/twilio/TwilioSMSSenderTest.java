/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.twilio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class TwilioSMSSenderTest {

  public TwilioSMSSenderTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testSendSMS() {
    String twilioConfig = "/home/marembo";
    System.setProperty(TwilioSMSSender.TWILIO_CONFIG_PROPERTY, twilioConfig);
    TwilioSMSSender sender = TwilioSMSSender.getInstance();
    boolean result = sender.sendSMS("+254726153577", "Hello this is a twilio sms test");
    assertTrue(result);
  }
}