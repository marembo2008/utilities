/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author marembo
 */
public class StringUtilTest {

  public StringUtilTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void test() {
    String str = "This string has {0} for {1}";
    String expected = "This string has parameters for replacement";
    String actual = StringUtil.withParamValue(str, "parameters", "replacement");
    Assert.assertEquals(expected, actual);
  }
}