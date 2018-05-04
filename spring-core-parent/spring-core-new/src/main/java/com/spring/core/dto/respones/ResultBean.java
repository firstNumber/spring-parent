package com.spring.core.dto.respones;

/**
 * @author liyongzhen
 * @create 2018-05-04 11:50
 **/
public class ResultBean<T> {
    private int code = 0;

	private T data;

	private String msg = "success";


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public ResultBean() {
	}

	/**
	 * 失败返回
	 *
	 * @param code
	 *            错误码
	 * @param msg
	 *            错误消息
	 */
	public ResultBean(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 普通数据返回
	 *
	 * @param data
	 */
	public ResultBean(T data) {
		this.code = 0;
		this.data = data;
		this.msg = "success";
	}


	@Override
	public String toString() {
		return "ResultBean [code=" + code + ", data=" + data + ", msg=" + msg
				+ ", pageInfo=" + null + "]";
	}
}
