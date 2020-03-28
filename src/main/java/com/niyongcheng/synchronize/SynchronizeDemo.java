package com.niyongcheng.synchronize;

public class SynchronizeDemo {
    public static void main(String[] args) throws InterruptedException {

        /*IncreaseDemo runnable = new IncreaseDemo();
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println(runnable.count);*/

        /**
         * the run method had sync
         */
        IncreaseWithSynchronize runnable = new IncreaseWithSynchronize();

        Thread thread = new Thread(runnable,"t1");
        Thread thread1 = new Thread(runnable,"t2");

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println(thread.getState());
        System.out.println(thread1.getState());

        System.out.println("current count is :" + runnable.count);
    }

    static class IncreaseDemo implements Runnable{
        public int count = 0;
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
            for(int i =0;i<100000;i++){
                count++;
            }
        }
    }

    /**
     *
     */
    static class IncreaseWithSynchronize implements Runnable {

        public int count = 0;

        public synchronized void add() {
            System.out.println("this is one method method");
        }

        public void run() {
            /**
             * sync on code block
             */
            synchronized (this) {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        }
    }
}
