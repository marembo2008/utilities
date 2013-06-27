/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Most methods consider the integer part to be constant.
 *
 * @author marembo
 */
public class IntegerCharacterNumbering extends Numbering {

  private IntegerNumbering integerNumbering;
  private CharacterNumbering characterNumbering;

  public IntegerCharacterNumbering() {
    this.integerNumbering = new IntegerNumbering();
    this.characterNumbering = new CharacterNumbering();
  }

  public IntegerCharacterNumbering(String number) {
    parseNumber(number);
  }

  private IntegerCharacterNumbering(IntegerCharacterNumbering characterNumbering) {
    this.integerNumbering = new IntegerNumbering(characterNumbering.integerNumbering);
    this.characterNumbering = new CharacterNumbering(characterNumbering.characterNumbering);
  }

  private void parseNumber(String number) {
    String[] parts = number.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
    System.out.println(Arrays.toString(parts));
    integerNumbering = new IntegerNumbering(parts[0]);
    characterNumbering = new CharacterNumbering(parts[1]);
  }

  public boolean isPossibleNext(Numbering numbering) {
    if (getClass() != numbering.getClass()) {
      return false;
    }
    IntegerCharacterNumbering qin = (IntegerCharacterNumbering) numbering;
    return this.integerNumbering.equals(qin.integerNumbering) && qin.characterNumbering.compareTo(this.characterNumbering) > 0;
  }

  /**
   * {@inheritDoc }
   * Considers the character part only
   *
   * @return
   */
  @Override
  public IntegerCharacterNumbering next() {
    this.characterNumbering.next();
    return this;
  }

  @Override
  public IntegerCharacterNumbering previous() {
    this.characterNumbering.previous();
    return this;
  }

  @Override
  public String toString() {
    return this.integerNumbering + "" + this.characterNumbering;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + (this.integerNumbering != null ? this.integerNumbering.hashCode() : 0);
    hash = 37 * hash + (this.characterNumbering != null ? this.characterNumbering.hashCode() : 0);
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
    final IntegerCharacterNumbering other = (IntegerCharacterNumbering) obj;
    if (this.integerNumbering != other.integerNumbering && (this.integerNumbering == null || !this.integerNumbering.equals(other.integerNumbering))) {
      return false;
    }
    if (this.characterNumbering != other.characterNumbering && (this.characterNumbering == null || !this.characterNumbering.equals(other.characterNumbering))) {
      return false;
    }
    return true;
  }

  @Override
  public void reset() {
    this.characterNumbering.reset();
  }

  @Override
  public boolean isZeroCase(String number) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isNext(String number) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public IntegerCharacterNumbering getNumberingInstance(String number) {
    return new IntegerCharacterNumbering(number);
  }

  @Override
  public int compareTo(Numbering t) {
    if (t.getClass() == getClass()) {
      IntegerCharacterNumbering icn = (IntegerCharacterNumbering) t;
      int cmp = integerNumbering.compareTo(icn.integerNumbering);
      return cmp == 0 ? this.characterNumbering.compareTo(icn.characterNumbering) : cmp;
    }
    return -1;
  }
}
