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

import com.ecommerce.application.eShopping.entityBeans.Addresses;
import com.ecommerce.application.eShopping.exceptions.DBException;
import com.ecommerce.application.eShopping.exceptions.DBException.NoData;
import com.ecommerce.application.eShopping.service.AddressService;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/userinfo") // This means URL's start with /demo (after Application path)
public class UserRestController {

    @Autowired
    AddressService addressService;
    
	@PostMapping(path="/addAddress")
	public ResponseEntity<String>  addNewUser (@RequestBody Addresses address) {
	
		String result=addressService.saveAddress(address);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Addresses> getAddress() {
		
		return addressService.findAll();
	}
	
	@GetMapping(path="/getAddress/{id}")
	public ResponseEntity<Addresses> getAddress(@PathVariable("id") Integer id ) throws NoData  {
	
		Addresses adress=addressService.findbyID(id);
		if(adress == null)
		{
	    throw new DBException.NoData("No row found for id : ");
		}
		
		return new ResponseEntity<Addresses>(adress,HttpStatus.OK);
		
	}
}