package org.chenche.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.chenche.webstore.domain.OrderVO;
import org.chenche.webstore.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOrderRepositoryImpl implements OrderRepository {

	private Map<Long, OrderVO> listOfOrders;

	private long nextOrderId;

	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, OrderVO>();
		nextOrderId = 1000;
	}

	public Long saveOrder(OrderVO order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}

	private synchronized long getNextOrderId() {
		return nextOrderId++;
	}

}
