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
public class GoogleGeocodeResponse {

  private GoogleResponseStatus status;
  private GoogleGeocodeResult[] result;

  public GoogleGeocodeResponse() {
  }

  public GoogleGeocodeResponse(GoogleResponseStatus status, GoogleGeocodeResult[] result) {
    this.status = status;
    this.result = result;
  }

  public GoogleResponseStatus getStatus() {
    return status;
  }

  public void setStatus(GoogleResponseStatus status) {
    this.status = status;
  }

  public GoogleGeocodeResult[] getResult() {
    return result;
  }

  public void setResult(GoogleGeocodeResult[] result) {
    this.result = result;
  }

  public AddressComponent getAddressComponent(GoogleGeocodeResultType geocodeResultType) {
    for (GoogleGeocodeResult ggr : result) {
      for (AddressComponent ac : ggr.getAddress_component()) {
        if (ac.getType() != null && ac.getType().length > 0) {
          for (GoogleGeocodeResultType t : ac.getType()) {
            if (t == geocodeResultType) {
              return ac;
            }
          }
        }
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "GoogleGeocodeResponse{" + "status=" + status + ", \nresult=" + Arrays.toString(result) + '}';
  }
}
