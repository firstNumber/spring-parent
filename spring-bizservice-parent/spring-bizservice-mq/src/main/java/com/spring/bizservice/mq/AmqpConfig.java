package com.spring.bizservice.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.spring.bizservice.mq.consumer.HourseConsumer;
import com.spring.bizservice.mq.framework.ApplicationContextUtil;
import com.spring.bizservice.mq.framework.ConsumerException;
import com.spring.bizservice.mq.framework.IConsumer;
import org.aspectj.bridge.MessageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.table.TableRowSorter;

/**
 * @author liyongzhen
 * @create 2018-11-26 10:59
 **/
@Configuration
@AutoConfigureAfter(ApplicationContextUtil.class)
public class AmqpConfig {
    private Logger logger = LoggerFactory.getLogger(AmqpConfig.class);

    @Autowired
    HourseConsumer hourseConsumer;


    @Bean("houseDefaultMLC")
    public SimpleMessageListenerContainer hourseDefaultMessageListenerContainer(CachingConnectionFactory factory){
        return getMessageContainer(factory,"queue.order.house",hourseConsumer);
    }

    public SimpleMessageListenerContainer  getMessageContainer(CachingConnectionFactory factory, String queueName, IConsumer consumer){
        SimpleMessageListenerContainer  container  = new SimpleMessageListenerContainer(factory);
        container.setQueueNames(queueName);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(50);
        container.setConcurrentConsumers(2);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg = new String(message.getBody());
                try {
                    consumer.receiver(msg);
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }catch (ConsumerException ce){
                    logger.error("消费服务错误,重新回到队列");
                    channel.basicRecover(true);
                }catch (Exception e){
                    logger.error("消息服务异常进入unack",e);
                }
            }
        });

        return container;

    }

}
