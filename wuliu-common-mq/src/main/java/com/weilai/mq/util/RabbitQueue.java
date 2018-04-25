package com.weilai.mq.util;

/**
 * *********************************
 * 
 * @Description: 队列名称(规则:发送方模块名称_接收方模块名称_业务说明)
 * @author: liyongzhen
 * @createdAt: 2016年11月15日下午8:32:30
 **********************************
 */
public enum RabbitQueue {
	LIGE_RABBITMQ(false) ,
	USER_STUDENT(false);

	private boolean sendEmail;// 异常时是否发送邮件

	RabbitQueue(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public boolean isSendEmail() {
		return sendEmail;
	}

	/**
	 * 根据名称找对象
	 * 
	 * @param queueName
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年11月18日
	 */
	public static RabbitQueue getByName(String queueName) {
		for (RabbitQueue qs : RabbitQueue.values()) {
			if (qs.toString().equals(queueName)) {
				return qs;
			}
		}
		return null;
	}
}
