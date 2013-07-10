/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.verification;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author kenn
 */
public class VerificationCodeGenerator {

    private static double FOUR_DIGIT_MAX = Math.pow(10, 4);
    private static String alphabet = "POIUYTREWQASDFGHJKLMNBVCXZqwertyuiopasdfghjklzxcvbnm";
    private static String nums = "7418529630";
    private static char[] chars = (alphabet.concat(nums)).toCharArray();
    private static int length = 50;
    
    private static SecureRandom random = new SecureRandom();

    public static long getFourDigitCode() {        
        double val = random.nextDouble() * FOUR_DIGIT_MAX;
        return Math.round(val);
    }

    public static String getRandomAlphaNumString() {
        return getRandomAlphaNumString(chars, length);
    }

    public static String getRandomAlphaNumString(char[] allowed) {
        return getRandomAlphaNumString(allowed, length);
    }

    public static String getRandomAlphaNumString(int len) {
        return getRandomAlphaNumString(chars, len);
    }

    public static String getRandomAlphaNumString(char[] allowed, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = allowed[random.nextInt(allowed.length)];
            sb.append(c);
        }        
        return sb.toString();
    }

    public static void main(String[] args) {
        for(int i =0; i< 15; i++){
            System.out.println(getRandomAlphaNumString(50));
        }
    }
}
