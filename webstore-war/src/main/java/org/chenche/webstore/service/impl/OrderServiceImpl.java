package org.chenche.webstore.service.impl;

import javax.transaction.Transactional;

import org.chenche.webstore.domain.CartItem;
import org.chenche.webstore.domain.Order;
import org.chenche.webstore.domain.OrderItemVO;
import org.chenche.webstore.domain.OrderVO;
import org.chenche.webstore.domain.ProductVO;
import org.chenche.webstore.domain.ShippingDetail;
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
	
	
	@Override
	@Transactional
	public void processOrder(String productId, int quantity) {
		ProductVO productById=this.productRepository.getProductById(productId);
		
		if(productById.getUnitsInStock()<quantity){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock"+ productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
		this.productRepository.updateProduct(productById);
	}

	private OrderVO translateFromOrder(Order order){
		OrderVO value =  new OrderVO();
		value.setCustomer(order.getCustomer());
		ShippingDetail shipDetail = order.getShippingDetail();
		value.setShippingDate(shipDetail.getShippingDate());
		value.setShippingAddress(shipDetail.getShippingAddress());
		for(CartItem cartItem: order.getCart().getCartItems().values() ){
			value.getItems().add(translateItemFromCart(cartItem));
		}
		return value;
	}
	
	private OrderItemVO translateItemFromCart(CartItem cartItem) {
		OrderItemVO orderItem = new OrderItemVO();
		orderItem.setQuantity(cartItem.getQuantity());
		orderItem.setTotalPrice(cartItem.getTotalPrice());
		orderItem.setProduct(ProductTranslator.translateToProductVO(cartItem.getProduct()));
		return orderItem;
	}

	@Override
	@Transactional
	public Long saveOrder(Order order) {
		Long orderId = this.orderRepository.saveOrder(this.translateFromOrder(order));
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}

}
