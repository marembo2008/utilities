/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.geometry;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Circle implements Geometry, Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(Circle.class);
  private Coordinate centre;
  private double radius;

  public Circle() {
  }

  public Circle(Coordinate centre, double radius) {
    this.centre = centre;
    this.radius = radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }

  public void setCentre(Coordinate centre) {
    this.centre = centre;
  }

  public Coordinate getCentre() {
    return centre;
  }

  public double geometryLength() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public GeometryType getType() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Geometry nextPoint() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Geometry geometricCentre() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public boolean isWithin(Geometry geometry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public boolean isIntersecting(Geometry... geometries) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public double radialDistance(Geometry geometry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public double metricDistance(Geometry geometry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public double radialDirection(Geometry geometry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
