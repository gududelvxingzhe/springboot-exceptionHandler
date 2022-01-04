package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RequestUtil {
	
	/**
	 * 获取post请求中body的参数
	 * @param request HttpServletRequest
	 * @return String
	 */
	public static String getParametersFromPost(HttpServletRequest request){
		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String line="";
		StringBuilder buffer = new StringBuilder();
		try {
			while ((line = br.readLine()) != null){
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
