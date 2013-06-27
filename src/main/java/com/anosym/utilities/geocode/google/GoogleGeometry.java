/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.google;

/**
 *
 * @author marembo
 */
public class GoogleGeometry {

  private GoogleLocation location;
  private ViewPort viewport;
  private ViewPort bounds;
  private GoogleLocationType location_type;

  public GoogleGeometry(GoogleLocation location, ViewPort viewport, ViewPort bounds, GoogleLocationType location_type) {
    this.location = location;
    this.viewport = viewport;
    this.bounds = bounds;
    this.location_type = location_type;
  }

  public GoogleGeometry() {
  }

  public ViewPort getBounds() {
    return bounds;
  }

  public void setBounds(ViewPort bounds) {
    this.bounds = bounds;
  }

  public GoogleLocationType getLocation_type() {
    return location_type;
  }

  public void setLocation_type(GoogleLocationType location_type) {
    this.location_type = location_type;
  }

  public GoogleLocation getLocation() {
    return location;
  }

  public void setLocation(GoogleLocation location) {
    this.location = location;
  }

  public ViewPort getViewport() {
    return viewport;
  }

  public void setViewport(ViewPort viewport) {
    this.viewport = viewport;
  }

  @Override
  public String toString() {
    return "GoogleGeometry{\n" + "location=" + location + ", \nviewport=" + viewport + ", \nbounds=" + bounds + ", \nlocation_type=" + location_type + '}';
  }
}
