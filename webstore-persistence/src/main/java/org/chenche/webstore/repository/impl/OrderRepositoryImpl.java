package org.chenche.webstore.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chenche.webstore.domain.OrderVO;
import org.chenche.webstore.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

	private static Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long saveOrder(OrderVO order) {
		this.entityManager.persist(order);
		logger.info("Order saved "+order.getOrderId());
		return order.getOrderId();
	}

}
