package com.weilai.common.exception;

/**
 * 自定义业务异常
 * *********************************
* @Description: TODO
* @author: liyongzhen
* @createdAt: 2016年6月2日上午9:36:31
**********************************
 */
public class BusiException extends RuntimeException {
	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer errorCode = 2;
	public Integer getErrorCode() {
		return errorCode;
	}
	public BusiException(String message){
		super(message);
	}
	public BusiException(Integer errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	public BusiException(Throwable cause) {
		super(cause);
	}
	public BusiException(String message, Throwable cause) {
		super(message, cause);
	}
	public BusiException(Integer errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
//	/**
//	 * 
//	* @Description: 重写toString方法,只输出抛异常的位置
//	* @return
//	* @Author: liyongzhen
//	* @Date: 2016年11月3日
//	 */
//	@Override
//    public String toString() {
//		StackTraceElement[] steArr = getStackTrace();
//		if(steArr.length==0) return "BusiException stackTrace 为空";
//		StackTraceElement ste = steArr[0];
//		return ste.getClassName()+"."+ste.getMethodName()+"("+ste.getFileName()+":"+ste.getLineNumber()+")";
//    }
	
	@Override
	public Throwable fillInStackTrace(){
		return null;
	}
}
