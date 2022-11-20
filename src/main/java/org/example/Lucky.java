package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
class CommonResource{
    int x = 0;
}
public class Lucky {
    static int count = 0;

    static class LuckyThread extends Thread {
        CommonResource res = new CommonResource();
        @Override
        public void run() {
            synchronized (res){
                while (res.x < 999999) {
                    res.x++;
                    if ((res.x % 10) + (res.x / 10) % 10 + (res.x / 100) % 10 == (res.x / 1000)
                            % 10 + (res.x / 10000) % 10 + (res.x / 100000) % 10) {
                        System.out.println(res.x);
                        count++;
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
