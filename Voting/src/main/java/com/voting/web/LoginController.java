package com.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voting.domain.User;
import com.voting.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	//@RequestMapping(value="/login", method=RequestMethod.GET)
	@GetMapping("/login")
	public String loginView() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(User user) {
		User savedUser = userService.save(user);
		System.out.println("Before Saving:" + user.toString());
		System.out.println("After Saving:" + savedUser.toString());
		return "redirect:/login";
	}
}
