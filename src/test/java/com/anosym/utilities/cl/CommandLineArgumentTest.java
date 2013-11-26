/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.cl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class CommandLineArgumentTest {

  public CommandLineArgumentTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    String[] args = {"-id=23", "-fg=45ff", "-id0=34"};
    CommandLineArgument.init(args);
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testInit() {
    System.out.println("init");
    assertTrue(CommandLineArgument.hasParameterId("id"));
    assertTrue(CommandLineArgument.hasParameterId("fg"));
    assertTrue(CommandLineArgument.hasParameterId("id0"));
  }

  @Test
  public void testClParameter() {
    System.out.println("clParameter");
    String paramId = "id";
    String expResult = "23";
    String result = CommandLineArgument.clParameter(paramId);
    assertEquals(expResult, result);
    result = CommandLineArgument.clParameter("id0");
    assertEquals("34", result);
    result = CommandLineArgument.clParameter("cf");
    assertEquals(null, result);
  }
}