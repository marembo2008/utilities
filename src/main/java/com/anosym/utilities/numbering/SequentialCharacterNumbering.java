/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

import java.util.Arrays;

/**
 * Here we increase the character sequence as a group. i.e. (aa -> bb -> cc)
 *
 * @author marembo
 */
public class SequentialCharacterNumbering extends CharacterNumbering {

  public SequentialCharacterNumbering() {
  }

  public SequentialCharacterNumbering(String numbering) {
    super(numbering);
  }

  public SequentialCharacterNumbering(char minCharacter) {
    super(minCharacter);
  }

  public SequentialCharacterNumbering(boolean capitalize, char minCharacter) {
    super(capitalize, minCharacter);
  }

  protected SequentialCharacterNumbering(boolean capitalize, char minCharacter, char[] currentNumber) {
    super(capitalize, minCharacter, currentNumber);
  }

  @Override
  public CharacterNumbering next() {
    int index = this.currentNumber.length - 1;
    if (this.currentNumber[index] < MAX_CHAR) {
      //here we increase every character in the array.
      for (; index > -1; index--) {
        this.currentNumber[index]++;
      }
    } else {
      //in this case, we add one more char and reset every part to inital zero.
      this.currentNumber = new char[this.currentNumber.length + 1];
      Arrays.fill(currentNumber, minCharacter);
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
      //here we increase every character in the array.
      for (; index > -1; index--) {
        this.currentNumber[index]--;
      }
    } else {
      this.currentNumber = new char[this.currentNumber.length - 1];
      Arrays.fill(currentNumber, MAX_CHAR);
    }
    return this;
  }

  @Override
  public boolean isNext(String number) {
    return new SequentialCharacterNumbering(capitalize, minCharacter, currentNumber)
            .next()
            .equals(new SequentialCharacterNumbering(capitalize, minCharacter, number.toCharArray()));
  }

  @Override
  public SequentialCharacterNumbering getNumberingInstance(String number) {
    return new SequentialCharacterNumbering(capitalize, minCharacter, number.toCharArray());
  }
}
