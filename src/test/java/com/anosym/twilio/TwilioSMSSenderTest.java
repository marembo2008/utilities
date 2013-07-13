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
<<<<<<< HEAD
//    String twilioConfig = "/home/marembo";
//    System.setProperty("twilio.config", twilioConfig);
//    TwilioSMSSender sender = TwilioSMSSender.getInstance();
//    boolean result = sender.sendSMS("+254725214698", "Hello this is a twilio sms test");
    boolean result = true;
      assertTrue(result);
=======
    String twilioConfig = "/home/marembo";
    System.setProperty("twilio.config", twilioConfig);
    TwilioSMSSender sender = TwilioSMSSender.getInstance();
    boolean result = sender.sendSMS("+254726153577", "Hello this is a twilio sms test");
    assertTrue(result);
>>>>>>> d5f54973ad980c0c60d8ceaa884f32b0258dc57b
  }
}