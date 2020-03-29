package com.niyongcheng.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args){
        //
        Depot depot = new Depot(100);
        Producer producer = new Producer(depot);
        Custumer custumer = new Custumer(depot);

        producer.produce(60);
        producer.produce(120);

        custumer.consume(90);
        custumer.consume(150);

        producer.produce(110);
    }
}

class Depot{
    private int size;
    private Lock lock;
    private int capacity;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Depot(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val){
        lock.lock();
        try{
            int left = val;
            while (left > 0){
                while (size >= capacity){
                    fullCondition.await();
                }
                int inc = (size+left)>capacity ? (capacity-size) : left;
                 size += inc;
                 left -= inc;
                 System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                                Thread.currentThread().getName(), val, left, inc, size);
                // 通知“消费者”可以消费了。
                emptyCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer(int val){
        lock.lock();
        try{
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                while (size <= 0)
                    emptyCondition.await();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size<left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
        }
            finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity:"+capacity+", actual size:"+size;
    }
}

class Producer{
    private Depot depot;

    public Producer(Depot depot){
        this.depot = depot;
    }

    public void produce(final int val){
        new Thread(()->{
            depot.produce(val);
        }).start();
    }
}

class Custumer{
    private Depot depot;

    public Custumer(Depot depot){
        this.depot = depot;
    }

    public void consume(final int val){
        new Thread(()->{
            depot.consumer(val);
        }).start();
    }
}
