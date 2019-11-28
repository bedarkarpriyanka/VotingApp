package com.voting.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.voting.domain.Product;
import com.voting.domain.User;
import com.voting.repositories.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/products")
	public String createProduct(@AuthenticationPrincipal User user) {
		Product product = new Product();
		product.setPublished(false);
		product.setUser(user);
		
		product = productRepository.save(product);
		return "redirect:/products/" + product.getId();
	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable Integer productId, ModelMap model, HttpServletResponse response) throws IOException {
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			model.put("product", product);
		}
		else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Product with ID "+ productId +" was not found");
		}
		return "product";
	}
	
	@PostMapping("/products/{productId}")
	public String updateProduct(@PathVariable Integer productId, Product product) {
		product = productRepository.save(product);
		return "redirect:/products/";
	}
}
