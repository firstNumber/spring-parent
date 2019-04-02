package com.spring.bizservice.demo.util.mainstr;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liyongzhen
 * @create 2019-04-02 9:08
 **/
public class Countdt3 implements Runnable {
    CountDownLatch latch ;
    @Override
    public void run() {
        latch.countDown();
        System.out.println("啦啦");
    }

    public Countdt3(CountDownLatch latch) {
        this.latch = latch;
    }
}
