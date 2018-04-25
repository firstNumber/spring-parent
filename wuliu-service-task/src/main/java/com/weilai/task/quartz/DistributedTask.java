package com.weilai.task.quartz;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.weilai.common.util.SpringContextUtil;
import com.weilai.task.core.UserTaskService;

/**
 * @author
 * @description 一句话描述该文件的用途
 * @date 2016-05-23
 */

@Service
public class DistributedTask extends QuartzJobBean {
	private static final Log logger = LogFactory.getLog(DistributedTask.class);

	private String targetObject;
	private String targetMethod;

	// 这里就是因为有上文中的AutowiringSpringBeanJobFactory才可以使用@Autowired注解，否则只能在配置文件中设置这属性的值
	@Autowired
	private UserTaskService userTaskService;

	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			logger.info("开始执行>>>>>> [" + targetObject + ":" + targetMethod + "] ");
			// Object otargetObject =
			// SpringContextUtil.getBean("quartzService");
			Object otargetObject = SpringContextUtil.getBean(targetObject);
			Method m = null;
			try {
				m = otargetObject.getClass().getMethod(targetMethod, new Class[] {});
				m.invoke(otargetObject, new Object[] {});
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new JobExecutionException(e);
		}
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}