package org.chenche.webstore.service;

import org.chenche.webstore.domain.Order;

public interface OrderService {
	void processOrder(String productId,int quantity);
	
	Long saveOrder(Order order);
}
