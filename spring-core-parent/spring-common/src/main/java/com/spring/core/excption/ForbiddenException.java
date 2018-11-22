package com.spring.core.excption;

/**
 * @author liyongzhen
 * @create 2018-11-22 10:15
 **/
public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 6525461640799286507L;

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(String message, Throwable e) {
		super(message, e);
	}
}
