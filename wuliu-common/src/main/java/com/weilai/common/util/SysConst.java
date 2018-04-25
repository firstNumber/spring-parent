package com.weilai.common.util;

import org.apache.commons.lang.StringUtils;
public class SysConst {

	public static final Integer OPERATION_TIME = 30; // 一些更新操作动作默认缓存的秒数,用于防止重复提交
	/**
	 * 验证码有效时间300s
	 */
	public static final Integer VERIFICATION_CODE_TIME = 5 * 60;

	/**
	 * *********************************
	 * 
	 * @Description: 用户操作类型
	 * @author: liyongzhen
	 * @createdAt: 2017年8月30日下午3:43:05
	 ********************************** 
	 */
	public enum UserOperationType {
		禁用用户(1), 启用用户(2),;
		private Integer value;

		UserOperationType(Integer value) {
			this.value = value;
		}

		public Integer getCode() {
			return this.value;
		}
	}

	/**
	 * @author: liyongzhen
	 * @createdAt: 2016年8月3日上午11:26:35
	 ********************************** 
	 */
	public enum OrderOrigin {
		LYZ;
		// 判断类型是否正确
		public static boolean contains(String type) {
			boolean b = false;
			if (StringUtils.isNotBlank(type)) {
				for (OrderOrigin ft : OrderOrigin.values()) {
					if (ft.toString().equalsIgnoreCase(type)) {
						b = true;
						break;
					}
				}
			}
			return b;
		}
	}

	/**
	 * 
	 * @author: liyongzhen
	 * @createdAt: 2015年8月19日下午4:07:44
	 ********************************** 
	 */
	public enum FromType {
		web, ios, android;
		// 判断类型是否正确
		public static boolean contains(String type) {
			boolean b = false;
			if (StringUtils.isNotBlank(type)) {
				for (FromType ft : FromType.values()) {
					if (ft.toString().equalsIgnoreCase(type)) {
						b = true;
						break;
					}
				}
			}
			return b;
		}
	}

}
