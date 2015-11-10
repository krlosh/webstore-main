package org.chenche.webstore.repository;

import org.chenche.webstore.domain.Cart;

public interface CartRepository {

	Cart create(Cart cart);

	Cart read(String cartId);

	void update(String cartId, Cart cart);

	void delete(String cartId);
	
}
