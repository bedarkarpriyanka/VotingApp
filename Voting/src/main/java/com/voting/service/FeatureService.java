package com.voting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.domain.Feature;
import com.voting.domain.Product;
import com.voting.domain.User;
import com.voting.repositories.FeatureRepository;
import com.voting.repositories.ProductRepository;

@Service
public class FeatureService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FeatureRepository featureRepository;
	
	public Feature createFeature(Integer productId, User user) {
		Feature feature = new Feature();
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			feature.setProduct(product);
			product.getFeatures().add(feature);
			
			feature.setUser(user);
			user.getFeatures().add(feature);
			
			feature.setStatus("Pending Review");
			return featureRepository.save(feature);
		}
		return feature;
	}
	
	public Feature save(Feature feature) {
		return featureRepository.save(feature);
	}

	public Optional<Feature> findById(Integer featureId) {
		return featureRepository.findById(featureId);
	}
}
