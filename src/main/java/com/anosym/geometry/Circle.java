/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.geometry;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Circle implements Geometry, Serializable {

    private Coordinate centre;
    private double radius;

    public Circle() {
    }

    public Circle(Coordinate centre, double radius) {
        this.centre = centre;
        this.radius = radius;
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
