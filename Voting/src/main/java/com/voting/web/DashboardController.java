package com.voting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voting.domain.Product;
import com.voting.domain.User;
import com.voting.repositories.ProductRepository;

@Controller
public class DashboardController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/")
	public String rootView() {
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {
		List<Product> products = productRepository.findByUser(user);
		model.put("products", products);
		return "dashboard";
	}

}
