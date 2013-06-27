/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

/**
 *
 * @author marembo
 */
public class CountryIpMapping {

  private CountryCode countryCode;
  private Ipv4Range ipv4Range;

  public CountryIpMapping(CountryCode countryCode, Ipv4Range ipv4Range) {
    this.countryCode = countryCode;
    this.ipv4Range = ipv4Range;
  }

  public CountryIpMapping() {
  }

  public CountryIpMapping(CountryCode countryCode, String origin, String end) {
    this(countryCode, new Ipv4Range(origin, end));
  }

  public CountryCode getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(CountryCode countryCode) {
    this.countryCode = countryCode;
  }

  public Ipv4Range getIpv4Range() {
    return ipv4Range;
  }

  public void setIpv4Range(Ipv4Range ipv4Range) {
    this.ipv4Range = ipv4Range;
  }
}
