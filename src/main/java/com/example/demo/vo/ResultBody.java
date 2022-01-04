package com.example.demo.vo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.enums.ResultCodeEnum;
import com.example.demo.exception.BaseErrorInfoInterface;
import com.example.demo.exception.BizException;
import com.example.demo.util.RequestUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Data
@Builder
@Accessors(chain = true)
public class ResultBody<T> {
	
	private String code;
	private String msg;
	private T data;
	
	public static <T> ResultBody<T> success(String msg) {
		String resultCode = ResultCodeEnum.SUCCESS.getResultCode();
		return new ResultBody<T>(resultCode, msg, null);
	}
	
	public static <T> ResultBody<T> success(String code, String msg) {
		
		return new ResultBody<>(code, msg, null);
	}
	
	public static <T> ResultBody<T> success(T data) {
		String resultCode = ResultCodeEnum.SUCCESS.getResultCode();
		String resultMsg = ResultCodeEnum.SUCCESS.getResultMsg();
		return new ResultBody<>(resultCode, resultMsg, data);
	}
	
	public static <T> ResultBody<T> failed(String msg) {
		String resultCode = ResultCodeEnum.INTERNAL_SERVER_ERROR.getResultCode();
		return new ResultBody<>(resultCode, msg, null);
	}
	
	public static <T> ResultBody<T> failed(String msg, T data) {
		String resultCode = ResultCodeEnum.INTERNAL_SERVER_ERROR.getResultCode();
		return new ResultBody<>(resultCode, msg, data);
	}
	
	public static ResultBody failed(BaseErrorInfoInterface baseErrorInfoInterface) {
		ResultBody<Object> body = new ResultBody<>();
		body.setCode(baseErrorInfoInterface.getResultCode());
		body.setMsg(baseErrorInfoInterface.getResultMsg());
		return body;
	}
	
	public static <T> ResultBody<T> result(String code, String msg, T data) {
		
		ResultBody<T> resultBody = new ResultBody<>();
		resultBody.setCode(code);
		resultBody.setMsg(msg);
		resultBody.setData(data);
		return resultBody;
	}
	
	public static ResultBody<String> exception(HttpServletRequest request, BizException e) {
		String resultCode = e.getErrorCode();
		String msg = e.getErrorMsg();
		StringBuilder requestStr = new StringBuilder();
		//请求方法
		String method = request.getMethod();
		requestStr.append(method).append("  ");
		
		//请求路径
		StringBuffer requestURL = request.getRequestURL();
		requestStr.append(requestURL);
		
		if (method.equalsIgnoreCase("get")) {
			String queryString = request.getQueryString();
			if (StringUtils.hasText(queryString)) {
				requestStr.append("?");
				requestStr.append(queryString);
			}
		} else {
			requestStr.append("    ");
			String parameters = RequestUtil.getParametersFromPost(request);
			requestStr.append(parameters);
		}
		
		return result(resultCode, msg, requestStr.toString());
	}
	
	public ResultBody() {
	}
	
	public ResultBody(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
