package org.chenche.webstore.repository;

import org.chenche.webstore.domain.OrderVO;

public interface OrderRepository {
	Long saveOrder(OrderVO order);
}
