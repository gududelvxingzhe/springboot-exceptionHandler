package com.example.demo.exception;


import com.example.demo.enums.ResultCodeEnum;
import com.example.demo.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BizException.class)
	public ResultBody<String> bizExceptionHandler(BizException e, HttpServletRequest request){
		log.error("自定义异常：", e);
		ResultBody<String> resultBody = ResultBody.exception(request, e);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		String msg = resultBody.getMsg();
		String data = resultBody.getData();
		log.error("Time:{} Exception:{} Request:{}", time, msg, data);
	
		return resultBody;
	}
	
	//处理其他异常
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
		log.error("未知异常！原因是:", e);
		return ResultBody.failed(ResultCodeEnum.INTERNAL_SERVER_ERROR);
	}
}
