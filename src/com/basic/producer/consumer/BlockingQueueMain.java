package com.basic.producer.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueMain {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        // producer adding numbers to queue.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Producer(queue));
        executorService.execute(new Producer(queue));

        // consumer fetching numbers from queue.
        executorService.execute(new Consumer(queue));
        executorService.execute(new Consumer(queue));

        System.out.println("Thread :" + Thread.currentThread().getName() + " is done");
        executorService.shutdown();
    }
}
