/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.util.Collection;
import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class SortedArrayList<E extends Comparable<E>> extends ConstraintArrayList<E> {

    private Comparator<E> sortComparator;

    public SortedArrayList(Constraint<E> constraint) {
        super(constraint);
    }

    public SortedArrayList(Comparator<E> sortComparator) {
        this.sortComparator = sortComparator;
    }

    public SortedArrayList(int initialCapacity, Constraint<E> constraint, Comparator<E> sortComparator) {
        super(initialCapacity, constraint);
        this.sortComparator = sortComparator;
    }

    public SortedArrayList(Constraint<E> constraint, Comparator<E> sortComparator) {
        super(constraint);
        this.sortComparator = sortComparator;
    }

    public SortedArrayList(Collection<? extends E> c, Constraint<E> constraint, Comparator<E> sortComparator) {
        super(c, constraint);
        this.sortComparator = sortComparator;
    }

    public SortedArrayList() {
    }

    @Override
    public int indexOf(Object o) {
        if (this.size() < 1100) {
            return super.indexOf(o);
        } else {
            return indexOf(o, 0, this.size());
        }
    }

    private int indexOf(Object o, int start, int end) {
        if ((end - start) < 1200) {
            for (; start < end; start++) {
                if (o.equals(this.get(start))) {
                    return start;
                }
            }
            return -1;
        }
        if (this.isEmpty() || start == end) {
            return -1;
        }
        if (o instanceof Comparable || sortComparator != null) {
            E e = (E) o;
            //then find the middle index
            int index = (start + end) / 2;
            E el = this.get(index);
            int cmp = 0;
            if (sortComparator != null) {
                cmp = sortComparator.compare(e, el);
            } else if (e instanceof Comparable) {
                Comparable<E> ce = (Comparable<E>) e;
                cmp = ce.compareTo(el);
            }
            if (cmp == 0) {
                return index;
            } else if (cmp > 0) {
                return indexOf(o, index + 1, end);
            } else {
                return indexOf(o, start, index);
            }
        } else {
            return super.indexOf(o);
        }
    }

    public int quadripleSearch(Object o, int start, int end) {
        if (this.isEmpty() || start == end) {
            return -1;
        }
        if (o instanceof Comparable || sortComparator != null) {
            E e = (E) o;
            //then find the middle index
            int sum = start + end;
            int diff = end - start;// + end;
            int middle = sum / 2;
            int lQuartile = start + (diff / 4);
            int uQuartile = start + ((diff * 3) / 4);
            E el = this.get(middle);
            E e2 = this.get(lQuartile);
            E e3 = this.get(uQuartile);
            int cmp = 0;
            if (sortComparator != null) {
                cmp = sortComparator.compare(e, el);
                if (cmp == 0) {
                    return middle;
                } else if (cmp < 0) {
                    cmp = sortComparator.compare(e, e2);
                    if (cmp == 0) {
                        return lQuartile;
                    } else if (cmp > 0) {
                        return quadripleSearch(o, lQuartile, middle);
                    } else {
                        return quadripleSearch(o, 0, lQuartile);
                    }
                } else {
                    cmp = sortComparator.compare(e, e3);
                    if (cmp == 0) {
                        return uQuartile;
                    } else if (cmp > 0) {
                        return quadripleSearch(o, uQuartile, end);
                    } else {
                        return quadripleSearch(o, middle, uQuartile);
                    }
                }
            } else if (e instanceof Comparable) {
                Comparable<E> ce = (Comparable<E>) e;
                cmp = ce.compareTo(el);
                if (cmp == 0) {
                    return middle;
                } else if (cmp < 0) {
                    cmp = ce.compareTo(e2);
                    if (cmp == 0) {
                        return lQuartile;
                    } else if (cmp > 0) {
                        return quadripleSearch(o, lQuartile + 1, middle);
                    } else {
                        return quadripleSearch(o, 0, lQuartile);
                    }
                } else {
                    cmp = ce.compareTo(e3);
                    if (cmp == 0) {
                        return uQuartile;
                    } else if (cmp > 0) {
                        return quadripleSearch(o, uQuartile, end);
                    } else {
                        return quadripleSearch(o, middle, uQuartile);
                    }
                }
            }
        }
        return super.indexOf(o);
    }

    private int possibleIndex(E e) {
        return possibleIndex(e, 0, this.size());
    }

    private int possibleIndex(E e, int start, int end) {
        if (this.isEmpty()) {
            return 0;
        }
        if (start == end) {
            return start;
        }
        //then find the middle index
        int index = (start + end) / 2;
        E el = this.get(index);
        int cmp = 0;
        if (sortComparator != null) {
            cmp = sortComparator.compare(e, el);
        } else if (e instanceof Comparable) {
            Comparable<E> ce = (Comparable<E>) e;
            cmp = ce.compareTo(el);
        }
        if (cmp == 0) {
            return index;
        } else if (cmp > 0) {
            return possibleIndex(e, index + 1, end);
        } else {
            return possibleIndex(e, start, index);
        }
    }

    @Override
    public boolean add(E e) {
        int index = possibleIndex(e);
        this.add(index, e);
        return true;
    }

    @Override
    public final void add(int index, E element) {
        //does nothing
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //does nothing
        return super.addAll(index, c);
    }

    @Override
    public final E set(int index, E element) {
        //does nothing
        return super.set(index, element);
    }
}
