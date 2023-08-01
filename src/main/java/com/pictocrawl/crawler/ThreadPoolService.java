package com.pictocrawl.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolService extends ThreadPoolExecutor {
    private final AtomicLong lastExecutionTime;

    public ThreadPoolService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.lastExecutionTime = new AtomicLong(System.nanoTime()); // Get elapsed time from JVM
    }

    public long getLastExecutionTime() {
        return lastExecutionTime.get();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        long currentSysNano = System.nanoTime();
        lastExecutionTime.set(currentSysNano);
    }

}