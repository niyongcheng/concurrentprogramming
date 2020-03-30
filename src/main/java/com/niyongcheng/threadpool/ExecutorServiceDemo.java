package com.niyongcheng.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main(String[] args){
        //Executor

        //ExecutorService

        ExecutorService pool = Executors.newFixedThreadPool(4);

        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });
        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });
        Thread thread3 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });
        Thread thread4 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });
        Thread thread5 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });

        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        pool.execute(thread5);

        pool.shutdown();
    }
}
