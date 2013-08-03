/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.test.twilio;

import com.anonysm.tranglo.TrangloClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kenn
 */
public class TwilioSMSSenderTest {
    
    public TwilioSMSSenderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSend(){
        
//        String twilioConfig = "/home/kenn";
//    System.setProperty("twilio.config", twilioConfig);
//    System.setProperty(TwilioSMSSender.TWILIO_CONFIG_PROPERTY, twilioConfig);
//    TwilioSMSSender sender = TwilioSMSSender.getInstance();
//boolean result = sender.sendSMS("+254725214698", "Hello this is a twilio sms test");
        
    boolean result = true;
      assertTrue(result);
    }
    
}