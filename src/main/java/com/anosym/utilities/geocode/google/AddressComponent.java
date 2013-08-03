/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.google;

import java.util.Arrays;

/**
 *
 * @author marembo
 */
public class AddressComponent {

  private String long_name;
  private String short_name;
  private GoogleGeocodeResultType[] type;

  public AddressComponent() {
  }

  public AddressComponent(String long_name, String short_name, GoogleGeocodeResultType[] type) {
    this.long_name = long_name;
    this.short_name = short_name;
    this.type = type;
  }

  public String getLong_name() {
    return long_name;
  }

  public void setLong_name(String long_name) {
    this.long_name = long_name;
  }

  public String getShort_name() {
    return short_name;
  }

  public void setShort_name(String short_name) {
    this.short_name = short_name;
  }

  public GoogleGeocodeResultType[] getType() {
    return type;
  }

  public void setType(GoogleGeocodeResultType[] type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "AddressComponent{" + "long_name=" + long_name + ", short_name=" + short_name + ", type=" + Arrays.toString(type) + '}';
  }
}
