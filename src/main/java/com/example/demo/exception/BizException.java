package com.example.demo.exception;

public class BizException extends RuntimeException{
	
	private String errorCode;
	private String errorMsg;
	
	public BizException(String errorCode,String errorMsg){
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
	
	public BizException(BaseErrorInfoInterface baseErrorInfoInterface){
		this.errorCode = baseErrorInfoInterface.getResultCode();
		this.errorMsg = baseErrorInfoInterface.getResultMsg();
	}
	public BizException(String errorCode,String errorMsg, Throwable acuse){
		super(errorCode,acuse);
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
	
	public BizException(){
	
	}
	
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	@Override
	public String toString() {
		return "BizException{" +
				"errorCode='" + errorCode + '\'' +
				", errorMsg='" + errorMsg + '\'' +
				'}';
	}
}
