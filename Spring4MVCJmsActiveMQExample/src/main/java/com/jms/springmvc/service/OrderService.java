package com.jms.springmvc.service;

import java.util.Map;

import com.jms.springmvc.model.InventoryResponse;
import com.jms.springmvc.model.Order;


public interface OrderService {
	public void sendOrder(Order order);
	
	public void updateOrder(InventoryResponse response);
	
	public Map<String, Order> getAllOrders();
}
