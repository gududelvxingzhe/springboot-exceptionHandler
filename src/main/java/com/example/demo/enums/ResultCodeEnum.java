package com.example.demo.enums;

import com.example.demo.exception.BaseErrorInfoInterface;

public enum ResultCodeEnum implements BaseErrorInfoInterface {
	
	// 数据操作错误定义
	SUCCESS("200", "成功!"),
	
	BODY_NOT_MATCH("400","请求的数据格式不符!"),
	
	SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
	
	NOT_FOUND("404", "未找到该资源!"),
	
	INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
	
	SERVER_BUSY("503","服务器正忙，请稍后再试!");
	
	private final String resultCode;
	private final String resultMsg;
	
	ResultCodeEnum(String ResultCode, String ResultMsg) {
		this.resultCode=ResultCode;
		this.resultMsg = ResultMsg;
	}
	
	@Override
	public String getResultCode() {
		return resultCode;
	}
	
	@Override
	public String getResultMsg() {
		return resultMsg;
	}
}
