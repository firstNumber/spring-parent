package com.spring.bizservice.mq.controller;

import com.spring.bizservice.mq.MQConfig;
import com.spring.bizservice.mq.framework.model.MQInfo;
import com.spring.bizservice.mq.framework.sender.AmqpSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liyongzhen
 * @create 2018-11-26 14:21
 **/

@RestController
@RequestMapping("/mq/")
public class MQController {

    private Logger logger = LoggerFactory.getLogger(MQController.class);
	@Autowired
	private AmqpSender sender;

	@Autowired
	private MQConfig mqConfig;

    @RequestMapping(value = "/push", method = RequestMethod.POST)
	public void push(@RequestParam(value = "subject", required = true) String subject,
			@RequestParam(value = "msg", required = true) String msg,
			@RequestParam(value = "delaySeconds", required = false ,defaultValue="0") Integer delaySeconds) {
		MQInfo mqInfo = getMQInfo(subject);
		logger.info("入参subject:{},msg:{},delaySecond:{}", subject, msg, delaySeconds);
		if (mqInfo == null) {
			throw new IllegalArgumentException("mq info is null");
		}
		//queueName
		String routingKey = mqInfo.getRoutingKey();
		String exchange = mqInfo.getExchange();
		if(delaySeconds>0) {
			routingKey = getDelayRoutingKey(mqInfo.getRoutingKey(), delaySeconds);
			logger.info("subject:{},routingKey:{}, delay:{}s",subject,routingKey,delaySeconds);
			/** 使用defaultExchange **/
			exchange = null;
		}
		sender.send(exchange, routingKey, msg);
	}

	private MQInfo getMQInfo(String subject) {
		List<MQInfo> infos = mqConfig.getInfos();
		if (CollectionUtils.isEmpty(infos)) {
			return null;
		}
		for (MQInfo mqInfo : infos) {
			if (subject.equals(mqInfo.getName())) {
				return mqInfo;
			}
		}
		return null;
	}


	private String getDelayRoutingKey(String name,Integer delaySeconds) {
		return String.format("dl.%s.%ss", name,delaySeconds);
	}
}
