package com.basic.producer.consumer;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Consumer implements Runnable {
    private Queue queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        IntStream.range(1, 5)
                .forEach(i -> {
                    int consumedItem = 0;
                    if (queue instanceof BlockingQueue) {
                        try {
                            consumedItem = (Integer) ((BlockingQueue<?>) queue).take();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        consumedItem = (Integer) queue.poll();
                    }
                    System.out.println("Consumer Thread :" + Thread.currentThread().getName()
                            + ", Consumed : " + consumedItem);
                });
    }
}
