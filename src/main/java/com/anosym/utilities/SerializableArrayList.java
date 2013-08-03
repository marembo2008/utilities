/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author kenn
 */
public class SerializableArrayList<T> extends ArrayList<T> implements SerializableList<T> {

    public SerializableArrayList(Collection c) {
        super(c);
    }

    public SerializableArrayList() {
    }

    public SerializableArrayList(int initialCapacity) {
        super(initialCapacity);
    }
}
