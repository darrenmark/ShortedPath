package com.darren.sp;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is not thread safe
 */
public class BruteForcePathCalculator<T extends Node<T>> implements PathCalculator<T> {
    private ShortedDistance<T> shortedDistance;
    private T start;


    public List<T> shortestPathSequence(T start, List<T> nodes) {
        this.start = start;
        shortedDistance = new ShortedDistance<T>(start, nodes);
        new SequenceCreator<T>(this, nodes).start();
        return shortedDistance.getPath();
    }

    void setShortedDistance(List<T> nodes) {
        ShortedDistance<T> newShortedDistance = new ShortedDistance<T>(start, nodes);
        if(newShortedDistance.distance < shortedDistance.distance) {
            shortedDistance = newShortedDistance;
        }

    }

    static class ShortedDistance<T extends Node<T>> {
        private List<T> nodes;
        private T startNode;
        long distance;

        private ShortedDistance(T startNode, List<T> nodes) {
            this.startNode = checkNotNull(startNode);
            this.nodes = checkNotNull(nodes);
            calculateDistance(startNode);
        }

        private void calculateDistance(T startNode) {
            T currentNode = startNode;
            for(T node: nodes) {
                distance += currentNode.getDistance(node);
                currentNode = node;
            }
        }

        List<T> getPath() {
            List<T> result = new ArrayList<T>();
            result.add(startNode);
            result.addAll(nodes);
            return result;
        }
    }
}
