/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.clickatell;

import com.anosym.vjax.annotations.Namespace;
import com.anosym.utilities.IdGenerator;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
@Namespace(prefix = "ckt", uri = "http://flemax.com/clickatell")
public class ClickatellConfiguration implements Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(ClickatellConfiguration.class);
  private String apiId = "3388863";
  private String username = "mimiprotect";
  private String password = "zU2PVYlQ";
  private String xmlApiUrl = "http://api.clickatell.com/xml/xml";
  private String fromNumber = "0724290713";

  public String getFromNumber() {
    return fromNumber;
  }

  public void setFromNumber(String fromNumber) {
    this.fromNumber = fromNumber;
  }

  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getXmlApiUrl() {
    return xmlApiUrl;
  }

  public void setXmlApiUrl(String xmlApiUrl) {
    this.xmlApiUrl = xmlApiUrl;
  }
}
