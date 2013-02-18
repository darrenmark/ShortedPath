package com.darren.sp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

/**
 * Create sequences and pushes it into the Queue
 */
class SequenceCreator<T> {
    private BlockingQueue<List<T>> queue;
    private List<T> nodes;

    public SequenceCreator(BlockingQueue<List<T>> queue, final List<T> nodes) {
        this.queue = queue;
        this.nodes = nodes;
    }

    /**
     * Start creating sequences and pushed it to the queue
     */
    void start() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    createSequence(nodes);
                } catch (InterruptedException ex) {
                   throw new RuntimeException(ex);
                }
            }
        }).start();
    }

    private void createSequence(List<T> nodes) throws InterruptedException{
        for(T node: nodes) {
            List<T> remainingNodes = new ArrayList<T>(nodes);
            remainingNodes.remove(node);
            createSequence(new Stack<T>(), node,  remainingNodes);
        }
    }

    private void createSequence(Stack<T> stack, T node, List<T> nodes) throws InterruptedException {
        stack.push(node);
        if(nodes.size() == 0) {
            queue.put(new ArrayList<T>(stack.subList(0, stack.size())));
        }
        for(T first: nodes) {
            List<T> remainingNodes = new ArrayList<T>(nodes);
            remainingNodes.remove(first);
            createSequence(stack, first, remainingNodes);
        }
        stack.pop();
    }

}
