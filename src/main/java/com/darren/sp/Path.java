package com.darren.sp;

import java.util.List;

/**
 */
public class Path<T extends Node<T>> {

    public List<T> getShortestPath(T start, List<T> nodes) {
        return getPathCalculator(nodes.size()).shortestPathSequence(start, nodes);
    }

    public PathCalculator<T> getPathCalculator(int numberOfNodes) {
        if(numberOfNodes < 9) {
            return new BruteForcePathCalculator<T>();
        }
        return new HeldKarpPathCalculator<T>();

    }
}
