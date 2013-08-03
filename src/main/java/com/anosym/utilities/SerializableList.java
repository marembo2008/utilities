/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.anosym.utilities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kenn
 */
public interface SerializableList<T> extends List<T>, Serializable{
    static final long serialVersionUID = 273213193L;
}
