package org.chenche.webstore.repository;

import org.chenche.webstore.domain.CartVO;

public interface CartRepository {

	CartVO create(CartVO cart);

	CartVO read(String cartId);

	void update(String cartId, CartVO cart);

	void delete(String cartId);
	
}
