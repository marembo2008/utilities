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
  private TrangloConfiguration trangloConfig;
  private static TrangloClient sender;
  private EPinReload serviceDescriptor = null;
  private EPinReloadSoap service = null;
  private String username = null;
  private String password = null;
  private String transID = null;
  private String prodCode = "";

  public TrangloClient(TrangloConfiguration trangloConfig) {
    this.trangloConfig = trangloConfig;
    init();
  }

  private TrangloClient() {
    this.trangloConfig = TrangloConfigUtil.getTrangloConfigurations();
    if (trangloConfig == null) {
      String err = "tranglo config has not been specified: "
              + "Tranglo config path=" + System.getProperty(TRANGLO_CONFIG_PROPERTY, System.getProperty("user.home"));
      throw new RuntimeException(err);
    }
    init();
  }

  private void init() {
    serviceDescriptor = new EPinReload();
    service = serviceDescriptor.getEPinReloadSoap();
    service.ping();
    username = trangloConfig.getUsername();
    password = trangloConfig.getPassword();
    //TODO confirm with Andre how this should work
    transID = "12345";
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

  public boolean topupAirtime(String phoneNumber, int amount) {
    return sendAirtime(phoneNumber, phoneNumber, amount);
  }

  public boolean sendAirtime(String from, String to, int amount) {
    String responseCode = service.requestReload(from, to, prodCode, amount, username, password, transID);
    System.out.println("response code " + responseCode);
    try {
      int response = Integer.parseInt(responseCode);
      if (response == 0 || response == 1) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

  public static void main(String[] args) {
    TrangloClient tc = TrangloClient.getInstance();
    String tzNumber = "255710437287";
    String saNumber = "263773437287";
    String ke = "254721905360";
    //TODO marembo test these numbers, sa does not work, tz works
    String from = ke;
    String to = from;
    int amount = 5;
    boolean res = tc.sendAirtime(from, to, amount);
    if (res) {
      System.out.println("SENT AIRTIME TO " + to);
    } else {
      System.out.println("FAILED to send airtime to " + to);
    }
  }
}
