package com.niyongcheng.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    static CountDownLatch doneSignal;

    public static void main(String[] args) throws InterruptedException {
        doneSignal = new CountDownLatch(6);

        //
        for(int i =0;i<5;i++){
            new InnerThread().start();
        }

        System.out.println("main await begin.");
        doneSignal.await();

        System.out.println("main await finished.");
    }

    static class InnerThread extends Thread{
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
                // 将CountDownLatch的数值减1
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
