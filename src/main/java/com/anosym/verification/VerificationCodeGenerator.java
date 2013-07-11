/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.verification;

import java.security.SecureRandom;

/**
 *
 * @author kenn
 */
public class VerificationCodeGenerator {

  private static final double FOUR_DIGIT_MAX = Math.pow(10, 4);
  private static final String ALPHABET = "POIUYTREWQASDFGHJKLMNBVCXZqwertyuiopasdfghjklzxcvbnm";
  private static final String NUMS = "7418529630";
  private static final char[] CHARS = (ALPHABET.concat(NUMS)).toCharArray();
  private static final int LENGTH = 50;
  private static final SecureRandom RANDOM = new SecureRandom();

  public static long getFourDigitCode() {
    double val = RANDOM.nextDouble() * FOUR_DIGIT_MAX;
    return Math.round(val);
  }

  public static String getRandomAlphaNumString() {
    return getRandomAlphaNumString(CHARS, LENGTH);
  }

  public static String getRandomAlphaNumString(char[] allowed) {
    return getRandomAlphaNumString(allowed, LENGTH);
  }

  public static String getRandomAlphaNumString(int len) {
    return getRandomAlphaNumString(CHARS, len);
  }

  public static String getRandomAlphaNumString(char[] allowed, int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = allowed[RANDOM.nextInt(allowed.length)];
      sb.append(c);
    }
    return sb.toString();
  }
}
