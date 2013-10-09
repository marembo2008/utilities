/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class PermutationTest {

  public PermutationTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testPermutations() {
    String[] expected = {"xyz", "xzy", "zyx", "zxy", "yzx", "yxz"};
    Arrays.sort(expected);
    String[] actual = Permutation.permutations("xyz");
    Arrays.sort(actual);
    assertArrayEquals(expected, actual);
  }
}