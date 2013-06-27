/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a number divided into sections. The sections can be separated by different delimiters.
 *
 * @author marembo
 */
public class PartIntegerNumber extends Numbering {

  /**
   * The parts of this number.
   */
  private List<IntegerNumbering> numberParts;
  /**
   * The parts delimiter. The size of this map must be equal to
   * {@link PartIntegerNumber#numberParts.size()}
   */
  private Map<Integer, String> delimiters;

  public PartIntegerNumber() {
    numberParts = new ArrayList<IntegerNumbering>(Arrays.asList(new IntegerNumbering()));
    delimiters = new HashMap<Integer, String>();
  }

  public PartIntegerNumber(String delimitedNumber) {
    numberParts = new ArrayList<IntegerNumbering>();
    delimiters = new HashMap<Integer, String>();
    parsePartNumber(delimitedNumber);
  }

  /**
   * We perform deeper copy here.
   *
   * @param numberParts
   * @param delimiters
   */
  PartIntegerNumber(List<IntegerNumbering> numberParts, Map<Integer, String> delimiters) {
    this.numberParts = new ArrayList<IntegerNumbering>();
    this.delimiters = new HashMap<Integer, String>(delimiters);
    for (IntegerNumbering i : numberParts) {
      this.numberParts.add(new IntegerNumbering(i));
    }
  }

  /**
   * We perform deeper copy here.
   *
   * @param numberParts
   * @param delimiters
   */
  PartIntegerNumber(PartIntegerNumber i) {
    this(i.numberParts, i.delimiters);
  }

  public boolean isSubpart(Numbering numbering) {
    if (numbering instanceof PartIntegerNumber) {
      PartIntegerNumber pin = (PartIntegerNumber) numbering;
      int s = this.numberParts.size();
      int s0 = pin.numberParts.size();
      return Math.abs(s0 - s) > 0;
    }
    return false;
  }

  public long getPart(int index) {
    return this.numberParts.get(index).currentNumber;
  }

  public boolean isPossibleNext(Numbering numbering) {
    if (numbering instanceof PartIntegerNumber) {
      PartIntegerNumber pin = (PartIntegerNumber) numbering;
      //check if the
      int s0 = pin.numberParts.size(), s1 = this.numberParts.size();
      int s = Math.min(s0, s1);
      for (int i = 0; i < s; i++) {
        IntegerNumbering i0 = this.numberParts.get(i);
        IntegerNumbering i1 = pin.numberParts.get(i);
        if (!i0.equals(i1)) {
          return new IntegerNumbering(i0).next().equals(i1) || i == (s - 1); //if we are at the head of this part integer numbering
        }
      }
    }
    return false;
  }

  private void parsePartNumber(String number) {
    number = number.trim();
    String regex = "\\s*\\W+\\s*";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(number);
    int numStart = 0;
    int i = 0;
    while (m.find()) {
      String delim = number.substring(m.start(), m.end()).trim();
      String num = number.substring(numStart, m.start()).trim();
      this.numberParts.add(new IntegerNumbering(num));
      this.delimiters.put(i++, delim);
      numStart = m.end();
    }
    /**
     * Either adds the last number of the part integer or adds the integer without a delimiter.
     */
    this.numberParts.add(new IntegerNumbering(number.substring(numStart)));
  }

  /**
   * {@inheritDoc }
   * By default this only returns the next number for the last part of this part number.
   *
   * @return
   */
  @Override
  public PartIntegerNumber next() {
    return next(this.numberParts.size() - 1);
  }

  /**
   * Returns the next, reseting all other parts after this part to zero case {That is to one}
   *
   * @return
   */
  public PartIntegerNumber next(int part) {
    this.numberParts.get(part).next();
    for (int i = (part + 1); i < this.numberParts.size(); i++) {
      this.numberParts.get(i).reset();
    }
    return this;
  }

  @Override
  public PartIntegerNumber previous() {
    return previous(this.numberParts.size() - 1);
  }

