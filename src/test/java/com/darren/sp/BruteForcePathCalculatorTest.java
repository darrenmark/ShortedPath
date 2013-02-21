package com.darren.sp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 */
public class BruteForcePathCalculatorTest {


    @Test
    public void test() throws InterruptedException{
        BruteForcePathCalculator<Point> calculator = new BruteForcePathCalculator<Point>();
        //List<Point> path = calculator.shortestPathSequence(new Point(0, 0), Arrays.<Point>asList(new Point(25, 25), new Point(60, 60), new Point(70, 70), new Point(80, 80), new Point(50, 50), new Point(10, 10), new Point(5,5), new Point(2,3),new Point(45, 45)));
        List<Point> path = calculator.shortestPathSequence(new Point(0, 0), Arrays.<Point>asList(new Point(70, 70),new Point(25, 25), new Point(60, 60)));
        assertThat(path.size(),is(equalTo(4)));
        assertThat(path.get(0),is(equalTo(new Point(0, 0))));
        assertThat(path.get(1),is(equalTo(new Point(25, 25))));
        assertThat(path.get(2),is(equalTo(new Point(60, 60))));
        assertThat(path.get(3),is(equalTo(new Point(70, 70))));
    }

    private class MyThreadFactory implements ThreadFactory  {
        public Thread createBackgroundThread(Runnable runnable) {
            return new Thread(runnable);
        }
    }

}
