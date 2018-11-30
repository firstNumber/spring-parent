package com.spring.bizservice.mq.consumer;

import com.spring.bizservice.mq.framework.IConsumer;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author liyongzhen
 * @create 2018-11-26 14:40
 **/
@Component
public class HourseConsumer implements IConsumer {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(HourseConsumer.class);


    @RabbitListener
    @Override
    public void receiver(String msg) {
        try {
            System.out.println("啦啦啦   "+msg);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
