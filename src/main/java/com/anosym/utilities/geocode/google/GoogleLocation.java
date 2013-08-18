/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.google;

import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class GoogleLocation implements Serializable {

  private static final long serialVersionUID = 4824482249201L;
  private double lat;
  private double lng;

  public GoogleLocation(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  public GoogleLocation() {
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  @Override
  public String toString() {
    return lat + "," + lng;
  }
}
