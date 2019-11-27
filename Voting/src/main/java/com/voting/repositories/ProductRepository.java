package com.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
