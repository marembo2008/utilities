/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.cl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * If an argument begins with -{id} then it must be followed by an equal sign, and hence must have a
 * value if an argument begins with --{id} then the following argument, if not in the format of
 * --{id}, is considered the value of this id, otherwise the id is considered to have a value of
 * false. All other data is considered as values.
 *
 * @author marembo
 */
public final class CommandLineArgument {

  private static Map<String, String> clArguments;

  private static String generateKey() {
    return System.nanoTime() + "";
  }

  public static void init(String... args) {
    if (args == null) {
      return;
    }
    clArguments = new HashMap<String, String>();
    Pattern equalId = Pattern.compile("^(\\-{1}\\w+\\=?\\w*)");
    Pattern valueId = Pattern.compile("\\-{2}");
    boolean previousValueId = false;
    String valueIdParam = null;
    for (String id : args) {
      //check equal id
      if (equalId.matcher(id).find()) {
        if (id.contains("=")) {
          String[] idvalues = id.split("=");
          clArguments.put(idvalues[0], idvalues[1]);
        } else {
          clArguments.put(id, id);
        }
      } else if (valueId.matcher(id).find()) {
        valueIdParam = id;
        previousValueId = true;
        continue;
      } else if (previousValueId) {
        clArguments.put(valueIdParam, id);
      } else {
        clArguments.put(generateKey(), id);
      }
      previousValueId = false;
    }
  }

  public static String clParameter(String paramId) {
    return clArguments.get(paramId);
  }

  public static boolean hasParameterValue(String paramValue) {
    return clArguments.containsValue(paramValue);
  }

  public static boolean hasParameterId(String paramId) {
    return clArguments.containsKey(paramId);
  }
}