  /**
   * Returns the previous for the specified part, reseting all other parts after this.
   *
   * @param part
   * @return
   */
  public PartIntegerNumber previous(int part) {
    this.numberParts.get(part).previous();
    for (int i = (part + 1); i < this.numberParts.size(); i++) {
      this.numberParts.get(i).reset();
    }
    return this;
  }

  @Override
  public String toString() {
    int i = 0;
    String str = this.numberParts.get(i).toString();
    for (Integer delimI : delimiters.keySet()) {
      str += delimiters.get(delimI);
      str += this.numberParts.get(++i).toString();
    }
    return str;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 73 * hash + (this.numberParts != null ? this.numberParts.hashCode() : 0);
    hash = 73 * hash + (this.delimiters != null ? this.delimiters.hashCode() : 0);
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
    final PartIntegerNumber other = (PartIntegerNumber) obj;
    if (this.numberParts != other.numberParts && (this.numberParts == null || !this.numberParts.equals(other.numberParts))) {
      return false;
    }
    if (this.delimiters != other.delimiters && (this.delimiters == null || !this.delimiters.equals(other.delimiters))) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc }
   * Resets only the last part of this PartNumber
   */
  @Override
  public void reset() {
    reset(this.numberParts.size() - 1);
  }

  /**
   * resets this part number from the partNumber index specified.
   *
   * @param part
   * @return
   */
  public PartIntegerNumber reset(int fromPart) {
    for (; fromPart < this.numberParts.size(); fromPart++) {
      this.numberParts.get(fromPart).reset();
    }
    return this;
  }

  @Override
  public boolean isZeroCase(String number) {
    return new PartIntegerNumber().equals(new PartIntegerNumber(number));
  }

  @Override
  public boolean isNext(String number) {
    PartIntegerNumber _this = new PartIntegerNumber(this);
    PartIntegerNumber _other = new PartIntegerNumber(number);
    return _this.next().equals(_other);
  }

  public boolean isNext(String number, int part) {
    PartIntegerNumber _this = new PartIntegerNumber(this);
    PartIntegerNumber _other = new PartIntegerNumber(number);
    return _this.next(part).equals(_other);
  }

  @Override
  public PartIntegerNumber getNumberingInstance(String number) {
    return new PartIntegerNumber(number);
  }

  /**
   * {@inheritDoc }
   * For this comparison to work, then the two numbers must have the same size.
   *
   * @param t
   * @return
   */
  @Override
  public int compareTo(Numbering t) {
    if (t.getClass() != getClass()) {
      throw new IllegalArgumentException("Invalid comparison: " + getClass() + " cannot be compared with: " + t.getClass());
    }
    PartIntegerNumber pin = (PartIntegerNumber) t;
    if (pin.numberParts.size() != this.numberParts.size()) {
      throw new IllegalArgumentException("Invalid Number Comparison: "
              + "Cannot compare PartIntegerNumbering with different sizes: (" + this.numberParts.size() + ","
              + pin.numberParts.size() + ")");
    }
    //Do we have the same delimiters.
    if (!this.delimiters.equals(pin.delimiters)) {
      throw new IllegalArgumentException("Cannot compare numbers of different delimiters");
    }
    for (int i = 0; i < pin.numberParts.size(); i++) {
      IntegerNumbering i0 = pin.numberParts.get(i);
      IntegerNumbering j0 = this.numberParts.get(i);
      int cmp = j0.compareTo(i0);
      if (cmp == 0) {
        continue;
      }
      /*
       * If the last part are not equal, we do not need to perform anu further comparison.
       */
      return cmp;
    }
    //if we reach here, the two numbers must be equal.
    return 0;
  }

  public void set(int i, long integerValue) {
    this.numberParts.get(i).currentNumber = integerValue;
    //reset every part of this number beyond here
    for (i = i+1; i < this.numberParts.size(); i++) {
      this.numberParts.get(i).reset();
    }
  }
}
