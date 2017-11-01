package com.example;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebForChat {
	
	public String home(Map<String, Object> model) {
		
		return "webforchat/home";
	}
	
	public String homeProduct(Map<String, Object> model, HttpServletRequest request) {
		model.put("productId", request.getParameter("productId"));
		
		return "webforchat/productDetail";
	}
	
	public String cart(Map<String, Object> model) {
		
		return "webforchat/cart";
	}
	
	public String order(Map<String, Object> model) {
		
		return "webforchat/order";
	}
	
	public String user(Map<String, Object> model) {
		
		return "webforchat/user";
	}
}
