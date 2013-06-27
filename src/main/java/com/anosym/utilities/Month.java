/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 *
 * @author marembo
 */
public enum Month {

  January(1, "Jan"),
  February(2, "Feb"),
  March(3, "Mar"),
  April(4, "Apr"),
  May(5, "May"),
  June(6, "Jun"),
  July(7, "Jul"),
  August(8, "Aug"),
  September(9, "Sep"),
  October(10, "Oct"),
  November(11, "Nov"),
  December(12, "Dec");
  private int index;
  private String shortName;

  private Month(int index, String shortName) {
    this.index = index;
    this.shortName = shortName;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
}
