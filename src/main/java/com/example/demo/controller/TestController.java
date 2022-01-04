package com.example.demo.controller;

import com.example.demo.enums.ResultCodeEnum;
import com.example.demo.exception.BizException;
import com.example.demo.vo.ResultBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

	@GetMapping("/test1")
	public ResultBody<HashMap<String, String>> test1(String id) {
		HashMap<String, String> map = new HashMap<>();
		
		try {
			if (!StringUtils.hasText(id)){
				throw new BizException(ResultCodeEnum.INTERNAL_SERVER_ERROR);
			}
			map.put(id,"测试数据");
		} catch (BizException e) {
			e.printStackTrace();
		}
		
		return ResultBody.success(map);
		
	}
	
	@GetMapping("/test2")
	public ResultBody<HashMap<String, String>> test2(String id) throws Exception{
		HashMap<String, String> map = new HashMap<>();
		if (!StringUtils.hasText(id)){
			throw new BizException(ResultCodeEnum.INTERNAL_SERVER_ERROR);
		}
		map.put(id,"测试数据");
		return ResultBody.success(map);
	}
	
	@GetMapping("/test3")
	public ResultBody<Integer> test3(){
		
		int count = Integer.parseInt("asd123");
		return ResultBody.success(count);
	}
}
