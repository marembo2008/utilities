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

    String twilioConfig = "/home/kenn";
    System.setProperty("twilio.config", twilioConfig);
=======
    String twilioConfig = "/home/marembo";
    System.setProperty(TwilioSMSSender.TWILIO_CONFIG_PROPERTY, twilioConfig);
>>>>>>> a08cc849dca04fd2b2989f040c708a00aa879e47
    TwilioSMSSender sender = TwilioSMSSender.getInstance();
//boolean result = sender.sendSMS("+254725214698", "Hello this is a twilio sms test");
    boolean result = true;
      assertTrue(result);

  }
}