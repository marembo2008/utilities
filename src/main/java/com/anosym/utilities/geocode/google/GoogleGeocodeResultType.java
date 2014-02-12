/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.google;

/**
 *
 * @author marembo
 */
public enum GoogleGeocodeResultType {

  street_address,
  route,
  intersection,
  political,
  country,
  administrative_area_level_1,
  administrative_area_level_2,
  administrative_area_level_3,
  colloquial_area,
  locality,
  sublocality,
  neighborhood,
  premise,
  subpremise,
  postal_code,
  postal_code_prefix,
  postal_town,
  natural_feature,
  airport,
  park,
  point_of_interest,
  subway_station,
  /**
   * Primarily for address_component
   */
  post_box,
  street_number,
  floor,
  room,
  transit_station,
  establishment,
  parking,
  bus_station,
  hospital,
  train_station;
}
