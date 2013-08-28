/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.jasperreport;

import com.anosym.vjax.annotations.Attribute;
import com.anosym.vjax.annotations.Converter;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class JReportElement implements Serializable {

  private static final long serialVersionUID = 2429489824291L;
  private JMode mode;
  private int x;
  private int y;
  private int width;
  private int height;
  private String color;

  public JReportElement() {
  }

  public JReportElement(JMode mode, int x, int y, int width, int height, String color) {
    this.mode = mode;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Attribute("opaque")
  public JMode getMode() {
    return mode;
  }

  public void setMode(JMode mode) {
    this.mode = mode;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
