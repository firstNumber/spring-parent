package com.weilai.student.core.service.impl;

import org.springframework.stereotype.Service;

import com.weilai.common.exception.BusiException;
import com.weilai.mq.rabbit.send.Rabbit;
import com.weilai.mq.util.RabbitQueue;
import com.weilai.student.core.service.UserStudent;

@Service("userStudent")
public class UserStudentImpl implements UserStudent {

	@Override
	public String queryStudent() {
		Rabbit.send(RabbitQueue.LIGE_RABBITMQ, "哟哟哟queryStudent:");
		throw  new RuntimeException("啦啦");
	}
	
}
