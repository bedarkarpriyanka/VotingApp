package com.voting.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
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

	private Logger log = LoggerFactory.logger(ProductController.class);
	
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

	@GetMapping("/p/{productName}")
	public String productUserView(@PathVariable String productName, ModelMap model) {
		if (productName != null) {
			try {
				String decodedProductName = URLDecoder.decode(productName, StandardCharsets.UTF_8.toString());
				Optional<Product> productOpt = productRepository.findByName(decodedProductName);
				if (productOpt.isPresent()) {
					model.put("product", productOpt.get());
				}
			} catch (UnsupportedEncodingException e) {
				log.error("Error in decoding product URL", e);
			}
		}
		return "productUserView";
	}
}
