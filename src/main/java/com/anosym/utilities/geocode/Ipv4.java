/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import com.anosym.utilities.Utility;

/**
 *
 * @author marembo
 */
public class Ipv4 implements Comparable<Ipv4> {

  private int firstOctet;
  private int secondOctet;
  private int thirdOctet;
  private int fourthOctet;

  public Ipv4() {
  }

  public Ipv4(long ip) {
    this.firstOctet = (int) ((ip / 16777216) % 256);
    this.secondOctet = (int) ((ip / 65536) % 256);
    this.thirdOctet = (int) ((ip / 256) % 256);
    this.fourthOctet = (int) ((ip) % 256);
  }

  public Ipv4(String ipAddress) {
    this(toInts(ipAddress));
  }

  public Ipv4(int[] parts) {
    if (parts == null || parts.length != 4) {
      throw new IllegalArgumentException("Invalid ip parts");
    }
    firstOctet = parts[0];
    secondOctet = parts[1];
    thirdOctet = parts[2];
    fourthOctet = parts[3];
  }

  private static int[] toInts(String str) {
    String bbs[] = str.split("\\.");
    if (bbs.length != 4) {
      throw new IllegalArgumentException("Invalid ip address: " + str);
    }
    int[] ints = new int[4];
    int j = 0;
    for (String p : bbs) {
      if (!Utility.isNumber(p)) {
        throw new IllegalArgumentException("Invalid ip addressing: " + p);
      }
      int i = Integer.parseInt(p);
      if (i > 255 || i < 0) {
        throw new IllegalArgumentException("Invalid ip addressing. out of range: " + i);
      }
      ints[j++] = i;
    }
    return ints;
  }

  public int getFirstOctet() {
    return firstOctet;
  }

  public void setFirstOctet(int firstOctet) {
    this.firstOctet = firstOctet;
  }

  public int getSecondOctet() {
    return secondOctet;
  }

  public void setSecondOctet(int secondOctet) {
    this.secondOctet = secondOctet;
  }

  public int getThirdOctet() {
    return thirdOctet;
  }

  public void setThirdOctet(int thirdOctet) {
    this.thirdOctet = thirdOctet;
  }

  public int getFourthOctet() {
    return fourthOctet;
  }

  public void setFourthOctet(int fourthOctet) {
    this.fourthOctet = fourthOctet;
  }

  /**
   * Returns true if this ip address is within the range of these two ips
   *
   * @param origin
   * @param end
   * @return
   */
  public boolean isWithin(Ipv4 origin, Ipv4 end) {
    /*
     * Determine if the all parts of the ip is equal or within the parts.
     */
    return this.compareTo(origin) >= 0 && this.compareTo(end) <= 0;

  }

  public static Ipv4 valueOf(long ip) {
    return new Ipv4(ip);
  }

  public static Ipv4 valueOf(String ip) {
    return new Ipv4(ip);
  }

  @Override
  public String toString() {
    return firstOctet + "." + secondOctet + "." + thirdOctet + "." + fourthOctet;
  }

  public long toLong() {
    /**
     * Multiplication of integers may result to overflow, so cast all integers to long before
     * multiplication.
     */
    long fo = this.firstOctet;
    long so = this.secondOctet;
    long to = this.thirdOctet;
    long fo_ = this.fourthOctet;
    long ip = fo * 16777216;
    ip += (so * 65536);
    ip += (to * 256);
    ip += fo_;
    return ip;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + this.firstOctet;
    hash = 67 * hash + this.secondOctet;
    hash = 67 * hash + this.thirdOctet;
    hash = 67 * hash + this.fourthOctet;
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
    final Ipv4 other = (Ipv4) obj;
    if (this.firstOctet != other.firstOctet) {
      return false;
    }
    if (this.secondOctet != other.secondOctet) {
      return false;
    }
    if (this.thirdOctet != other.thirdOctet) {
      return false;
    }
    if (this.fourthOctet != other.fourthOctet) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Ipv4 origin) {
    return Long.valueOf(toLong()).compareTo(origin.toLong());
  }
}
