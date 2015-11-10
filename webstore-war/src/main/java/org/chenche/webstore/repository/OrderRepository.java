package org.chenche.webstore.repository;

import org.chenche.webstore.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
