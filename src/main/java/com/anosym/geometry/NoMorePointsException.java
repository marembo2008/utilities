/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.geometry;

import java.util.NoSuchElementException;

/**
 *
 * @author marembo
 */
public class NoMorePointsException extends NoSuchElementException {

  /**
   * Creates a new instance of
   * <code>NoMorePointsException</code> without detail message.
   */
  public NoMorePointsException() {
  }

  /**
   * Constructs an instance of
   * <code>NoMorePointsException</code> with the specified detail message.
   *
   * @param msg the detail message.
   */
  public NoMorePointsException(String msg) {
    super(msg);
  }
}
