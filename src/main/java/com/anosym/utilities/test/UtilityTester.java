/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.test;

import com.anosym.vjax.v3.VObjectMarshaller;
import com.anosym.utilities.Application;
import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.CurrencyCode;
import com.anosym.utilities.currency.CurrencyCodes;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        CurrencyCodes currencyCodes = Utility.getCurrencyCodes();
        StringBuilder sb = new StringBuilder();
        sb.append("public enum Currency {\n\n");
        StringBuilder en = new StringBuilder();
        Set<String> codes = new HashSet<String>();
        List<CurrencyCode> l = currencyCodes.getCurrencyCodes();
        Collections.sort(l, new Comparator<CurrencyCode>() {

            @Override
            public int compare(CurrencyCode o1, CurrencyCode o2) {
                return o1.getCurrencySymbol().compareToIgnoreCase(o2.getCurrencySymbol());
            }
        });
        for (CurrencyCode cc : l) {
            if (codes.add(cc.getCurrencyIsoCode())) {
                if (en.length() > 0) {
                    en.append(",\n");
                }
                en.append("    ")
                        .append(cc.getCurrencySymbol().toUpperCase()).append("(\"")
                        .append(cc.getCurrencyName()).append("\", ")
                        .append("\"").append(cc.getCurrencyIsoCode()).append("\") ");
            }
        }
        en.append(";\n\n");
        //
        sb.append(en);
        sb.append("    private final String name;\n");
        sb.append("    private final String isoCode;\n\n");
        sb.append("    private Currency(final String name, final String isoCode) {\n")
                .append("        this.name = name;\n")
                .append("        this.isoCode = isoCode;\n")
                .append("    }\n\n");
        sb.append("    public String getName() {\n        return this.name;\n    }\n\n");
        sb.append("    public String getIsoCode() {\n        return this.isoCode;\n    }\n\n");
        sb.append("}\n");
        System.out.println(sb);
    }

    private static String generateVerificationCode() {
        try {
            char set[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabscdefghijklmnopqrstuvwxyz0123456789".toCharArray();
            int r = new Random(System.currentTimeMillis()).nextInt(50) + 100;
            String hash = "";
            for (int i = 0; i < 50; i++) {
                int p = new Random(System.currentTimeMillis() + i).nextInt(62);
                hash += set[p];
            }
            return hash;
        } catch (Exception ex) {
            Logger.getLogger(UtilityTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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
