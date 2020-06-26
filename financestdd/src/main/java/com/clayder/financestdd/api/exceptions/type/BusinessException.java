package com.clayder.financestdd.api.exceptions.type;

public class BusinessException extends RuntimeException {

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
