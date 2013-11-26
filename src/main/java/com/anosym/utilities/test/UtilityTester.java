/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.test;

import com.anosym.vjax.v3.VObjectMarshaller;
import com.anosym.utilities.Application;
import com.anosym.utilities.Utility;
import com.anosym.utilities.geocode.CountryCode;
import com.anosym.vjax.util.VConditional;
import com.anosym.vjax.xml.VDocument;
import com.anosym.vjax.xml.VElement;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marembo
 */
public class UtilityTester {

  public static class A<T> {

    public A() {
      Type t = getClass().getGenericSuperclass();
      ParameterizedType pt = (ParameterizedType) t;
      Type[] tt = pt.getActualTypeArguments();
      if (tt.length > 0) {
        Class c = (Class) tt[0];
        System.out.println(c);
      }
    }
  }

  public static class B {
  }

  public static class BB {
  }

  public static class C extends A<B> {
  }

  public static class CC extends A<BB> {
  }

  public static void main(String[] args) throws Exception {
    String uah = "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko".toLowerCase();
    String regex = "windows\\s+[^\\d*\\w*]\\s+\\d+\\.*\\d*";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(uah);
    System.out.println(m.find());
  }

  private static void randomize(List<String> candidates) {
    int s = candidates.size();
    for (int i = 0, j = s - 1; i < (s - 1); i++, j++) {
      j %= s;
      String c0 = candidates.get(i);
      String c1 = candidates.get(j);
      candidates.set(i, c1);
      candidates.set(j, c0);
    }
  }

//  private static String[] generateAgents
  private static void testElexar() {
    final String[] candidatesCode = {"U", "P", "R"};
    final String[] politicalAgent = {
      "+254721905360",
      "+254123456789",
      "254721912151"};
    for (;;) {
      try {
        Random r = new Random(System.currentTimeMillis());
        int v0 = r.nextInt(500);
        int v2 = r.nextInt((500 - v0) + 100);
        int v3 = r.nextInt((500 - (v0 + v2)) + 100);
        int disputed = r.nextInt(Math.min(v0, Math.min(v2, v3)));
        int rejected = r.nextInt(Math.min(v0, Math.min(v2, v3)));
        VotingResult result = new VotingResult();
        int p = r.nextInt(politicalAgent.length);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(candidatesCode[0], v0);
        map.put(candidatesCode[1], v2);
        map.put(candidatesCode[2], v3);
        result.setResult(map);
        result.setDisputed(disputed);
        result.setRejected(rejected);
        result.setAgent(politicalAgent[p]);
        VObjectMarshaller<VotingResult> v = new VObjectMarshaller<VotingResult>(VotingResult.class);
        String votesResults = "result=" + v.doMarshall(result);
        URL url = new URL("http://41.57.96.94:8484/elexar?" + votesResults);
        InputStream inn = url.openConnection().getInputStream();
        Thread.sleep(30000);
      } catch (Exception ex) {
        Logger.getLogger(UtilityTester.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void printOutMyPass(String passExtension) {
    for (int i = 0; i < 10; i++) {
      System.out.println(Application.generateReadableHash("**vampire2008##" + passExtension));
    }
//    System.out.println(BigDecimal.valueOf(0.001).divide(BigDecimal.valueOf(100), MathContext.DECIMAL32));
//    System.out.println(BigDecimal.valueOf(0.001).divide(BigDecimal.valueOf(100), RoundingMode.UP));
  }
}