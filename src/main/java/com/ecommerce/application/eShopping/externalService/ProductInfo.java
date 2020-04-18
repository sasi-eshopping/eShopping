package com.ecommerce.application.eShopping.externalService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
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
	private OAuth2RestTemplate  OAuth2RestTemplate;

	@HystrixCommand(fallbackMethod = "getFallProduct",
			threadPoolKey="productPool",
			threadPoolProperties ={
					
					@HystrixProperty(name = "coreSize", value = "3"),
				    @HystrixProperty(name = "maxQueueSize", value = "2"),
	},
			commandProperties = { 
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
		    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000"),
	})
	

	public Product getProduct(String url,String token) {
		
		//OAuth2RestTemplate.setAccessTokenProvider(accessTokenProvider);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		Product prod = OAuth2RestTemplate.getForObject(url,Product.class,entity);
		System.out.println("aacess toke"+OAuth2RestTemplate.getAccessToken());
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
