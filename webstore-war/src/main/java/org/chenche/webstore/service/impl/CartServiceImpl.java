package org.chenche.webstore.service.impl;

import javax.transaction.Transactional;

import org.chenche.webstore.domain.Cart;
import org.chenche.webstore.domain.CartItem;
import org.chenche.webstore.domain.CartItemVO;
import org.chenche.webstore.domain.CartVO;
import org.chenche.webstore.exception.InvalidCartException;
import org.chenche.webstore.repository.CartRepository;
import org.chenche.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Transactional
	public Cart create(Cart cart) {
		CartVO cartVO = translateFromCart(cart);
		CartVO newCart = cartRepository.create(cartVO);
		return translateToCart(newCart);
	}

	private Cart translateToCart(CartVO newCart) {
		if(newCart == null){
			return null;
		}
		Cart cart = new Cart(newCart.getCartId());
		for(CartItemVO vo : newCart.getItems()){
			CartItem item = new CartItem(ProductTranslator.translateToProduct(vo.getProduct()));
			item.setQuantity(vo.getQuantity());
			item.setTotalPrice(vo.getTotalPrice());
			cart.addCartItem(item);
		}
		return cart;
	}

	private CartVO translateFromCart(Cart cart) {
		if(cart==null){
			return null;
		}
		CartVO vo = new CartVO();
		vo.setCartId(cart.getCartId());
		for(CartItem item:cart.getCartItems().values()){
			CartItemVO itemVO=new CartItemVO();
			//itemVO.setCart(vo);
			itemVO.setQuantity(item.getQuantity());
			itemVO.setTotalPrice(item.getTotalPrice());
			itemVO.setProduct(ProductTranslator.translateToProductVO(item.getProduct()));
			vo.getItems().add(itemVO);
		}
		return vo;
	}

	@Transactional
	public Cart read(String cartId) {
		return translateToCart(cartRepository.read(cartId));
	}

	@Transactional
	public void update(String cartId, Cart cart) {
		cartRepository.update(cartId, translateFromCart(cart));
	}

	@Transactional
	public void delete(String cartId) {
		cartRepository.delete(cartId);

	}

	@Override
	@Transactional
	public Cart validate(String cartId) {
		CartVO cart = cartRepository.read(cartId);
		if (cart == null/* ||cart.getCartItems().size()==0 */) {
			throw new InvalidCartException(cartId);
		}
		return translateToCart(cart);
	}
}
