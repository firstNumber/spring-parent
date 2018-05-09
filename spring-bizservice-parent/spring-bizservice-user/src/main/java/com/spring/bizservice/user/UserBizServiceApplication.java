package com.spring.bizservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liyongzhen
 * @create 2018-05-04 10:30
 **/
@SpringBootApplication
@EnableEurekaClient
public class UserBizServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBizServiceApplication.class,args);
    }
}
