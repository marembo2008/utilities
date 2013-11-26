/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class Ipv4Test {

  public Ipv4Test() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testNext() {
    Ipv4 actual = new Ipv4("78.23.25.36");
    Ipv4 expected = new Ipv4(new int[]{78, 23, 25, 37});
    actual.next();
    assertEquals(expected, actual);
  }
}