package com.spring.bizservice.demo.util.mainstr;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liyongzhen
 * @create 2019-04-02 9:08
 **/
public class Countdt2 implements Runnable {
    CountDownLatch latch ;
    @Override
    public void run() {
        try {
            latch.await(300000, TimeUnit.MILLISECONDS);
            System.out.println("哈哈");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Countdt2(CountDownLatch latch) {
        this.latch = latch;
    }
}
