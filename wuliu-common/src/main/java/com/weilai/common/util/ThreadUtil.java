package com.weilai.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * *********************************
 * 
 * @Description: 使用线程池,代替代码中显式启动线程
 * @author: liyongzhen
 * @createdAt: 2017年4月7日上午11:44:03
 **********************************
 */
public class ThreadUtil {
	private static final ThreadPoolExecutor EXECUTOR_SERVICE = (ThreadPoolExecutor) Executors.newFixedThreadPool(30);

	public static void execute(Runnable command) {
		EXECUTOR_SERVICE.execute(command);
	}

	public static int getActiveThreadCount() {
		return EXECUTOR_SERVICE.getActiveCount();
	}
}
