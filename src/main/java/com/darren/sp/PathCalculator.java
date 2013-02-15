package com.darren.sp;

import java.util.List;

/**
 */
public interface PathCalculator<T extends Node<T>> {

    public List<T> shortestPathSequence(T start, List<T> nodes);
}
