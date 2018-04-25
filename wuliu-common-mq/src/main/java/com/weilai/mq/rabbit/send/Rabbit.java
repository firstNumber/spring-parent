package com.weilai.mq.rabbit.send;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;

import com.weilai.common.util.SpringContextUtil;
import com.weilai.mq.util.RabbitQueue;

public class Rabbit {
	private static Logger logger = Logger.getLogger("MQSend");

	private static AmqpTemplate amqpTemplate = (AmqpTemplate) SpringContextUtil.getBean("amqpTemplate"); // 消息推送

	/**
	 * 发送消息
	 * 
	 * @param queueName
	 * @param msg
	 *            需重写toString方法
	 * @Author: liyongzhen
	 * @Date: 2016年11月14日
	 */
	public static void send(RabbitQueue queueName, Object msg) {
		try {
			amqpTemplate.convertAndSend(queueName.toString(), msg);
			logger.info("发送消息成功;queue:" + queueName + ",sendMsg:" + msg);
		} catch (Exception e) {
			logger.error("发送消息失败;queue:" + queueName + ",sendMsg:" + msg, e);
			if (queueName.isSendEmail()) {
			}
		}
	}
}
