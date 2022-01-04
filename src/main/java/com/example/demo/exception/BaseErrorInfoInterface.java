package com.example.demo.exception;

/**
 * 自定义错误描述接口，枚举类徐亚实现此接口
 */
public interface BaseErrorInfoInterface {
	
	/** 错误码*/
	String getResultCode();
	
	/** 错误描述*/
	String getResultMsg();
}
