package com.weilai.common.cache;

import java.util.Date;

/**
 * <descption>缓存工具类</descption>
 * @author liyongzhen
 * 
 */
public class CacheUtil {
	public static final String SYSTEM_NAME = "LYZ";//平台名称,所有缓存加这个前缀
	public static void main(String[] args) {
		Date d= new Date(System.currentTimeMillis()+ 10* 60 * 1000);
		System.out.println(d);
	}
	/**
	 * 生成缓存标识key
	 * 
	 * @param cacheName
	 * @param value
	 * @return
	 */
	public static String getCacheName(Object cacheName, Object value) {
		return new StringBuilder(SYSTEM_NAME).append(":").append(cacheName).append(":").append(value).toString();
	}
	
	public static String getCacheName(Object cacheName) {
		return new StringBuilder(SYSTEM_NAME).append(":").append(cacheName).toString();
	}
	
	/**
	 * 转换缓存时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static int getCacheTime(int time) {
		return time * 60 * 1000;
	}
	/**
	 * 转换缓存时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static Date getCacheTime(String time) throws Exception {
		return new Date(Long.valueOf(time) * 60 * 1000);
	}
}
