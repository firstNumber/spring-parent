package com.weilai.user.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.mq.rabbit.send.Rabbit;
import com.weilai.mq.util.RabbitQueue;
import com.weilai.common.dao.BaseDao;
import com.weilai.user.account.dao.CarboxDao;
import com.weilai.user.account.model.Carbox;
import com.weilai.user.account.service.CarboxService;


@Service("carboxService")
public class CarboxServiceImpl extends BaseServiceImpl<Carbox> implements CarboxService{

	@Autowired
	private CarboxDao carboxDaoImpl;
	
	@Override
	public BaseDao<Carbox> getBaseDao() {
		return carboxDaoImpl;
	}

	@Override
	public Carbox queryCar() {
		Carbox carbox = get(2);
		System.out.println(carbox.toString());
		Rabbit.send(RabbitQueue.LIGE_RABBITMQ, "一个MQ消息:啦啦啦");
		return carbox;
	}
	
}
