package com.ecommerce.application.eShopping.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.application.eShopping.entityBeans.Addresses;
import com.ecommerce.application.eShopping.repo.AddressRepo;
import com.ecommerce.application.eShopping.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepo addressrepo;
	
	@Override
	public String saveAddress(Addresses address) {
		// TODO Auto-generated method stub

		
		addressrepo.save(address);
		return "Success";
	}

	@Override
	public void deleteAddress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAddress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAddress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Addresses> findAll() {
		// TODO Auto-generated method stub
		Iterable<Addresses> addresses=addressrepo.findAll();
		return addresses;
	}

	@Override
	public Addresses findbyID(Integer id) {
		// TODO Auto-generated method stub
	    Addresses addresses=addressrepo.findById(id).orElse(null);
		return addresses;
	}

}
