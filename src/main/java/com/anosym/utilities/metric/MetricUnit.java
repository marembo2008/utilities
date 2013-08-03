/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.metric;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author marembo
 */
public class MetricUnit implements Serializable {

  /**
   * The SI unit for this unit. If this is itself an SI unit, then it will point to itself or it
   * will be null.
   */
  private MetricUnit siUnit;
  private String longName;
  private String shortName;
  private BigDecimal conversionFactor;

  public MetricUnit(MetricUnit siUnit, String longName, String shortName, BigDecimal conversionFactor) {
    this.siUnit = siUnit;
    this.longName = longName;
    this.shortName = shortName;
    this.conversionFactor = conversionFactor;
  }

  private MetricUnit(String longName, String shortName) {
    this.longName = longName;
    this.shortName = shortName;
    this.conversionFactor = BigDecimal.ONE;
    setSiUnit(this);
  }

  public static MetricUnit getSIUnit(String longName, String shortName) {
    return new MetricUnit(longName, shortName);
  }

  public MetricUnit getSiUnit() {
    return siUnit;
  }

  public final void setSiUnit(MetricUnit siUnit) {
    this.siUnit = siUnit;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  /**
   * Returns a new unit with the
   * <code>newBase</code> as the relative SI unit. Both
   * <code>this</code> and
   * <code>newBase</code> must have the same si unit base or the chain of SI unit must at least
   * converge to a common base.
   *
   * @param newBase the new SI unit base for <code>this</code> unit.
   * @return a new metric unit with <code>newBase</code> as the si unit.
   * @throws MetricUnitException when the exception cannot be performed.
   */
  public MetricUnit getUnit(MetricUnit newBase) {
    if (!this.siUnit.equals(newBase.siUnit)) {
      if (!this.siUnit.isSIUnit()) {
        MetricUnit unit = new MetricUnit(this.siUnit.siUnit, longName, shortName,
                this.conversionFactor.multiply(this.siUnit.conversionFactor)
                .stripTrailingZeros());
        return unit.getUnit(newBase);
      } else if (!newBase.siUnit.isSIUnit()) {
        MetricUnit unit = new MetricUnit(newBase.siUnit.siUnit, newBase.longName, newBase.shortName,
                newBase.conversionFactor.multiply(newBase.siUnit.conversionFactor)
                .stripTrailingZeros());
        return getUnit(unit);
      } else {
        throw new MetricUnitException("SI unit bases do not conform. Conversion failure: " + this.siUnit + " != " + newBase.siUnit);
      }
    }
    BigDecimal newBaseConversionFactor = this.conversionFactor
            .divide(newBase.conversionFactor, MathContext.DECIMAL32)
            .stripTrailingZeros();
    return new MetricUnit(newBase, longName, shortName, newBaseConversionFactor);
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public BigDecimal getConversionFactor() {
    return conversionFactor;
  }

  public void setConversionFactor(BigDecimal conversionFactor) {
    this.conversionFactor = conversionFactor;
  }

  public boolean isSIUnit() {
    return this.siUnit == null || (this.siUnit == this && this.conversionFactor.equals(BigDecimal.ONE));
  }

  @Override
  public int hashCode() {
    int hash = 7;
    if (this.siUnit.isSIUnit()) {
      hash = 67 * hash + (this.siUnit.longName != null ? this.siUnit.longName.hashCode() : 0);
      hash = 67 * hash + (this.siUnit.shortName != null ? this.siUnit.shortName.hashCode() : 0);
      hash = 67 * hash + (this.siUnit.conversionFactor != null ? this.siUnit.conversionFactor.hashCode() : 0);
    } else {
      hash = 67 * hash + (this.siUnit != null ? this.siUnit.hashCode() : 0);
    }
    hash = 67 * hash + (this.longName != null ? this.longName.hashCode() : 0);
    hash = 67 * hash + (this.shortName != null ? this.shortName.hashCode() : 0);
    hash = 67 * hash + (this.conversionFactor != null ? this.conversionFactor.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MetricUnit other = (MetricUnit) obj;
    if (!isSIUnit()) {
      /**
       * We avoid recursive call that will result to a stackoverflow problem if this is an si unit.
       */
      if (this.siUnit != other.siUnit && (this.siUnit == null || !this.siUnit.equals(other.siUnit))) {
        return false;
      }
    }
    if ((this.longName == null) ? (other.longName != null) : !this.longName.equals(other.longName)) {
      return false;
    }
    if ((this.shortName == null) ? (other.shortName != null) : !this.shortName.equals(other.shortName)) {
      return false;
    }
    if (this.conversionFactor != other.conversionFactor && (this.conversionFactor == null || !this.conversionFactor.equals(other.conversionFactor))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return shortName + "(" + longName + ") " + ((!isSIUnit()) ? ("= " + conversionFactor + ((siUnit.isSIUnit() ? siUnit.shortName + "(" + siUnit.longName + ")" : siUnit))) : "");
  }
}
