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

    public List<T> shortestPathSequence(T start, List<T> nodes) {
        BlockingQueue<List<T>> queue = new ArrayBlockingQueue<List<T>>(1);
        new SequenceCreator<T>(queue, nodes).start();
        ShortedDistance<T> shortedDistance = new ShortedDistance<T>(start, nodes);
        int count = 0, totalCombinations = getFactorial(nodes.size());
        while(count++ < totalCombinations) {
            try {
                ShortedDistance<T> newShortedDistance = new ShortedDistance<T>(start, queue.poll(1,TimeUnit.SECONDS));
                if(newShortedDistance.distance < shortedDistance.distance) {
                    shortedDistance = newShortedDistance;
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        return shortedDistance.getPath();
    }

    private static class ShortedDistance<T extends Node<T>> {
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

    private int getFactorial(int number) {
        int result = 1;
        for(int i=1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
