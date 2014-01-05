/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 *
 * @author marembo
 */
public interface SelectionFilter<T, R> {

  /**
   * Returns a filtered value based on some conditions. Returns null if no condition is met.
   *
   * @param value
   * @return
   */
  R filter(T value);
}
