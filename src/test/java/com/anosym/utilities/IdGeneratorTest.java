/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class IdGeneratorTest {

  public IdGeneratorTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * If this fails, then we are definite someone has changed the serial version uid generator, or
   * refactored the class from its current package, and thats a bad omen!
   */
  @Test
  public void testSerialVersionUIDGenerator() {
    Long expectedUID = -6169454019531237442L;
    Long actualUID = IdGenerator.serialVersionUID(IdGenerator.class);
    assertEquals(expectedUID, actualUID);
  }
}