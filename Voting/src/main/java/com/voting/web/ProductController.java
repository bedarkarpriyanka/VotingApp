package com.voting.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.voting.domain.Product;

@Controller
public class ProductController {

	@GetMapping("/products")
	public String getProducts(ModelMap model) {
		//model.put("product", new Product());
		return "product";
	}
	
	@PostMapping("/products")
	public String createProduct() {
		return "redirect:/products";
	}
}
