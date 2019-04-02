package com.spring.bizservice.demo.util.mainstr;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liyongzhen
 * @create 2019-04-02 9:03
 **/
public class Coudt {
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Countdt2 c2 = new Countdt2(latch);
        Thread t1 = new Thread(c2);
        t1.start();
        Thread.sleep(1000);
        Countdt3 c3 = new Countdt3(latch);
         Thread t2 = new Thread(c3);
        t2.start();
    }
}
