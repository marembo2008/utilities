/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;

/**
 *
 * @author variance
 */
public interface Constraint<E> extends Serializable{

    boolean accept(E element);
}
