/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
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
public class ClassUtilsTest {

    public ClassUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public static @interface FieldAnnotation {

    }

    public static class MyClass {

        @FieldAnnotation
        private int fieldName;

        public int getFieldName() {
            return fieldName;
        }

        public void setFieldName(int fieldName) {
            this.fieldName = fieldName;
        }

    }

    public static class MySubclass extends MyClass {

        private String fieldName1;

        public String getFieldName1() {
            return fieldName1;
        }

        public void setFieldName1(String fieldName1) {
            this.fieldName1 = fieldName1;
        }

    }

    /**
     * Test of getField method, of class ClassUtils.
     */
    @Test
    public void testGetField() {
        System.out.println("getField");
        String expResult = "fieldName";
        String result = ClassUtils.getField(MyClass.class, FieldAnnotation.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFieldForSubclass() {
        System.out.println("getField");
        String expResult = "fieldName";
        String result = ClassUtils.getField(MySubclass.class, FieldAnnotation.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFieldNoannotation() {
        System.out.println("getField");
        String result = ClassUtils.getField(ClassUtils.class, FieldAnnotation.class);
        assertNull(result);
    }

}
