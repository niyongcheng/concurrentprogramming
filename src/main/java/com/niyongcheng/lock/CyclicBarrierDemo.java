package com.niyongcheng.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static CyclicBarrier cb;

    public static void main(String[] args){
        cb = new CyclicBarrier(6);

        for(int i =0;i<5;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting for CyclicBarrier.");
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + " continued.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
