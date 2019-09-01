package com.ecommerce.application.eShopping.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.application.eShopping.entityBeans.Address;
import com.ecommerce.application.eShopping.repo.AddressRepo;
import com.ecommerce.application.eShopping.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepo addressrepo;
	
	@Override
	public String saveAddress(Address address) {
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
	public Iterable<Address> findAll() {
		// TODO Auto-generated method stub
		Iterable<Address> addresses=addressrepo.findAll();
		return addresses;
	}

	@Override
	public Address findbyID(Integer id) {
		// TODO Auto-generated method stub
	    Address addresses=addressrepo.findById(id).orElse(null);
		return addresses;
	}

}
