package com.basic.producer.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocksConditionMain {
    public static void main(String[] args) {

        CustomBlockingQueue queue = new CustomBlockingQueue(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // producer adding numbers to queue.
        executorService.execute(new Producer(queue));
        executorService.execute(new Producer(queue));

        // consumer retrieving numbers from queue.
        executorService.execute(new Consumer(queue));
        executorService.execute(new Consumer(queue));

        System.out.println("Thread :" + Thread.currentThread().getName() + " is done");
        executorService.shutdown();
    }
}
