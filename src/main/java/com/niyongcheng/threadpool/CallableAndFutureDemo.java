package com.niyongcheng.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future f1 = pool.submit(()->{
           int sum = 0;
           for(int i=0;i<100;i++){
               sum += i;
           }
           return sum;
        });

        System.out.println(f1.get());

        pool.shutdown();
    }
}
