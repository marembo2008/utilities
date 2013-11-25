/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author marembo
 */
public class ClassUtils {

    /**
     * Find the field, declared, or in superclass annotated with the specified
     * annotation. If no field is found returns null.
     *
     * @param <T>
     * @param clazz
     * @param annot
     * @return
     */
    public static <T> String getField(Class<T> clazz, Class<? extends Annotation> annot) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(annot)) {
                    return f.getName();
                }
            }
            /**
             * if we reach here, we did not get the annotation in the current
             * class, try superclass.
             */
            if (clazz.getSuperclass().isAssignableFrom(Object.class)) {
                return null;
            }
            return getField(clazz.getSuperclass(), annot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find the field, declared, or in superclass annotated with the specified
     * annotation. If no field is found returns null.
     *
     * @param <T>
     * @param o
     * @param annot
     * @return
     */
    public static <T, V> V getFieldValue(T o, Class<? extends Annotation> annot) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(annot)) {
                    f.setAccessible(true);
                    return (V) f.get(o);
                }
            }
            /**
             * if we reach here, we did not get the annotation in the current
             * class, try superclass.
             */
            if (o.getClass().getSuperclass().isAssignableFrom(Object.class)) {
                return null;
            }
            return (V) getField(o.getClass().getSuperclass(), annot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
