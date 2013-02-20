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

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
