package com.weilai.common.enumeration;

/**
 * *********************************
* @Description: 公用1.派单2.抢单
* @author: liyongzhen
* @createdAt: 2017年11月29日上午11:47:19
**********************************
 */
public enum OrderType {
	派单(1), 抢单(2);
	private Integer value;
	
	OrderType(Integer value) {
		this.value = value;
	}

	public Integer getCode() {
		return this.value;
	}

	public static String code2Name(Integer code) {
		for (OrderType e : OrderType.values()) {
			if (e.getCode().equals(code)) {
				return e.toString();
			}
		}
		return null;
	}

	public static OrderType getByCode(Integer code) {
		for (OrderType e : OrderType.values()) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}
		return null;
	}
}
