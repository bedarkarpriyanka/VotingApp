package com.voting.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voting.domain.Feature;
import com.voting.service.FeatureService;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

	@Autowired
	FeatureService featureService;
	
	@PostMapping("")
	public String createFeature(@PathVariable Integer productId) {
		Feature savedFeature = featureService.createFeature(productId);
		return "redirect:/products/" + productId + "/features/" + savedFeature.getId();
	}
	
	@GetMapping("{featureId}")
	public String getFeature(@PathVariable Integer productId, @PathVariable Integer featureId, ModelMap model) {
		Optional<Feature> featureOpt = featureService.findById(featureId);
		if (featureOpt.isPresent()) {
			model.put("feature", featureOpt.get());
		}
		//TODO: handle where feature does not exist for featureId
		return "feature";
	}
	
	@PostMapping("{featureId}")
	public String updateFeature(@PathVariable Integer productId, @PathVariable Integer featureId, Feature feature) {
		featureService.save(feature);
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}
}
