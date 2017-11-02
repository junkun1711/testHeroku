package com.example.model;

import java.util.Date;
import java.util.List;

public class Order {
	private Integer id;
	private Integer userId;
	private Double orderPrice;
	private Date orderTime;
	private String orderTrace;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderTrace() {
		return orderTrace;
	}
	public void setOrderTrace(String orderTrace) {
		this.orderTrace = orderTrace;
	}
	public List<OrderItem> getProducts() {
		return products;
	}
	public void setProducts(List<OrderItem> products) {
		this.products = products;
	}
	private List<OrderItem> products;
	
}
