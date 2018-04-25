package com.weilai.user.rabbit.listenner;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import com.weilai.mq.rabbit.receive.MQReceiveHandler;
import com.weilai.mq.util.RabbitQueue;

/**
 * *********************************
 * 
 * @Description: 
 * @author: liyongzhen
 * @createdAt: 2016年6月2日下午4:14:39
 **********************************
 */
@Service("addOrderListener")
public class AddOrderListener extends MQReceiveHandler {
	private static Logger log = Logger.getLogger(AddOrderListener.class);
	@Override
	protected void doBusi(Message message) {
		String queueName = message.getMessageProperties().getConsumerQueue();
		if (RabbitQueue.LIGE_RABBITMQ.toString().equals(queueName)) {
			System.out.println("这是:"+AddOrderListener.class.getName()+"的监听...........");
			log.info(System.currentTimeMillis()+"===="+AddOrderListener.class+"====");
		}
	}

}
