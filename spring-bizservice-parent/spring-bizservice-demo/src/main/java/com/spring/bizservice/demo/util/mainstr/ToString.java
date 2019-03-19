package com.spring.bizservice.demo.util.mainstr;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liyongzhen
 * @create 2019-03-06 9:22
 **/
public class ToString {
    public static void main(String[] args) {
//        int i =0;
//        int b = i++;
//        int c = i++;
//
//        System.out.println(c);


        ThreadPoolExecutor  executor = new ThreadPoolExecutor(10,200,1000, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(1));

        executor.submit((Callable) ()-> 1);

        executor.submit(()-> System.out.println("2"));
        executor.submit(()-> System.out.println("3"));
        executor.submit(()-> System.out.println("4"));
        executor.submit(()-> System.out.println("5"));
        executor.submit(()-> System.out.println("6"));

    }



    public static void  CtoString(){
        try {

            Thread.sleep(2000);
             System.out.println(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
