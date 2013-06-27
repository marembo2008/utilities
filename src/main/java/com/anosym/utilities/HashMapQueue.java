/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class HashMapQueue<K, V> implements MapQueue<K, V> {

    private Queue<Duplex<K, V>> map;

    public HashMapQueue() {
        map = new LinkedList<Duplex<K, V>>();
    }

    public HashMapQueue(MapQueue<K, V> map) {
        this();
        for (Entry<K, V> e : map.entrySet()) {
            Duplex<K, V> d = new Duplex<K, V>(e.getKey(), e.getValue());
            this.map.add(d);
        }
    }

    public boolean add(K k, V v) {
        Duplex<K, V> d = new Duplex<K, V>(k, v);
        return map.add(d);
    }

    public boolean offer(K k, V v) {
        Duplex<K, V> d = new Duplex<K, V>(k, v);
        return map.offer(d);
    }

    public Duplex<K, V> remove() {
        return map.remove();
    }

    public Duplex<K, V> poll() {
        return map.poll();
    }

    public Duplex<K, V> element() {
        return map.element();
    }

    public Duplex<K, V> peek() {
        return map.peek();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        for (Duplex<K, V> d : map) {
            if (d.getFirstValue().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (Duplex<K, V> d : map) {
            if (d.getSecondValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        for (Duplex<K, V> d : map) {
            if (d.getFirstValue().equals(key)) {
                return d.getSecondValue();
            }
        }
        return null;
    }

    private Duplex<K, V> getPair(K key) {
        for (Duplex<K, V> d : map) {
            if (d.getFirstValue().equals(key)) {
                return d;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        Duplex<K, V> d = getPair(key);
        if (d != null) {
            V v = d.getSecondValue();
            d.setSecondValue(value);
            return v;
        } else {
            offer(key, value);
            return null;
        }
    }

    public V remove(Object key) {
        Duplex<K, V> d = getPair((K) key);
        if (d != null) {
            V v = d.getSecondValue();
            ((LinkedList<Duplex<K, V>>) map).remove(d);
            return v;
        } else {
            return null;
        }
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (K k : m.keySet()) {
            put(k, m.get(k));
        }
    }

    public void clear() {
        map.clear();
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (Duplex<K, V> d : map) {
            set.add(d.getFirstValue());
        }
        return set;
    }

    public Collection<V> values() {
        Set<V> set = new HashSet<V>();
        for (Duplex<K, V> d : map) {
            set.add(d.getSecondValue());
        }
        return set;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<Entry<K, V>>();
        for (Duplex<K, V> d : map) {
            entries.add(new QEntry<K, V>(d.getFirstValue(), d.getSecondValue()));
        }
        return entries;
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private int index = 0;

            public boolean hasNext() {
                return index < map.size();
            }

            public K next() {
                Duplex<K, V> d = ((LinkedList<Duplex<K, V>>) map).get(index);
                if (d == null) {
                    throw new NoSuchElementException("No more elements");
                }
                return d.getFirstValue();
            }

            public void remove() {
                Duplex<K, V> d = ((LinkedList<Duplex<K, V>>) map).get(index);
                if (d != null) {
                    map.remove(d);
                }
            }
        };
    }

    private class QEntry<K, V> implements Entry<K, V> {

        private K key;
        private V value;

        public QEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V _v = this.value;
            this.value = value;
            return _v;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (Duplex<K, V> d : map) {
            str += ("[" + d + "]");
        }
        str = str.replaceAll("\\]\\[", "],[");
        str = "{" + str + "}";
        return str;
    }
}
