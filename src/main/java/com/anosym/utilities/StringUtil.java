/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 *
 * @author marembo
 */
public final class StringUtil {

  public static String withParamValue(String str, String... params) {
    for (int i = 0; i < params.length; i++) {
      str = str.replace("{" + i + "}", params[i]);
    }
    return str;
  }
}
