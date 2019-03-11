package com.tutorial.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TreadPoolEx {
    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

    }
}
