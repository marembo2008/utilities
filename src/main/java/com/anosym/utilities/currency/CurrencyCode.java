/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.utilities.geocode.CountryCode;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class CurrencyCode implements Serializable {

  private static final long serialVersionUID = 128282922920L;
  private CountryCode countryCode;
  private String currencyName;
  private String currencySymbol;
  private String currencyIsoCode;

  public CurrencyCode(CountryCode countryCode, String currencyName, String currencySymbol, String currencyIsoCode) {
    this.countryCode = countryCode;
    this.currencyName = currencyName;
    this.currencySymbol = currencySymbol;
    this.currencyIsoCode = currencyIsoCode;
  }

  public CurrencyCode() {
  }

  public CountryCode getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(CountryCode countryCode) {
    this.countryCode = countryCode;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public String getCurrencyIsoCode() {
    return currencyIsoCode;
  }

  public void setCurrencyIsoCode(String currencyIsoCode) {
    this.currencyIsoCode = currencyIsoCode;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (this.countryCode != null ? this.countryCode.hashCode() : 0);
    hash = 97 * hash + (this.currencyName != null ? this.currencyName.hashCode() : 0);
    hash = 97 * hash + (this.currencySymbol != null ? this.currencySymbol.hashCode() : 0);
    hash = 97 * hash + (this.currencyIsoCode != null ? this.currencyIsoCode.hashCode() : 0);
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
    final CurrencyCode other = (CurrencyCode) obj;
    if (this.countryCode != other.countryCode && (this.countryCode == null || !this.countryCode.equals(other.countryCode))) {
      return false;
    }
    if ((this.currencyName == null) ? (other.currencyName != null) : !this.currencyName.equals(other.currencyName)) {
      return false;
    }
    if ((this.currencySymbol == null) ? (other.currencySymbol != null) : !this.currencySymbol.equals(other.currencySymbol)) {
      return false;
    }
    if ((this.currencyIsoCode == null) ? (other.currencyIsoCode != null) : !this.currencyIsoCode.equals(other.currencyIsoCode)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return currencySymbol;
  }

  public String toFullString() {
    return "CurrencyCode{" + "countryCode=" + countryCode + ", currencyName=" + currencyName + ", currencySymbol=" + currencySymbol + ", currencyIsoCode=" + currencyIsoCode + '}';
  }

  public String getDescription() {
    return currencySymbol + "(" + countryCode.getName() + ")";
  }
}
