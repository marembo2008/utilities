/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author variance
 */
public class SerializableHashMap<K, V> extends HashMap<K, V> implements SerializableMap<K, V> {

    private static final long serialVersionUID = 356789324L;

    public SerializableHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public SerializableHashMap() {
    }

    public SerializableHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SerializableHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
}
