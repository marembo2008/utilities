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
public class GoogleGeocodeResult {

  private GoogleGeocodeResultType[] type;
  private String formatted_address;
  private AddressComponent[] address_component;
  private GoogleGeometry geometry;

  public GoogleGeocodeResult(GoogleGeocodeResultType[] type, String formatted_address, AddressComponent[] address_component, GoogleGeometry geometry) {
    this.type = type;
    this.formatted_address = formatted_address;
    this.address_component = address_component;
    this.geometry = geometry;
  }

  public GoogleGeocodeResult() {
  }

  public GoogleGeocodeResultType[] getType() {
    return type;
  }

  public void setType(GoogleGeocodeResultType[] type) {
    this.type = type;
  }

  public String getFormatted_address() {
    return formatted_address;
  }

  public void setFormatted_address(String formatted_address) {
    this.formatted_address = formatted_address;
  }

  public AddressComponent[] getAddress_component() {
    return address_component;
  }

  public void setAddress_component(AddressComponent[] address_component) {
    this.address_component = address_component;
  }

  public GoogleGeometry getGeometry() {
    return geometry;
  }

  public void setGeometry(GoogleGeometry geometry) {
    this.geometry = geometry;
  }

  @Override
  public String toString() {
    return "GoogleGeocodeResult{\n" + "type=" + Arrays.toString(type) + ", \nformatted_address=" + formatted_address + ", "
            + "\naddress_component=" + Arrays.toString(address_component) + ", \ngeometry=" + geometry + '}';
  }
}
