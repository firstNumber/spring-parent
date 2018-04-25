package com.weilai.common.exception;

/**
 * sql语句异常
 * *********************************
* @Description: TODO
* @author: liyongzhen
* @createdAt: 2016年6月2日上午9:36:31
**********************************
 */
public class MySqlException extends RuntimeException {
	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	public MySqlException(Throwable cause) {
		super(cause);
	}
	public MySqlException(String message, Throwable cause) {
		super(message, cause);
	}
	public MySqlException(String message){
		super(message);
	}
}
