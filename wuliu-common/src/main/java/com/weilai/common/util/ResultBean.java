package com.weilai.common.util;

import java.io.Serializable;

/**
 * *********************************
 * 
 * @Description: 用于系统内部交互时,返回一些对象时,还需要额外返回一些状态,例如 成功,失败等 使用Map
 *               <String,Object>或者MapUtil麻烦时,可以使用此类.
 *               bean是要返回的对象,status和message则是状态和信息,status默认=1
 * @author: liyongzhen
 * @createdAt: 2016年11月29日下午6:07:19
 **********************************
 */
public class ResultBean<T> implements Serializable {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Integer status = 1;// 默认成功
	private String errorMsg;
	private String okMsg;
	private T bean;

	public ResultBean() {

	}

	/**
	 * status==1 则成功,否则失败
	 * 
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2017年5月11日
	 */
	public boolean notSuccess() {
		return !status.equals(1);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 设置错误信息,会把status标记为2
	 * 
	 * @param errorMsg
	 * @Author: liyongzhen
	 * @Date: 2016年12月8日
	 */
	public void setErrorMsg(String errorMsg) {
		if (this.status == 1) {
			this.status = 2;
		}
		this.errorMsg = errorMsg;
	}

	public void setErrorMsg(String errorMsg, Integer status) {
		this.status = status;
		this.errorMsg = errorMsg;
	}

	public String getOkMsg() {
		return okMsg;
	}

	public void setOkMsg(String okMsg) {
		this.okMsg = okMsg;
	}

	@Override
	public String toString() {
		return "ResultBean [status=" + status + ", errorMsg=" + errorMsg + ", okMsg=" + okMsg + ", bean=" + bean + "]";
	}

}
