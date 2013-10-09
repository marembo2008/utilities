/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marembo
 */
public final class Permutation {

  public static String[] permutations(String str) {
    return doPermutation(str);
  }

  private static String[] doPermutation(String chars) {
    if (chars.length() == 1) {
      return new String[]{chars};
    } else if (chars.length() == 2) {
      //exchange the characters and returns
      return new String[]{chars, chars.charAt(1) + "" + chars.charAt(0)};
    }
    //else we know that the number of permutations is for each letter = length -1.
    List<String> perms = new ArrayList<String>();
    for (int i = 0; i < chars.length(); i++) {
      int n = factorial(chars.length() - 1);
      String[] result = new String[n];
      char a = chars.charAt(i);
      //remove that charater
      String toChars = chars.substring(0, i) + chars.substring(i + 1);
      String[] options = doPermutation(toChars);
      for (int l = 0; l < n; l++) {
        result[l] = a + options[l];
      }
      perms.addAll(Arrays.asList(result));
    }
    return perms.toArray(new String[0]);
  }

  private static int factorial(int l) {
    if (l == 1) {
      return l;
    }
    return l * factorial((l - 1));
  }
}
