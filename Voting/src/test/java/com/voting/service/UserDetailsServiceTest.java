package com.voting.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserDetailsServiceTest {

	@Test
	public void generateEncryptedPassword() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "p123";
		String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		Assertions.assertNotEquals(rawPassword, encodedPassword);
	}

}
