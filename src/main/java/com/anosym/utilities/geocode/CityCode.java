/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class CityCode implements Serializable {

  private CountryCode countryCode;
  private String name;
  /**
   * This is not standard, and can be anything. It can be for example regional dialing code.
   */
  private String code;
  /**
   * United Nations Code for Trade and Transport Locations (UN/LOCODE)
   */
  private String unlocode;

  public CityCode() {
  }

  public CityCode(CountryCode countryCode, String name, String code) {
    this.countryCode = countryCode;
    this.name = name;
    this.code = code;
  }

  public void setUnlocode(String unlocode) {
    this.unlocode = unlocode;
  }

  public String getUnlocode() {
    return unlocode;
  }

  public CountryCode getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(CountryCode countryCode) {
    this.countryCode = countryCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 83 * hash + (this.countryCode != null ? this.countryCode.hashCode() : 0);
    hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
    hash = 83 * hash + (this.code != null ? this.code.hashCode() : 0);
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
    final CityCode other = (CityCode) obj;
    if (this.countryCode != other.countryCode && (this.countryCode == null || !this.countryCode.equals(other.countryCode))) {
      return false;
    }
    if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
      return false;
    }
    if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
      return false;
    }
    return true;
  }
}
