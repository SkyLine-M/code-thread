package com.ma.threadpool;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 5; i++) {
            RunnableTest thread = new RunnableTest();
            executorService.execute(thread);
        }
    }
}

class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }
}
