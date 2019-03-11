package com.tutorial.concurrency;

public class ThreadGroupEx implements Runnable {
    public static void main(String args[]) {
        ThreadGroup tg1 = new ThreadGroup("Parent ThreadGroup");

        ThreadGroupEx threadGroupEx = new ThreadGroupEx();

        Thread t1 = new Thread(tg1, threadGroupEx, "threadGroupEx1");
        Thread t2 = new Thread(tg1, threadGroupEx, "threadGroupEx1");
        Thread t3 = new Thread(tg1, threadGroupEx, "threadGroupEx1");

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tg1.activeCount());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
