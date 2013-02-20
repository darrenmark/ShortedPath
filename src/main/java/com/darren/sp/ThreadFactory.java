package com.darren.sp;

/**
 */
public interface ThreadFactory {
    Thread createBackgroundThread(Runnable runnable);
}
