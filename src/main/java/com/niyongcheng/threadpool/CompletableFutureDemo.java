package com.niyongcheng.threadpool;

import sun.lwawt.macosx.CSystemTray;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CompletableFutureDemo {

    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreDate() {
        System.out.println("begin to start computer");

        try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
        System.out.println("end to start computer," + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureDemo::getMoreDate);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 100);

        future1.whenComplete((v,e)->{
            System.out.println(v);
            System.out.println(e);
        });

        Future<Integer> f = future.whenComplete((v,e)->{
           System.out.println(v);
           System.out.println(e);
        });

        System.out.println(f.get());
        System.in.read();
    }
}
