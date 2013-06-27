/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

/**
 *
 * @author marembo
 */
public class IntegerNumbering extends Numbering {

  protected long currentNumber;
  private final long minNumber;

  public IntegerNumbering() {
    minNumber = currentNumber = 1l;
  }

  public IntegerNumbering(String number) {
    this(Long.parseLong(number));
  }

  public IntegerNumbering(long number) {
    this();
    this.currentNumber = number;
  }

  IntegerNumbering(IntegerNumbering i) {
    this.minNumber = i.minNumber;
    this.currentNumber = i.currentNumber;
  }

  @Override
  public IntegerNumbering next() {
    currentNumber++;
    return this;
  }

  @Override
  public IntegerNumbering previous() {
    if (currentNumber > minNumber) {
      currentNumber--;
    } else {
      throw new NumberFormatException("Numbering scheme reached beginning");
    }
    return this;
  }

  @Override
  public void reset() {
    this.currentNumber = minNumber;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 43 * hash + (int) (this.currentNumber ^ (this.currentNumber >>> 32));
    hash = 43 * hash + (int) (this.minNumber ^ (this.minNumber >>> 32));
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
    final IntegerNumbering other = (IntegerNumbering) obj;
    if (this.currentNumber != other.currentNumber) {
      return false;
    }
    if (this.minNumber != other.minNumber) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Numbering t) {
    if (t.getClass() == IntegerNumbering.class) {
      return Long.valueOf(currentNumber).compareTo(((IntegerNumbering) t).currentNumber);
    }
    throw new UnsupportedOperationException("Cannot compare Numbering of class: " + t.getClass() + " with Numbering of class: " + getClass());
  }

  @Override
  public String toString() {
    return currentNumber + "";
  }

  @Override
  public boolean isZeroCase(String number) {
    try {
      //this automatically checks that it is number.
      IntegerNumbering num = new IntegerNumbering(Long.parseLong(number));
      return new IntegerNumbering().equals(num);
    } catch (Exception e) {
    }
    return false;
  }

  @Override
  public boolean isNext(String number) {
    try {
      IntegerNumbering _this = new IntegerNumbering(this.currentNumber);
      return _this.next().equals(new IntegerNumbering(Long.parseLong(number)));
    } catch (Exception e) {
    }
    return false;
  }

  @Override
  public Numbering getNumberingInstance(String number) {
    return new IntegerNumbering(number);
  }
}
