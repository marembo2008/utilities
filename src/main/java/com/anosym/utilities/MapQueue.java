/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This is an implementation of a queue with map properties.
 * The {@linkplain #put(java.lang.Object, java.lang.Object) } works the same as the general map implementation
 * with one distinct exception. If the key already exists in the queue, it will be updated,
 * however, if it does not exist, the it will be placed behind the queue
 * @see java.util.Queue
 * @author Administrator
 */
public interface MapQueue<K, V> extends Map<K, V>, Iterable<K> {

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * <tt>true</tt> upon success and throwing an <tt>IllegalStateException</tt>
     * if no space is currently available.
     *
     * @param e the element to add
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    boolean add(K k, V v);

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return <tt>true</tt> if the element was added to this queue, else
     *         <tt>false</tt>
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    boolean offer(K e, V v);

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    Duplex<K, V> remove();

    /**
     * Retrieves and removes the head of this queue,
     * or returns <tt>null</tt> if this queue is empty.
     *
     * @return the head of this queue, or <tt>null</tt> if this queue is empty
     */
    Duplex<K, V> poll();

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    Duplex<K, V> element();

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns <tt>null</tt> if this queue is empty.
     *
     * @return the head of this queue, or <tt>null</tt> if this queue is empty
     */
    Duplex<K, V> peek();
}
