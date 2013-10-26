/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import com.anosym.utilities.IdGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class CountryCodeTest {

  public CountryCodeTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * If this fails, then we are definite someone has changed the serial version uid generator, and
   * thats a bad omen!
   */
  @Test
  public void testConstantSerialVersionUID() {
    Long expected = 7631300728984336584L;
    Long actual = IdGenerator.serialVersionUID(CountryCode.class);
    assertEquals(expected, actual);
  }
}