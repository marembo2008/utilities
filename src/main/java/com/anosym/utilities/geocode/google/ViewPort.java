/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.google;

/**
 *
 * @author marembo
 */
public class ViewPort {

  private GoogleLocation southwest;
  private GoogleLocation northeast;

  public ViewPort(GoogleLocation southwest, GoogleLocation northeast) {
    this.southwest = southwest;
    this.northeast = northeast;
  }

  public ViewPort() {
  }

  public GoogleLocation getSouthwest() {
    return southwest;
  }

  public void setSouthwest(GoogleLocation southwest) {
    this.southwest = southwest;
  }

  public GoogleLocation getNortheast() {
    return northeast;
  }

  public void setNortheast(GoogleLocation northeast) {
    this.northeast = northeast;
  }

  @Override
  public String toString() {
    return "ViewPort{" + "southwest=" + southwest + ", northeast=" + northeast + '}';
  }

}
