/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author marembo
 */
public final class IdGenerator {

  public static final String ID_GENERATOR_EPOCH = "id.generator.epoch.iso";
  private static final int LONG_STR_LEN = (Long.MAX_VALUE + "").length();

  public static Long generateId() {
    Long id = null;
    String epoch = System.getProperty(ID_GENERATOR_EPOCH);
    if (epoch != null && !epoch.isEmpty()) {
      Calendar c = FormattedCalendar.parseISODate(epoch);
      if (c != null) {
        id = System.nanoTime() - c.getTimeInMillis();
      }
    } else {
      id = System.nanoTime();
    }
    id = Math.abs(id);
    String idStr = id.toString();
    int maxAdd = (LONG_STR_LEN - idStr.length() - 1);
    Random r = new Random(id);
    int index = r.nextInt(idStr.length());
    if (index > -1 && maxAdd > 0) {
      int maxValue = (int) Math.pow(10, maxAdd);
      int value = Math.abs(r.nextInt(maxValue));
      String p1 = idStr.substring(0, index);
      String p2 = idStr.substring(index);
      idStr = p1 + value + p2;
      return Long.parseLong(idStr);
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
    String id = generateId().toString();
    byte[] bbs = id.getBytes();
    for (int i = 0; i < bbs.length; i++) {
      byte b = bbs[i];
      //randomly add a value that makes the a number or a character without
      byte c = (byte) ((Math.abs(new Random(System.currentTimeMillis()).nextInt(200)) + b + 48) % 122);
      bbs[i] = c;
    }
    return new String(bbs);
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
}
