package com.darren.sp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 */
public class BruteForcePathCalculatorTest {

    @Test
    public void test() throws InterruptedException{
        BruteForcePathCalculator<AlphaNode> calculator = new BruteForcePathCalculator<AlphaNode>();
        List<AlphaNode> path = calculator.shortestPathSequence(new AlphaNode("Z"), Arrays.<AlphaNode>asList(new AlphaNode("A"), new AlphaNode("B"), new AlphaNode("C")));
        System.out.println(path);
    }

    @Test
    public void test_1() throws InterruptedException{
        BruteForcePathCalculator<Point> calculator = new BruteForcePathCalculator<Point>();
        List<Point> path = calculator.shortestPathSequence(new Point(0, 0), Arrays.<Point>asList(new Point(25, 25), new Point(60, 60), new Point(70, 70), new Point(80, 80), new Point(50, 50), new Point(10, 10), new Point(5,5), new Point(2,3),new Point(45, 45)));
        System.out.println(path);
    }

    public static class AlphaNode implements Node<AlphaNode> {
        private String id;

        public AlphaNode(String id) {
            this.id = id;
        }

        public AlphaNode getObject() {
            return this;
        }

        public long getDistance(Node<AlphaNode> node) {
            return 0;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
