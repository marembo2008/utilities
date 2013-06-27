/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Collection;

/**
 *
 * @author variance
 */
public class ConstraintArrayList<T> extends SerializableArrayList<T> {

  private transient Constraint<T> constraint;

  public ConstraintArrayList(Collection<? extends T> c, Constraint<T> constraint) {
    this(constraint);
    if (c != null && !c.isEmpty() && !this.doAddAll(c)) {
      throw new IllegalArgumentException("Invalid List Elements");
    }
  }

  public ConstraintArrayList(Constraint<T> constraint) {
    super();
    this.constraint = constraint;
  }

  public ConstraintArrayList(int initialCapacity, Constraint<T> constraint) {
    super(initialCapacity);
    this.constraint = constraint;
  }

  public ConstraintArrayList() {
  }

  public void setConstraint(Constraint<T> constraint) {
    this.constraint = constraint;
  }

  public Constraint<T> getConstraint() {
    return constraint;
  }

  @Override
  public synchronized boolean add(T e) {
    if (constraint == null || constraint.accept(e)) {
      return super.add(e);
    }
    return false;
  }

  @Override
  public synchronized void add(int index, T element) {
    if (constraint == null || constraint.accept(element)) {
      super.add(index, element);
    }
  }

  private synchronized boolean doAddAll(Collection<? extends T> c) {
    return addAll(c);
  }

  @Override
  public synchronized boolean addAll(Collection<? extends T> c) {
    if (constraint != null) {
      for (T t : c) {
        if (!constraint.accept(t)) {
          return false;
        }
      }
    }
    return super.addAll(c);
  }

  @Override
  public synchronized boolean addAll(int index, Collection<? extends T> c) {
    if (constraint != null) {
      for (T t : c) {
        if (!constraint.accept(t)) {
          return false;
        }
      }
    }
    return super.addAll(index, c);
  }
}
