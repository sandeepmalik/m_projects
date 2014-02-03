package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Monika
 * Date  : 2/3/14
 * Time  : 12:37 AM
 */
public class Queue<T> {

    private final List<T> items = new ArrayList<T>();

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue() {
        if (items.size() == 0) {
            throw new IllegalStateException();
        } else {
            return items.remove(0);
        }
    }

}
