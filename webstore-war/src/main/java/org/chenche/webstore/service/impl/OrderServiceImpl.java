package org.chenche.webstore.service.impl;

import org.chenche.webstore.domain.Order;
import org.chenche.webstore.domain.Product;
import org.chenche.webstore.repository.OrderRepository;
import org.chenche.webstore.repository.ProductRepository;
import org.chenche.webstore.service.CartService;
import org.chenche.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	public void processOrder(String productId, int quantity) {
		Product productById=this.productRepository.getProductById(productId);
		
		if(productById.getUnitsInStock()<quantity){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock"+ productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = this.orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}

}
