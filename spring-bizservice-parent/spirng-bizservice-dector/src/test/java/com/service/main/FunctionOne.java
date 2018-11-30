package com.service.main;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.util.SocketUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongzhen
 * @create 2018-08-22 9:01
 **/
public class FunctionOne {
    public static void main(String[] args) {
        System.out.println("杨幂");

        List list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);


        list.stream().forEach(num->{
            System.out.println(num);
        });
    }
}
