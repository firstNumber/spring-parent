package com.spring.bizservice.mq.framework;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.spring.bizservice.mq.MQConfig;
import com.spring.bizservice.mq.framework.model.MQInfo;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author liyongzhen
 * @create 2018-11-26 13:22
 **/
@Configuration
public class InitqueuesConfig implements InitializingBean {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InitqueuesConfig.class);
    @Autowired
    CachingConnectionFactory factory;
    @Autowired
    private MQConfig mqConfig;
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            List<MQInfo> infos = mqConfig.getInfos();
            Channel channel =factory.createConnection().createChannel(false);
            for(MQInfo info:infos){
                String exchange= info.getExchange();
                String exchageType = info.getExchangeType();
                String routingKey = info.getRoutingKey();
                channel.exchangeDeclare(exchange,exchageType);
                List<String> queues = info.getQueues();
                for(String queueName :queues){
                    channel.queueDeclare(queueName,true,false,true,null);
                    channel.queueBind(queueName,exchange,exchageType);
                }
                List<String> dltimes = info.getDltimes();
                if(dltimes!=null){
                    for(String times :dltimes){
                        String queueName =String.format("dl%s%ss",routingKey,times);
                        Map<String,Object> arguments = Maps.newHashMap();
                        long time = Long.valueOf(times.substring(0,times.length()-1));
                        arguments.put("x-message-ttl", 1000*time);
						arguments.put("x-dead-letter-exchange", exchange);
						arguments.put("x-dead-letter-routing-key", routingKey);
						channel.queueDeclare(queueName, true, false, false, arguments);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("InitqueuesConfig",e.getCause());
        }
    }
}
