/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Arrays;
import java.util.List;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Administrator
 */
public class ArrayListBindingConverter<T> extends Converter<T[], List<T>> {

    @Override
    public List<T> convertForward(T[] value) {
        return Arrays.asList(value);
    }

    @Override
    public T[] convertReverse(List<T> value) {
        return (T[]) value.toArray(new Object[0]);
    }
}
