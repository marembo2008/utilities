/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.tranglo;

import com.anonysm.tranglo.service.EPinReload;
import com.anonysm.tranglo.service.EPinReloadSoap;

/**
 *
 * @author kenn
 */
public class TrangloClient {

    public static final String TRANGLO_CONFIG_PROPERTY = TrangloConfigUtil.TRANGLO_CONFIG_PROPERTY;
    TrangloConfiguration trangloConfig;
    private static TrangloClient sender;

    EPinReload serviceDescriptor = null;
    EPinReloadSoap service = null;
    String username = null;
    String password = null;
    String transID = null;
    String prodCode = "";
    
    public boolean topupAirtime(String phoneNumber, int amount){
        return sendAirtime(phoneNumber, phoneNumber, amount);
    }
    
    public boolean sendAirtime(String from, String to, int amount){
        
        String responseCode = service.requestReload(from, to, prodCode, amount, username, password, transID);
        System.out.println("response code "+responseCode);
        try {
           int response = Integer.parseInt(responseCode);
            if(response == 0 || response == 1){
                return true;
            }
        } catch (Exception e) {
            
        }
        return false;
    }
    
    private void init(){
        serviceDescriptor = new EPinReload();
        service = serviceDescriptor.getEPinReloadSoap();
        service.ping();
        username = trangloConfig.getUsername();
        password = trangloConfig.getPassword();
        //TODO confirm with Andre how this should work
        transID = "12345";
    }
    
    
    public TrangloClient(TrangloConfiguration trangloConfig) {
        this.trangloConfig = trangloConfig;
        init();
    }

    private TrangloClient() {
        this.trangloConfig = TrangloConfigUtil.getTwilioConfig();
        if (trangloConfig == null) {
            String err = "tranglo config has not been specified: "
                    + "Tranglo config path=" + System.getProperty(TRANGLO_CONFIG_PROPERTY, System.getProperty("user.home"));
            throw new RuntimeException(err);
        }
        init();
    }

    public static TrangloClient getInstance() throws IllegalStateException {
        if (sender == null) {
            sender = new TrangloClient();
        }
        return sender;
    }

    public static TrangloClient getInstance(TrangloConfiguration config) throws IllegalStateException {
        if (sender == null) {
            sender = new TrangloClient(config);
        }
        return sender;
    }


    
    public static void main(String[] args) {
        TrangloClient tc = TrangloClient.getInstance();
        String tzNumber = "255710437287";
        String saNumber = "27763574581";
        //TODO marembo test these numbers, sa does not work, tz works
        String from = saNumber;
        String to = from;
        int amount = 1500;
       boolean res =  tc.sendAirtime(from, to, amount);
       if(res){
           System.out.println("SENT AIRTIME TO "+to);
       }else{
           System.out.println("FAILED to send airtime to "+ to);
       }
           
        
    }
}
