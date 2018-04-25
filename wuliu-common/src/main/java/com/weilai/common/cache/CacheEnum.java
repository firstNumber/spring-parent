package com.weilai.common.cache;

import org.apache.commons.lang.StringUtils;

/**
 * ********************************* 缓存枚举项 除临时缓存(例如防重复点击),其他缓存名称都要放这里.
 * 初始化时配置对应的service和默认缓存时长
 * 
 * @author: liyongzhen
 * @createdAt: 2016年8月29日上午9:23:17
 ********************************** 
 */
public enum CacheEnum {
	QCC("123",0);
	private String serviceName;
	private int cacheSecond;

	/**
	 * <p>
	 * 缓存名称
	 * </p>
	 * 
	 * @param serviceName
	 *            缓存对应的serviceName名称,如果为null或"",则代表不需要去service查询
	 *            ,对应的service需要重写get4CacheFactory方法
	 * @param cacheSecond
	 *            默认缓存的时间,单位秒,0代表无限制
	 */
	CacheEnum(String serviceName, Integer cacheSecond) {
		this.serviceName = serviceName;
		this.cacheSecond = cacheSecond;
	}

	public String getServiceName() {
		return serviceName;
	}

	public int getCacheSecond() {
		return cacheSecond;
	}

	/**
	 * 是否包含这个缓存名称,如果包含则返回,否则返回null
	 * 
	 * @param cacheName
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年5月17日
	 */
	public static CacheEnum contains(String cacheName) {
		if (StringUtils.isNotBlank(cacheName)) {
			for (CacheEnum cn : CacheEnum.values()) {
				if (cn.toString().equals(cacheName)) {
					return cn;
				}
			}
		}
		return null;
	}
}
