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
  private String apiId = "api_id_here";
  private String username = "username_here";
  private String password = "password_here";
  private String xmlApiUrl = "http://api.clickatell.com/xml/xml";
  private String fromNumber = "from_phone_number_here";

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

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + (this.apiId != null ? this.apiId.hashCode() : 0);
    hash = 97 * hash + (this.username != null ? this.username.hashCode() : 0);
    hash = 97 * hash + (this.password != null ? this.password.hashCode() : 0);
    hash = 97 * hash + (this.xmlApiUrl != null ? this.xmlApiUrl.hashCode() : 0);
    hash = 97 * hash + (this.fromNumber != null ? this.fromNumber.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ClickatellConfiguration other = (ClickatellConfiguration) obj;
    if ((this.apiId == null) ? (other.apiId != null) : !this.apiId.equals(other.apiId)) {
      return false;
    }
    if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
      return false;
    }
    if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
      return false;
    }
    if ((this.xmlApiUrl == null) ? (other.xmlApiUrl != null) : !this.xmlApiUrl.equals(other.xmlApiUrl)) {
      return false;
    }
    if ((this.fromNumber == null) ? (other.fromNumber != null) : !this.fromNumber.equals(other.fromNumber)) {
      return false;
    }
    return true;
  }
}
