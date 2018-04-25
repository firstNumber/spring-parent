package com.weilai.student.core.rabbit.listenner;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import com.weilai.mq.rabbit.receive.MQReceiveHandler;
import com.weilai.mq.util.RabbitQueue;


@Service("userStudentListener")
public class UserStudentListener extends MQReceiveHandler {
	private static Logger log = Logger.getLogger(UserStudentListener.class);
	@Override
	protected void doBusi(Message message) {
		String queueName = message.getMessageProperties().getConsumerQueue();
		if (RabbitQueue.USER_STUDENT.toString().equals(queueName)) {
			System.out.println("这是"+UserStudentListener.class.getName()+"的监听...........");
			log.info(System.currentTimeMillis()+"===="+UserStudentListener.class+"====");
		}
	}
}
