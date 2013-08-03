/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 * Used to select elements of lists
 * based on user defined criterion
 * @author marembo
 */
public interface Selector<T> {

    /**
     * Returns true if the specified element should be selected
     * @param instance
     * @return
     */
    boolean select(T instance);
}
