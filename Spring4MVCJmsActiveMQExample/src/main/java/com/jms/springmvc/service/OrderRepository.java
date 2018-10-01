package com.jms.springmvc.service;

import java.util.Map;

import com.jms.springmvc.model.Order;


public interface OrderRepository {

	public void putOrder(Order order);
	
	public Order getOrder(String orderId);
	
	public Map<String, Order> getAllOrders();
}
