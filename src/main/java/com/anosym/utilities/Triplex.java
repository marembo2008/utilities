/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Triplex<F, S, T> extends Duplex<F, S> implements Serializable {

    private T thirdValue;

    public Triplex(F firstValue, S secondValue, T thirdValue) {
        super(firstValue, secondValue);
        this.thirdValue = thirdValue;
    }

    public Triplex(T thirdValue) {
        this.thirdValue = thirdValue;
    }

    public Triplex() {
    }

    public T getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(T thirdValue) {
        this.thirdValue = thirdValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Triplex<F, S, T> other = (Triplex<F, S, T>) obj;
        if (!super.equals(obj)) {
            return false;
        }
        if (this.thirdValue != other.thirdValue && (this.thirdValue == null || !this.thirdValue.equals(other.thirdValue))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * super.hashCode();
        hash = 71 * hash + (this.thirdValue != null ? this.thirdValue.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "{" + getFirstValue() + "; " + getSecondValue() + ";" + getThirdValue() + '}';
    }
}
