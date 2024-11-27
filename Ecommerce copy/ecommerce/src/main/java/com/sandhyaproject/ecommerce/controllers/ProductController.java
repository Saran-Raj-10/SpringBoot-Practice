package com.sandhyaproject.ecommerce.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandhyaproject.ecommerce.models.Product;
import com.sandhyaproject.ecommerce.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
//	@GetMapping("/products")
//	@GetMapping 
//	public List<Map<String, Object>> getAllProducts() {
//	    return Arrays.asList(
//	        Map.of("name", "product 1", "price", 234),
//	        Map.of("name", "product 2", "price", 134)
//	    );
//	}
	
	@GetMapping 
	public List<Product> getAllProducts() {
	    return productService.getAllProducts();
	}
//	@GetMapping("/products/category")
	@GetMapping("/category")
	public List<Map<String, Object>> getCategoryProducts() {
	    return Arrays.asList(
	        Map.of("name", "product 1", "price", 234),
	        Map.of("name", "product 2", "price", 134)
	    );
	}
	@GetMapping("/name")
	public List<Map<String, Object>> getProductsName() {
	    return Arrays.asList(
	        Map.of("name", "product 1"),
	        Map.of("name", "product 2")
	    );
	}
	
	@GetMapping("/sort")
	public List<Product> sortProducts() {
	    return productService.sortProducts();
	}

}