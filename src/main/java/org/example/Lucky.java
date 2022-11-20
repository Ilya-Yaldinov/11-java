package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static AtomicInteger x = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);
    private static final Object syncObject = new Object();
    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x.get() < 999999) {
                int curX = x.incrementAndGet();
                synchronized (syncObject){
                    if ((curX % 10) + (curX / 10) % 10 + (curX / 100) % 10 == (curX / 1000)
                            % 10 + (curX / 10000) % 10 + (curX / 100000) % 10) {
                        System.out.println(curX);
                        count.incrementAndGet();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}

