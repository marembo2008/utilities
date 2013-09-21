/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author marembo
 */
public final class IdGenerator {

  public static final String ID_GENERATOR_EPOCH = "com.anosym.idgenerator.epoch";
  private static final int LONG_STR_LEN = (Long.MAX_VALUE + "").length();

  public static Long generateId() {
    Long id = System.currentTimeMillis();
    String epochStr = System.getProperty(ID_GENERATOR_EPOCH, "2000-01-01 00:00:00");
    if (epochStr != null) {
      Calendar epoch = FormattedCalendar.createInstance(epochStr);
      id = id - epoch.getTimeInMillis();
    }
    return id;
  }

  public static String generateId(String prefix) {
    return prefix + generateId();
  }

  public static Long generateLongId(int length) {
    Random r = new Random(System.nanoTime());
    int val = (int) Math.pow(10, length - 1);
    return Long.valueOf(r.nextInt(val) + val);
  }

  public static Long generateSafeAndUniqueId(int length) {
    //we add simple randomness so that if it were to be called by twi threads at the same time, we are guaranteed of uniques.
    long val = System.nanoTime() + new Random(System.currentTimeMillis()).nextInt(5);
    return val % ((long) (Math.pow(10, length)));
  }

  public static Long generateShortId() {
    String epoch = System.getProperty(ID_GENERATOR_EPOCH);
    if (epoch != null && !epoch.isEmpty()) {
      Calendar c = FormattedCalendar.parseISODate(epoch);
      if (c != null) {
        return (System.currentTimeMillis() - c.getTimeInMillis());
      }
    }
    return System.currentTimeMillis();
  }

  public static String generateStringId() {
    return generateId().toString();
  }

  public static String generateIdentifier() {
    return generateIdentifier(10);
  }

  public static String generateIdentifier(int length) {
    final char[] characters = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    String id = "";
    Random m = new SecureRandom(generateStringId().getBytes());
    for (int i = 0; i < length; i++) {
      int r = m.nextInt(characters.length);
      char c = characters[r];
      if (r % 2 == 0) {
        c = Character.toUpperCase(c);
      }
      id += c;
    }
    return id;
  }

  public static Long generateId(Class<?> clazz) {
    String fullName = clazz.getCanonicalName();
    byte[] bytes = fullName.getBytes();
    String seed = "";
    for (byte b : bytes) {
      seed += "" + b;
    }
    return new Random(new BigInteger(seed).longValue()).nextLong();
  }

  public static int generateShortId(Class<?> clazz) {
    String fullName = clazz.getCanonicalName();
    byte[] bytes = fullName.getBytes();
    String seed = "";
    for (byte b : bytes) {
      seed += "" + b;
    }
    return new Random(new BigInteger(seed).longValue()).nextInt(100000) + 1000;
  }

  public static String generateId(Class<?> clazz, int length) {
    String fullName = clazz.getCanonicalName();
    return generateId(fullName, length);
  }

  public static String generateId(String uniqueData, int length) {
    byte[] bytes = uniqueData.getBytes();
    String seed = "";
    for (byte b : bytes) {
      seed += "" + b;
    }
    int maxValue = (int) Math.pow(10, length - 1);
    return (new Random(new BigInteger(seed).longValue()).nextInt(maxValue) + maxValue) + "";
  }

  public static String generateId(int length, String prefix) {
    return prefix + generateLongId(length);
  }

  public static Long generateLongId(Class<?> clazz, int length) {
    String fullName = clazz.getCanonicalName();
    byte[] bytes = fullName.getBytes();
    String seed = "";
    for (byte b : bytes) {
      seed += "" + b;
    }
    int maxValue = (int) Math.pow(10, length - 1);
    return Long.valueOf(new Random(new BigInteger(seed).longValue()).nextInt(maxValue) + maxValue);
  }

  public static Long generateLongId(String uniqueCode, int length) {
    byte[] bytes = uniqueCode.getBytes();
    String seed = "";
    for (byte b : bytes) {
      seed += "" + b;
    }
    int maxValue = (int) Math.pow(10, length - 1);
    return Long.valueOf(new Random(new BigInteger(seed).longValue()).nextInt(maxValue) + maxValue);
  }

  /**
   * Guaranteed to return the same id for every class instance
   *
   * @param clazz
   * @return
   */
  public static String generateStringId(Class<?> clazz) {
    return Math.abs(generateId(clazz)) + "";
  }

  /**
   * Guaranteed to generate the same UID for the class
   *
   * @param clazz
   * @return
   */
  public static Long serialVersionUID(Class<?> clazz) {
    return generateId(clazz);
  }

  static void populateRandm(final Set<Long> sets) {
    new Thread(new Runnable() {
      public void run() {
        for (int i = 0; i < 100000; i++) {
          synchronized (sets) {
            Long id = generateId();
            sets.add(id);
          }
        }
        synchronized (sets) {
          sets.notifyAll();
        }
      }
    }).start();
  }

  public static void main(String[] args) {
    for (int i = 0; i < 1000; i++) {
      System.out.println(generateUniqueId());
    }
  }

  public static String generateUniqueId() {
    String id = IdGenerator.generateStringId();
    byte[] bbs = id.getBytes();
    //we know all l this is just integers.
    //add at leas
    Random r = new Random(System.currentTimeMillis());
    for (int i = 0; i < bbs.length; i++) {
      byte b = bbs[i];
      int n = r.nextInt(26);
      if (n > 0) {
        int k = (57 - b) + 8 + n;
        b += k;
        bbs[i] = b;
      }
    }
    return new String(bbs);
  }
}
