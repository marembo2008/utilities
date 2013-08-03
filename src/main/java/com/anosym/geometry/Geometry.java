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
public interface Geometry extends Serializable {

    /**
     * Returns the geometrical distance covered by this geometry
     * @return
     */
    public double geometryLength();

    /**
     * Returns the type of this geometry
     * @return
     */
    public GeometryType getType();

    /**
     * A call to this method will successively return the next point of this geometry in order.
     * After the NoMorePointsException is thrown, it is guaranteed that the next call to this method will
     * return the first point in the geometry
     * @return
     * @throws NoMorePointsException when there are no more points in the geometry
     */
    public Geometry nextPoint();

    /**
     * Returns a geometry representing the geometric centre of this geometry
     * @return
     */
    public Geometry geometricCentre();

    /**
     * Returns true if this geometry is within the boundaries of the specified geometry
     * @param geometry
     * @return
     */
    public boolean isWithin(Geometry geometry);

    /**
     * Return true if this geometries and the specified geometries intersects.
     * for this method to return true, all the geometries must intersect each other.
     * @param geometries
     * @return
     */
    public boolean isIntersecting(Geometry... geometries);

    /**
     * Returns the radial distance, in radians, between this geometry and the specified geometry
     * the reference for this distance is the centre of the earth
     * @param geometry
     * @return
     */
    public double radialDistance(Geometry geometry);

    /**
     * Returns metric distance, in metres, between this geometry and the specified geometry
     * @param geometry
     * @return
     */
    public double metricDistance(Geometry geometry);

    /**
     * Returns the direction of this geometry, in radians, as seen when observing the point location
     * of this geometry from the specified geometry
     * @param geometry the reference geometry
     * @return radial distance
     */
    public double radialDirection(Geometry geometry);
}
