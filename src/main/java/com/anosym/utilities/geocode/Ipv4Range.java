/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

/**
 *
 * @author marembo
 */
public class Ipv4Range {

  private Ipv4 origin;
  private Ipv4 end;

  public Ipv4Range() {
  }

  public Ipv4Range(Ipv4 origin, Ipv4 end) {
    this.origin = origin;
    this.end = end;
  }

  public Ipv4Range(String origin, String end) {
    this(new Ipv4(origin), new Ipv4(end));
  }

  public Ipv4 getOrigin() {
    return origin;
  }

  public void setOrigin(Ipv4 origin) {
    this.origin = origin;
  }

  public Ipv4 getEnd() {
    return end;
  }

  public void setEnd(Ipv4 end) {
    this.end = end;
  }

  public boolean isInRange(Ipv4 ip) {
    return ip.isWithin(origin, end);
  }

  @Override
  public String toString() {
    return origin + "-" + end;
  }
}
