/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.geometry;

import com.anosym.utilities.SerializableList;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Polygon implements Geometry, Serializable {

    private SerializableList<Coordinate> points;

    public Polygon() {
    }

    public Polygon(SerializableList<Coordinate> points) {
        this.points = points;
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
