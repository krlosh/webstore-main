package org.chenche.webstore.controller;

import org.chenche.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customers")
	public String list(Model model){
		
		model.addAttribute("customers", this.customerService.getAllCustomers());
		
		return "customers";
	}
}
