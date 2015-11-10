package org.chenche.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome(Model model){
//		model.addAttribute("greeting","Bienvenido a la tienda");
//		model.addAttribute("tagline","La gran y única tienda");
//		return "welcome";
		return "redirect:/products"; 
	}
}
