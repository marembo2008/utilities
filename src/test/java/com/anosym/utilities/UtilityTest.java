/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import com.anosym.utilities.currency.CurrencyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class UtilityTest {

  public UtilityTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testCapitalizeSentence() {
    String sentence = "THis should be capitalized per WORD";
    String expected = "This Should Be Capitalized Per Word";
    String actual = Utility.capitalize(sentence);
    assertEquals(expected, actual);
  }

  @Test
  public void testCapitalizeSingleLetterWord() {
    String sentence = "a";
    String expected = "A";
    String actual = Utility.capitalize(sentence);
    assertEquals(expected, actual);
  }

  @Test
  public void testFindCurrencyCodeFromCountryIsoCodeAndCurrencyIsoCode() {
    System.out.println("testFindCurrencyCodeFromCountryIsoCodeAndCurrencyIsoCode");
    CurrencyCode usd = Utility.findCurrencyCodeFromCountryIsoCodeAndCurrencySymbol("US","USD");
    assertEquals(usd.getCountryCode(), Utility.findCountryCodeFromCountryIsoCode("US"));
  }
}