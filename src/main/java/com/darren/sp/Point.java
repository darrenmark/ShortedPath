package com.darren.sp;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 */
public class Point implements Node<Point> {
    private long x;
    private long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Point getObject() {
        return this;
    }

    public long getDistance(Node<Point> node) {
        Point other = node.getObject();
        return (long) sqrt( pow(x - other.x, 2) + pow(y - other.y, 2));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
