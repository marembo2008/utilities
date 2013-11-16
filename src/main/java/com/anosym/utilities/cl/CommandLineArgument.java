/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.cl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
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
    List<String> argList = new ArrayList<String>(Arrays.asList(args));
    clArguments = new HashMap<String, String>();
    Pattern idRegex = Pattern.compile("\\-{1}\\w+\\={1}");
    Pattern valueRegex = Pattern.compile("={1}.+");
    Iterator<String> argIter = argList.iterator();
    for (; argIter.hasNext();) {
      String arg = argIter.next().trim();
      Matcher idm = idRegex.matcher(arg);
      Matcher valuem = valueRegex.matcher(arg);
      if (idm.find()) {
        String id = arg.substring(idm.start() + 1, idm.end() - 1);
        if (valuem.find()) {
          String value = arg.substring(valuem.start() + 1);
          clArguments.put(id, value);
        }
        //regardless we remove it
        argIter.remove();
      }
    }
    //then we have --key value option
    argIter = argList.iterator();
    idRegex = Pattern.compile("\\-{2}\\w+");
    valueRegex = Pattern.compile("[\\w*\\d*]+");
    String id = null;
    for (; argIter.hasNext();) {
      String param = argIter.next().trim();
      Matcher idm = idRegex.matcher(param);
      Matcher valuem = valueRegex.matcher(param);
      if (idm.find()) {
        id = param.substring(idm.start() + 2);
      } else if (valuem.find() && id != null) {
        clArguments.put(id, param);
        id = null;
      } else {
        id = null;
      }
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
