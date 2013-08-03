/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

/**
 *
 * @author marembo
 */
public class RomanNumbering extends IntegerNumbering {

  public RomanNumbering() {
    super();
  }

  public RomanNumbering(long minNumber) {
    super(minNumber);
  }

  public RomanNumbering(String roman) {
    super(decode(roman));
  }

  enum Numeral {

    I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100), CD(400), D(500), CM(900), M(1000);
    int weight;

    Numeral(int weight) {
      this.weight = weight;
    }
  };

  public static String toRoman(long n) {

    if (n <= 0) {
      throw new IllegalArgumentException();
    }

    StringBuilder buf = new StringBuilder();

    final Numeral[] values = Numeral.values();
    for (int i = values.length - 1; i >= 0; i--) {
      while (n >= values[i].weight) {
        buf.append(values[i]);
        n -= values[i].weight;
      }
    }
    return buf.toString();
  }

  private static int decodeSingle(char letter) {
    switch (letter) {
      case 'M':
        return 1000;
      case 'D':
        return 500;
      case 'C':
        return 100;
      case 'L':
        return 50;
      case 'X':
        return 10;
      case 'V':
        return 5;
      case 'I':
        return 1;
      default:
        return 0;
    }
  }

  static int decode(String roman) {
    int result = 0;
    String uRoman = roman.toUpperCase(); //case-insensitive
    for (int i = 0; i < uRoman.length() - 1; i++) {//loop over all but the last character
      //if this character has a lower value than the next character
      if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i + 1))) {
        //subtract it
        result -= decodeSingle(uRoman.charAt(i));
      } else {
        //add it
        result += decodeSingle(uRoman.charAt(i));
      }
    }
    //decode the last character, which is always added
    result += decodeSingle(uRoman.charAt(uRoman.length() - 1));
    return result;
  }

  public long toInteger() {
    return currentNumber;
  }

  @Override
  public String toString() {
    return toRoman(currentNumber);
  }

  @Override
  public boolean isZeroCase(String number) {
    return new RomanNumbering().equals(new RomanNumbering(number));
  }

  @Override
  public boolean isNext(String number) {
    RomanNumbering rn = new RomanNumbering(currentNumber);
    return rn.next().equals(new RomanNumbering(number));
  }

  @Override
  public RomanNumbering getNumberingInstance(String number) {
    return new RomanNumbering(number);
  }
}
