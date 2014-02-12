/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

/**
 * Generally, we may not be at liberty to change the toString method if our object.
 *
 * @author marembo
 * @param <T>
 */
public interface StringConverter<T> {

  String toString(T obj);
}
