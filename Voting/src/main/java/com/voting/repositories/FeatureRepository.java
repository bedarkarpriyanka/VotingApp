package com.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.domain.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
