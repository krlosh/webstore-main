package org.chenche.webstore.controller;

import org.chenche.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/{productId}/{quantity}")
	public String process(Model model,@PathVariable("productId")String productId,@PathVariable("quantity") int quantity){
		this.orderService.processOrder(productId, quantity);
		return "redirect:/products";
	}
}
