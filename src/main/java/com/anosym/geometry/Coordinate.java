/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.geometry;

import com.anosym.utilities.Utility;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Coordinate extends Point2D.Double implements Geometry, Serializable {

  private double altitude;

  public Coordinate(double x, double y) {
    setLocation(x, y);
  }

  public Coordinate(double x, double y, double alt) {
    setLocation(x, y);
    this.altitude = alt;
  }

  public Coordinate() {
    this(0.0, 0.0);
  }

  public Coordinate(Point2D coord) {
    this(coord.getX(), coord.getY());
  }

  @Override
  public final void setLocation(double x, double y) {
    super.setLocation(Utility.roundOff(x, 7), Utility.roundOff(y, 7));
  }

  @Override
  public void setLocation(Point2D p) {
    super.setLocation(p.getX(), p.getY());
  }

  public double distance(Coordinate other) {
    return distanceL(getY(), getX(), other.getY(), other.getX());
  }

  @Override
  public double distance(Point2D other) {
    return distanceL(getY(), getX(), other.getY(), other.getX());
  }

  public double distance(Point other) {
    return distanceL(getY(), getX(), other.getY(), other.getX());
  }

  private double distanceL(double lat1, double lon1, double lat2, double lon2) {
    lat1 = Math.toRadians(lat1);
    lon1 = Math.toRadians(lon1);
    lat2 = Math.toRadians(lat2);
    lon2 = Math.toRadians(lon2);
    return 6378388 * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
  }

  public double getAltitude() {
    return altitude;
  }

  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }

  public double getLatitude() {
    return getY();
  }

  public void setLatitude(double latitude) {
    this.setLocation(getX(), latitude);
  }

  public double getLongitude() {
    return getX();
  }

  public void setLongitude(double longitude) {
    this.setLocation(longitude, getX());
  }

  public double geometryLength() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public GeometryType getType() {
    return GeometryType.POINT;
  }

  public Geometry nextPoint() {
    throw new NoMorePointsException("No next elements in coordinates");
  }

  public Geometry geometricCentre() {
    return this;
  }

  public boolean isWithin(Geometry geometry) {
    if (geometry.getType() == GeometryType.POINT) {
      return this.equals(geometry);
    }
    return false;
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

  public String linearString() {
    String lon = this.getX() + "", lat = getY() + "";
    //replace '.' with E
    lon = lon.replace('.', 'E');
    lat = lat.replace('.', 'E');
    return lon + "_" + lat;
  }
}
