package com.spring.bizservice.mq.framework.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liyongzhen
 * @create 2018-11-26 14:23
 **/
@Component
public class AmqpSender {
    @Autowired
	private AmqpTemplate amqpTemplate;

	private Logger logger = LoggerFactory.getLogger(AmqpSender.class);

	public void send(String exchange, String routingKey, String msg) {
		logger.info("exchange={},routingKey={}, msg={}", exchange, routingKey, msg);
		MessageProperties mp = new MessageProperties();
		mp.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		Message message = new Message(msg.getBytes(), mp);
		amqpTemplate.send(exchange, routingKey, message);
	}
}
