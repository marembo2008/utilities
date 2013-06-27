/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.metric;

/**
 *
 * @author marembo
 */
public class MetricUnitException extends IllegalArgumentException {

  public MetricUnitException() {
  }

  public MetricUnitException(String s) {
    super(s);
  }

  public MetricUnitException(String message, Throwable cause) {
    super(message, cause);
  }

  public MetricUnitException(Throwable cause) {
    super(cause);
  }
}
