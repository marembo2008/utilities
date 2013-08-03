/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.formatter;

import com.anosym.utilities.Duplex;

/**
 *
 * @author marembo
 */
public class CurrencyFormatter {

  private String decimalSeparator;
  private String groupSeparator;
  private int groups;
  private int decimalPrecision;

  public CurrencyFormatter() {
    decimalSeparator = ".";
    groupSeparator = ",";
    groups = 3;
    decimalPrecision = 2;
  }

  public CurrencyFormatter(int decimalPrecision) {
    this();
    this.decimalPrecision = decimalPrecision;
  }

  public CurrencyFormatter(String decimalSeparator, String groupSeparator) {
    this();
    this.decimalSeparator = decimalSeparator;
    this.groupSeparator = groupSeparator;
  }

  public CurrencyFormatter(String decimalSeparator, String groupSeparator, int groups) {
    this();
    this.decimalSeparator = decimalSeparator;
    this.groupSeparator = groupSeparator;
    this.groups = groups;
  }

  public CurrencyFormatter(String decimalSeparator, String groupSeparator, int groups, int decimalPrecision) {
    this.decimalSeparator = decimalSeparator;
    this.groupSeparator = groupSeparator;
    this.groups = groups;
    this.decimalPrecision = decimalPrecision;
  }

  public String getDecimalSeparator() {
    return decimalSeparator;
  }

  public void setDecimalSeparator(String decimalSeparator) {
    this.decimalSeparator = decimalSeparator;
  }

  public String getGroupSeparator() {
    return groupSeparator;
  }

  public void setGroupSeparator(String groupSeparator) {
    this.groupSeparator = groupSeparator;
  }

  public int getGroups() {
    return groups;
  }

  public void setGroups(int groups) {
    this.groups = groups;
  }

  private String getDefaultDecimalParts() {
    return decimalSeparator + appendZero("", decimalPrecision);
  }

  private String appendZero(String str, int toLength) {
    for (int i = str.length(); i < toLength; i++) {
      str += "0";
    }
    return str;
  }

  private String getDecimalParts(String decimalPart) {
    if (decimalPart.startsWith(decimalSeparator)) {
      decimalPart = decimalPart.substring(1);
    }
    decimalPart = appendZero(decimalPart, decimalPrecision);
    //check if it is larger otherwise
    if (decimalPart.length() > decimalPrecision) {
      String strVal = decimalPart.substring(0, decimalPrecision);
      int val = Integer.parseInt(strVal);
      if (decimalPart.charAt(decimalPrecision) > '0') {
        val++;
      }
      decimalPart = val + "";
    }
    return decimalSeparator + decimalPart;
  }

  private Duplex<String, Boolean> getDecimalValue(String decimalPart) {
    if (decimalPart.startsWith(decimalSeparator)) {
      decimalPart = decimalPart.substring(1);
    }
    int carry = 0;
    decimalPart = appendZero(decimalPart, decimalPrecision);
    //check if it is larger otherwise
    if (decimalPart.length() > decimalPrecision) {
      String strVal = decimalPart.substring(0, decimalPrecision);
      char[] arrValues = strVal.toCharArray();
      if (decimalPart.charAt(decimalPrecision) > '0') {
        carry = 1;
        for (int i = arrValues.length - 1; i >= 0; i--) {
          if (carry > 0) {
            int ch = Character.digit(arrValues[i], 10);
            ch += carry;
            arrValues[i] = Character.forDigit(ch % 10, 10);
            carry = ch / 10;
          }
        }
        decimalPart = new String(arrValues);
      } else {
        decimalPart = strVal;
      }
    }
    return new Duplex<String, Boolean>(decimalSeparator + decimalPart, carry > 0);
  }

  public String format(Number currency) {
    String strValue = currency.toString();
    String decimalPartFormatted = getDefaultDecimalParts();
    if (strValue.contains(decimalSeparator)) {
      //Add the decimal part
      decimalPartFormatted = strValue.substring(strValue.indexOf(decimalSeparator));
      Duplex<String, Boolean> dup = getDecimalValue(decimalPartFormatted);
      decimalPartFormatted = dup.getFirstValue();
      strValue = strValue.substring(0, strValue.indexOf(decimalSeparator));
      if (dup.getSecondValue()) {
        Integer val = Integer.parseInt(strValue);
        val++;
        strValue = val + "";
      }
    }
    int i = strValue.length();
    String groupFormatted = "";
    for (; i >= groups; i -= groups) {
      if (!groupFormatted.isEmpty()) {
        groupFormatted = groupSeparator + groupFormatted;
      }
      groupFormatted = strValue.substring(i - groups, i) + groupFormatted;
    }
    if (i > 0) {
      if (!groupFormatted.isEmpty()) {
        groupFormatted = groupSeparator + groupFormatted;
      }
      groupFormatted = strValue.substring(0, i) + groupFormatted;
    }
    return groupFormatted + decimalPartFormatted;
  }
}
