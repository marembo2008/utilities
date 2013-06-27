/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marembo
 */
public abstract class Numbering implements Comparable<Numbering> {

  /**
   * If the sequentialCharacter is true, then the {@link SequentialCharacterNumbering} will be used
   * for character classes. This is because it is impossible to determine the difference between
   * normal character sequencing and the group sequencing.
   *
   * @param instance
   * @param sequentialCharacter
   * @return
   */
  public static Numbering getInstance(String instance, boolean sequentialCharacter) {
    try {
      //fails if this is not an integer.
      return new IntegerNumbering(instance);
    } catch (Exception e) {
    }
    //we need to determine about the toRoman and character.
    Pattern p = Pattern.compile("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    if (p.matcher(instance).find()) {
      //return toRoman
      return new RomanNumbering(instance);
    } else {
      //make sure it does not have none character
      Pattern pp = Pattern.compile("[\\W*\\s*]+");
      if (pp.matcher(instance).find()) {
        //then we have either qualified or part numbers.
        //check if we have qualified should not contain none characters, but should have spaces
        Pattern p_ = Pattern.compile("[\\W+&&[^\\s]]+");
        Matcher mm_ = p_.matcher(instance);
        if (!mm_.find()) {
          try {
            return new IntegerCharacterNumbering(instance);
          } catch (Exception ex) {
          }
          //then we have qualified numbers. check if we are toRoman or integer.
          Pattern pp_ = Pattern.compile("\\d+");
          if (pp_.matcher(instance).find()) {
            return new QualifiedIntegerNumbering(instance);
          } else {
            return new QualifiedRomanNumbering(instance);
          }
        } else {
          //we must check if it contains part number.
          return new PartIntegerNumber(instance);
        }
      } else {
        if (!sequentialCharacter) {
          return new CharacterNumbering(instance);
        } else {
          return new SequentialCharacterNumbering(instance);
        }
      }
    }
  }

  public static boolean isZeroCaseNumber(String number) {
    try {
      return new IntegerNumbering().isZeroCase(number);
    } catch (Exception e) {
    }
    boolean isZero = new CharacterNumbering().isZeroCase(number);
    if (!isZero) {
      isZero = new RomanNumbering().isZeroCase(number);
    }
    return isZero;
  }

  /**
   * Evaluates the next number in this numbering scheme.
   */
  public abstract Numbering next();

  /**
   * Evaluates the previous number in this numbering scheme.
   */
  public abstract Numbering previous();

  /**
   * {@inheritDoc }
   * We do this here so that we force all subclasses to implement the toString method.
   *
   * @return
   */
  @Override
  public abstract String toString();

  /**
   * {@inheritDoc }
   * Force definition of equality.
   *
   * @param other
   * @return
   */
  @Override
  public abstract boolean equals(Object other);

  /**
   * {@inheritDoc }
   * Force overriding of hashcode.
   *
   * @return
   */
  @Override
  public abstract int hashCode();

  /**
   * Resets this Numbering scheme to the minimum.
   */
  public abstract void reset();

  /**
   * Returns true if the specified string number is the zero case (initial positive count) of the
   * numbering system.
   *
   * @param number
   * @return
   */
  public abstract boolean isZeroCase(String number);

  /**
   * Returns true if this is the next number in this numbering system.
   *
   * @param number
   * @return
   */
  public abstract boolean isNext(String number);

  /**
   * Returns an instance of this number, based on the value specified. This method should be called
   * after it has been ascertained that the specified number can be represented by this numbering
   * system.
   *
   * @param number
   * @return
   */
  public abstract Numbering getNumberingInstance(String number);
}
