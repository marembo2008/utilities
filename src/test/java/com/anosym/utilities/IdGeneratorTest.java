/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Calendar;
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

  @Test
  public void testGenerateTimeBasedId() {
    System.out.println("generateTimeBasedId");
    Calendar now = Calendar.getInstance();
    now.set(2013, 02, 02, 03, 01, 00);
    String id = "2013223";
    String actualId = IdGenerator.generateTimeBasedId(now, 4);
    System.out.println(actualId);
    assertTrue(actualId.startsWith(id));
  }
}