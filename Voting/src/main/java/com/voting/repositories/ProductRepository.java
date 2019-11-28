package com.voting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.domain.Product;
import com.voting.domain.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByUser(User user);
}
