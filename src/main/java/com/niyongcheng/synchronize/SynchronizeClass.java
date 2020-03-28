package com.niyongcheng.synchronize;

/**
 *
 */
public class SynchronizeClass {

    private int count = 0;
    /**
     *
     */
    public synchronized void Add(){
        for(int i = 0;i<1000;i++){
            System.out.println("this is sync on method");
        }
    }

    /**
     *
     */
    public void Increase(){
        synchronized (this){
            System.out.println("this is one sync block demo");
        }
    }
}
