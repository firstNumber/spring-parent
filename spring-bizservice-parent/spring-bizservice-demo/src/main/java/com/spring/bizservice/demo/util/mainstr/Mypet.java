package com.spring.bizservice.demo.util.mainstr;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

/**
 * @author liyongzhen
 * @create 2019-03-05 16:19
 **/
public class Mypet {
    public  static void greet(String word){
        word ="hey";
        System.out.println(word+":");
    }

    public static void main(String[] args) {
        String word ="Hello";
        greet(word);
        System.out.println(word);
    }
}
