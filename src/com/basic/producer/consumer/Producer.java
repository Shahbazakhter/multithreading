package com.basic.producer.consumer;

import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

public class Producer implements Runnable {
    private Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.range(1, 5)
                .forEach(i -> {
                    int number = new Random().nextInt(100);
                    queue.add(number);
                    System.out.println("Producer Thread :" + Thread.currentThread().getName() + ", Produced : " + number);
                    delay(10);
                });
    }

    private void delay(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
