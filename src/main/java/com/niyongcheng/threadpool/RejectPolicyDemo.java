package com.niyongcheng.threadpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * for reject policy
 AbortPolicy         -- 当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常。
 CallerRunsPolicy    -- 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
 DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
 DiscardPolicy       -- 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
 */
public class RejectPolicyDemo {
    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE,THREADS_SIZE,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));

        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i =0;i<10;i++){
            Runnable myrun = new MyRunnable("task-" + i);
            pool.execute(myrun);
        }

        pool.shutdown();
    }
}

class MyRunnable implements Runnable {
    private String name;
    public MyRunnable(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " is running.");
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
