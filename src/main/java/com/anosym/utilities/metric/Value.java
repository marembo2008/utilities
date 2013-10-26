/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.metric;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author marembo
 */
public class Value implements Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(Value.class);
  private BigDecimal value;
  private MetricUnit unit;

  public Value(BigDecimal value, MetricUnit unit) {
    this.value = value;
    this.unit = unit;
  }

  public Value() {
  }

  public Value add(Value value) {
    Value v0 = value.getConvertedValue(unit);
    return new Value(getValue().add(v0.getValue()), unit);
  }

  public Value subtract(Value value) {
    Value v0 = value.getConvertedValue(unit);
    return new Value(getValue().subtract(v0.getValue()), unit);
  }

  public Value multiply(Value value) {
    Value v0 = value.getConvertedValue(unit);
    return new Value(getValue().multiply(v0.getValue()), unit);
  }

  public Value divide(Value value) {
    Value v0 = value.getConvertedValue(unit);
    return new Value(getValue().divide(v0.getValue(), MathContext.DECIMAL32), unit);
  }

  public Value divide(Value value, MathContext context) {
    Value v0 = value.getConvertedValue(unit);
    return new Value(getValue().divide(v0.getValue(), context), unit);
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public MetricUnit getUnit() {
    return unit;
  }

  public Value getConvertedValue(MetricUnit newUnit) {
    MetricUnit base = unit.getUnit(newUnit);
    BigDecimal newValue = value.multiply(base.getConversionFactor()).stripTrailingZeros();
    return new Value(newValue, newUnit);
  }

  public Value getSiUnitValue() {
    BigDecimal newValue = value.multiply(unit.getConversionFactor()).stripTrailingZeros();
    return new Value(newValue, unit.getSiUnit());
  }

  public void setUnit(MetricUnit unit) {
    this.unit = unit;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (this.value != null ? this.value.hashCode() : 0);
    hash = 67 * hash + (this.unit != null ? this.unit.hashCode() : 0);
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
    final Value other = (Value) obj;
    if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
      return false;
    }
    if (this.unit != other.unit && (this.unit == null || !this.unit.equals(other.unit))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return value + unit.getShortName();
  }
}
