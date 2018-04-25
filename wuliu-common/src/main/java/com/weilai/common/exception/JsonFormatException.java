package com.weilai.common.exception;

/**
 * json格式化异常
 * *********************************
* @author: liyongzhen
* @createdAt: 2016年6月2日上午9:36:31
**********************************
 */
public class JsonFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer errorCode = 2;
	public Integer getErrorCode() {
		return errorCode;
	}
	public JsonFormatException(String message){
		super(message);
	}
	public JsonFormatException(Integer errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	public JsonFormatException(Throwable cause) {
		super(cause);
	}
	public JsonFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	public JsonFormatException(Integer errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
}
