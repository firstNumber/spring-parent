package com.weilai.mq.rabbit.receive;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;
import com.weilai.mq.rabbit.send.Rabbit;
import com.weilai.mq.util.RabbitQueue;

/**
 * *********************************
 * 
 * @Description: 消息处理 模板方法
 * @author: liyongzhen
 * @createdAt: 2016年11月18日下午5:08:31
 **********************************
 */
public abstract class MQReceiveHandler implements ChannelAwareMessageListener {
	private static Logger mqLog = Logger.getLogger("MQReceive");

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		String queueName = message.getMessageProperties().getConsumerQueue();
		try {
			doBusi(message);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			mqLog.info("消费消息成功;queue:" + queueName + ",message:" + message);
		} catch (Exception e) {
			mqLog.error("消费消息失败;queue:" + queueName + ",message:" + message, e);
			RabbitQueue qn = RabbitQueue.getByName(queueName);
			if (qn != null && qn.isSendEmail()) {
//				Rabbit.sendEmail4MQError(qn,
//						"消费消息失败;queue:" + queueName + ",message:" + message + ",exMsg:" + e.getMessage());
			}
			try {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
			} catch (IOException e1) {
				mqLog.error("nack消息失败;queue:" + queueName + ",message:" + message, e);
				if (qn != null && qn.isSendEmail()) {
//					Rabbit.sendEmail4MQError(qn,
//							"nack消息失败;queue:" + queueName + ",message:" + message + ",exMsg:" + e.getMessage());
				}
			}
		}
	}

	/**
	 * listener 中用此方法实现业务逻辑
	 * 
	 * @param message
	 * @Author: liyongzhen
	 * @Date: 2016年11月18日
	 */
	protected abstract void doBusi(Message message);

}
