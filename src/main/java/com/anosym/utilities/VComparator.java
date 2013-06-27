/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 *
 * @author Administrator
 */
public interface VComparator<T, V> {

    /**
     * Logical comparison for which a value less than zero if t is less than v, value equal to zero
     * if t is equal to v and value greater than zero if t is greater than v
     * @param t
     * @param v
     * @return
     */
    int compare(T t, V v);
}
