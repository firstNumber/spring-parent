package com.spring.bizservice.demo.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author liyongzhen
 * @create 2019-03-26 12:53
 **/
public class MyWebSocket {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("www.baidu.com",80);
        if(!s.getTcpNoDelay()){
            s.setTcpNoDelay(true);
        }
    }
}
