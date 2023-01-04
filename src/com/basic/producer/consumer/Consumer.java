package com.basic.producer.consumer;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Consumer implements Runnable {
    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        IntStream.range(1, 5)
                .forEach(i -> {
                    Integer consumedItem = 0;
                    if (queue instanceof BlockingQueue) {
                        try {
                            consumedItem = ((BlockingQueue<Integer>) queue).take();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        consumedItem = queue.poll();
                    }
                    System.out.println("Consumer Thread :" + Thread.currentThread().getName()
                            + ", Consumed : " + consumedItem);
                });
    }
}
