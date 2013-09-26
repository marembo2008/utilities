/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;

/**
 *
 * @author Marembo
 */
public class Duplex<F, S> implements Serializable {

  private F firstValue;
  private S secondValue;

  public Duplex() {
  }

  public Duplex(Duplex<F, S> d) {
    this.firstValue = d.firstValue;
    this.secondValue = d.secondValue;
  }

  public Duplex(F firstValue, S secondValue) {
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }

  public F getFirstValue() {
    return firstValue;
  }

  public void setFirstValue(F firstValue) {
    this.firstValue = firstValue;
  }

  public S getSecondValue() {
    return secondValue;
  }

  public void setSecondValue(S secondValue) {
    this.secondValue = secondValue;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Duplex<F, S> other = (Duplex<F, S>) obj;
    if (this.firstValue != other.firstValue && (this.firstValue == null || !this.firstValue.equals(other.firstValue))) {
      return false;
    }
    if (this.secondValue != other.secondValue && (this.secondValue == null || !this.secondValue.equals(other.secondValue))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + (this.firstValue != null ? this.firstValue.hashCode() : 0);
    hash = 19 * hash + (this.secondValue != null ? this.secondValue.hashCode() : 0);
    return hash;
  }

  @Override
  public String toString() {
    return "{" + firstValue + "; " + secondValue + '}';
  }

  public synchronized void set(F first, S second) {
    this.firstValue = first;
    this.secondValue = second;
  }
}
