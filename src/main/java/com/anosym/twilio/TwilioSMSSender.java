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

/**
 *
 * @author kenn
 */
public class TwilioSMSSender {
    private static TwilioConfig CONFIG;
    String ACCOUNT_SID = CONFIG.ACCOUNT_SID;
    String AUTH_TOKEN = CONFIG.AUTH_TOKEN;
    String FROM = CONFIG.FROM_PHONENUMBER;
    private static TwilioSMSSender sender;
    
    private TwilioSMSSender(TwilioConfig config){
        this.CONFIG = config;
    }
    
    public TwilioSMSSender getInstance() throws IllegalStateException{
        if  (sender == null){
            throw new IllegalStateException("Configuration object is not initialized");
        }
        return sender;
    }
    
    public TwilioSMSSender getInstance(TwilioConfig config){
        if( sender == null){
            sender = new TwilioSMSSender(config);
        }
        return sender;
    }
    
    
    
    public boolean sendSMS(String number, String msg){
    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
// Build a filter for the SmsList
        Map<String, String> params = new HashMap<String, String>();
        
        params.put("Body", "testing");
        params.put("To", number);
        params.put("From", FROM);
        
        SmsFactory messageFactory = client.getAccount().getSmsFactory();
        Sms message = null;
        try {
            message = messageFactory.create(params);
        } catch (TwilioRestException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(message.getSid());
return true;
        
}
}