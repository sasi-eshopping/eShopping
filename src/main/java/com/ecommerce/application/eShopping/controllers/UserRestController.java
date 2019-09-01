package com.ecommerce.application.eShopping.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.application.eShopping.entityBeans.Address;
import com.ecommerce.application.eShopping.exceptions.DBException;
import com.ecommerce.application.eShopping.exceptions.DBException.BadExecution;
import com.ecommerce.application.eShopping.exceptions.DBException.NoData;
import com.ecommerce.application.eShopping.externalService.ProductInfo;
import com.ecommerce.application.eShopping.service.AddressService;
import com.ecommerce.application.eShopping.controllers.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/userinfo") // This means URL's start with /demo (after Application path)
public class UserRestController {

	@Autowired
	AddressService addressService;



	@Autowired
	private ProductInfo productInfo;

	@PostMapping(path = "/addAddress")
	public ResponseEntity<String> addNewUser(@RequestBody Address address) {

		String result = addressService.saveAddress(address);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Address> getAddress() {

		return addressService.findAll();
	}

	@GetMapping(path = "/getAddress/{id}")
	/* @HystrixCommand(fallbackMethod = "getFallbackAdress") */
	
	public ResponseEntity<Address> getAddress(@PathVariable("id") Integer id) throws NoData{

		Address adress = addressService.findbyID(1);
		// RestTemplate rest=new RestTemplate();
		Product prod=new Product();
		
		prod=productInfo.getProduct("http://eShopping-Prod-Service/products/getProduct/"+String.valueOf(id));
		
		System.out.println("Hi I am from Product" + prod.getPrice());
		if (adress == null) {
			throw new DBException.NoData("No row found for id : ");
		}

		return new ResponseEntity<Address>(adress, HttpStatus.OK);

	}
   
    
	
	
	
}