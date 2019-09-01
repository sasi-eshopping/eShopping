package com.ecommerce.application.eShopping.externalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.application.eShopping.controllers.Product;
import com.ecommerce.application.eShopping.exceptions.DBException;
import com.ecommerce.application.eShopping.exceptions.DBException.BadExecution;
import com.ecommerce.application.eShopping.exceptions.DBException.NoData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ProductInfo {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallProduct",
			commandProperties = { 
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
		    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000"),
	})
	

	public Product getProduct(String url) {
		Product prod = restTemplate.getForObject(url, Product.class);
		System.out.println("INSIDE ORIGIONAL");
		return prod;
	}

	public Product getFallProduct(String url) {
		/*
        DBException.BadExecution*/
//		System.out.println("INSIDE EXCEPTION");
//		throw new DBException.BadExecution("server down");
		System.out.println("INSIDE FALLBACK");
		return new Product("222", "Pen from Proxy", "12");
	}
}
