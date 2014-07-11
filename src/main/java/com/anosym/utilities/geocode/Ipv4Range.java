/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

/**
 *
 * @author marembo
 */
public class Ipv4Range implements Comparable<Ipv4Range> {

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

  /**
   * Returns true if the specified ip is before the start of the current range.
   *
   * @param ip
   * @return
   */
  public boolean before(Ipv4 ip) {
    return ip.compareTo(origin) < 0;
  }

  /**
   * Returns true if the specified ip is after the end of the current range.
   *
   * @param ip
   * @return
   */
  public boolean after(Ipv4 ip) {
    return ip.compareTo(end) > 0;
  }

  @Override
  public String toString() {
    return origin + "-" + end;
  }

  @Override
  public int compareTo(Ipv4Range o) {
    //certainly two ip ranges should not have the same origin
    int m;
    return (m = origin.compareTo(o.origin)) == 0 ? end.compareTo(o.end) : m;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + (this.origin != null ? this.origin.hashCode() : 0);
    hash = 53 * hash + (this.end != null ? this.end.hashCode() : 0);
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
    final Ipv4Range other = (Ipv4Range) obj;
    if (this.origin != other.origin && (this.origin == null || !this.origin.equals(other.origin))) {
      return false;
    }
    return this.end == other.end || (this.end != null && this.end.equals(other.end));
  }

}
