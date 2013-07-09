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
    String ACCOUNT_SID = TwilioConfig.ACCOUNT_SID;
    String AUTH_TOKEN = TwilioConfig.AUTH_TOKEN;
    String FROM = TwilioConfig.FROM_PHONENUMBER;
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