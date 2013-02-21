package com.darren.sp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Create sequences and pushes it into the Queue
 */
class SequenceCreator<T extends Node<T>> {
    private BruteForcePathCalculator<T> bruteForcePathCalculator;
    private List<T> nodes;

    public SequenceCreator(BruteForcePathCalculator<T> bruteForcePathCalculator, final List<T> nodes) {
        this.bruteForcePathCalculator = bruteForcePathCalculator;
        this.nodes = checkNotNull(nodes);
    }

    /**
     * Start creating sequences and pushed it to the queue
     */
    void start() {
        createSequence(nodes);
    }

    private void createSequence(List<T> nodes){
        for (T node : nodes) {
            List<T> remainingNodes = new ArrayList<T>(nodes);
            remainingNodes.remove(node);
            createSequence(new Stack<T>(), node, remainingNodes);
        }
    }

    private void createSequence(Stack<T> stack, T node, List<T> nodes) {
        stack.push(node);
        if (nodes.size() == 0) {
            bruteForcePathCalculator.setShortedDistance(new ArrayList<T>(stack.subList(0, stack.size())));
        }
        for (T first : nodes) {
            List<T> remainingNodes = new ArrayList<T>(nodes);
            remainingNodes.remove(first);
            createSequence(stack, first, remainingNodes);
        }
        stack.pop();
    }

}
