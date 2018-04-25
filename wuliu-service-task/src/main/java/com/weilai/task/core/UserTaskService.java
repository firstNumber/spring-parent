package com.weilai.task.core;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.weilai.common.util.SpringContextUtil;


@Service("userTaskService")
public class UserTaskService{
	
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void pushUser(){
		System.out.println("我是一個Task");
	};
	
	
	public String invoke(String serviceName, String mothedName) {
		try {
			Object service = SpringContextUtil.getBean(serviceName);
			java.lang.reflect.Method m = service.getClass().getMethod(mothedName);
			m.invoke(service);
			return "执行成功!";
		} catch (Exception e) {
			return "执行失败:" + e.getMessage();
		}

	}
}
