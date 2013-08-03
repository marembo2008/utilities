/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

import java.util.Arrays;

/**
 *
 * @author marembo
 */
public class CharacterNumbering extends Numbering {

  protected static final char MIN_CHAR = 'a';
  protected static final char MAX_CHAR = 'z';
  protected boolean capitalize;
  protected final char minCharacter;
  protected char[] currentNumber;

  public CharacterNumbering() {
    this(false, MIN_CHAR);
  }

  public CharacterNumbering(String numbering) {
    this(false, MIN_CHAR, numbering.toCharArray());
  }

  public CharacterNumbering(char minCharacter) {
    this(minCharacter < MIN_CHAR, minCharacter);
  }

  public CharacterNumbering(boolean capitalize, char minCharacter) {
    this.capitalize = capitalize;
    this.minCharacter = (minCharacter + "").toLowerCase().charAt(0);
    if (this.minCharacter < MIN_CHAR || this.minCharacter > MAX_CHAR) {
      throw new NumberFormatException("Invalid character numbering scheme");
    }
    this.currentNumber = new char[]{this.minCharacter};
  }

  protected CharacterNumbering(boolean capitalize, char minCharacter, char[] currentNumber) {
    this.capitalize = capitalize;
    this.minCharacter = minCharacter;
    this.currentNumber = currentNumber;
  }

  protected CharacterNumbering(CharacterNumbering cn) {
    this(cn.capitalize, cn.minCharacter, cn.currentNumber);
  }

  @Override
  public CharacterNumbering next() {
    int index = this.currentNumber.length - 1;
    if (this.currentNumber[index] < MAX_CHAR) {
      this.currentNumber[index]++;
    } else {
      boolean incr = false;
      for (; index > -1; index--) {
        if (this.currentNumber[index] < MAX_CHAR) {
          //inc this, and reset all other indeces above this to minchar, and then break.
          this.currentNumber[index]++;
          Arrays.fill(currentNumber, index + 1, currentNumber.length, minCharacter);
          incr = true;
          break;
        }
      }
      if (!incr) {
        this.currentNumber = new char[this.currentNumber.length + 1];
        Arrays.fill(currentNumber, minCharacter);
      }
    }
    return this;
  }

  @Override
  public CharacterNumbering previous() {
    int index = this.currentNumber.length - 1;
    if (index == 0 && this.currentNumber[0] == minCharacter) {
      throw new NumberFormatException("Minimum character numbering range");
    }
    if (this.currentNumber[index] > MIN_CHAR) {
      this.currentNumber[index]--;
    } else {
      boolean decr = false;
      for (; index > -0; index--) {
        if (currentNumber[index] > minCharacter) {
          currentNumber[index]--;
          //set the rest to max char.
          Arrays.fill(currentNumber, MAX_CHAR);
          decr = true;
          break;
        }
      }
      if (!decr) {
        this.currentNumber = new char[this.currentNumber.length - 1];
        Arrays.fill(currentNumber, MAX_CHAR);
      }
    }
    return this;
  }

  @Override
  public void reset() {
    currentNumber = new char[]{minCharacter};
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (this.capitalize ? 1 : 0);
    hash = 67 * hash + this.minCharacter;
    hash = 67 * hash + Arrays.hashCode(this.currentNumber);
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
    final CharacterNumbering other = (CharacterNumbering) obj;
    if (this.capitalize != other.capitalize) {
      return false;
    }
    if (this.minCharacter != other.minCharacter) {
      return false;
    }
    if (!Arrays.equals(this.currentNumber, other.currentNumber)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String str = new String(currentNumber);
    if (capitalize) {
      str = str.toUpperCase();
    }
    return str;
  }

  @Override
  public int compareTo(Numbering t) {
    if (getClass() != t.getClass()) {
      throw new NumberFormatException("Cannot compare numbering of different classes: (" + getClass() + ", " + t.getClass() + ")");
    }
    return toString().compareTo(t.toString());
  }

  @Override
  public boolean isZeroCase(String number) {
    return number.equalsIgnoreCase(MIN_CHAR + "");
  }

  @Override
  public boolean isNext(String number) {
    return new CharacterNumbering(capitalize, minCharacter, currentNumber)
            .next()
            .equals(new CharacterNumbering(capitalize, minCharacter, number.toCharArray()));
  }

  @Override
  public Numbering getNumberingInstance(String number) {
    return new CharacterNumbering(capitalize, minCharacter, number.toCharArray());
  }
}
