package com.niyongcheng.thread;

import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //use the class which implement the Runnable interface
        Thread thread = new Thread(new Eagle());
        Thread thread2 = new Thread(new Rabbit());

        //use the class which inheritance Thread class
        Thread myThread = new MyThread();

        thread.start();
        thread2.start();
        myThread.start();

        //Future is jdk 1.5 feature
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Callable<String>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            public String call() throws Exception {
                return "this is one Future Task";
            }
        });

        System.out.println(future.get());

        //ComplateableFuture is jdk 1.8 feature
    }
}

class Eagle implements Runnable{

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        System.out.println("I am eagle, can fly 80 miles per hour.");
    }
}

class Rabbit implements Runnable{

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        System.out.println("I am rabbit, I can run 30 miles per hour");
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("this is myThread, come on.");
    }
}