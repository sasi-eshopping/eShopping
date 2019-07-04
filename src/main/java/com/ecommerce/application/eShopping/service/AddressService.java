package com.ecommerce.application.eShopping.service;

import com.ecommerce.application.eShopping.entityBeans.Addresses;

public interface AddressService {
	
	public String saveAddress(Addresses n);

	public void deleteAddress();

	public void getAddress();

	public void updateAddress();

	public Iterable<Addresses> findAll();
	
	public Addresses findbyID(Integer id);
	
}
