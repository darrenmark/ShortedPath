package com.darren.sp;

/**
 */
public interface Node<T> {
    T getObject();

    long getDistance(Node<T> node);
}
