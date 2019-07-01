package com.ecommerce.application.eShopping.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.application.eShopping.entityBeans.Addresses;
import com.ecommerce.application.eShopping.repo.AddressRepo;
import com.ecommerce.application.eShopping.service.AddressService;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    AddressService addressService;
    
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Addresses address = new Addresses();
		address.setFullName(name);
		address.setEmail(email);
		
		return addressService.saveAddress(address);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Addresses> getAddress() {
		// This returns a JSON or XML with the users
		return addressService.findAll();
	}
}