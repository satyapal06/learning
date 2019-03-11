package com.tutorial.queuex.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerService {
    public static void main(String[] args) {
        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        //starting producer to produce messages in queue
        Thread t1 = new Thread(producer);
        t1.setName("Producer One");
        t1.start();

        Thread t2 = new Thread(producer);
        t2.setName("Producer Two");
        t2.start();

        //starting consumer to consume messages from queue
        new Thread(consumer).start();

        System.out.println("Producer and Consumer has been started");
    }
}
