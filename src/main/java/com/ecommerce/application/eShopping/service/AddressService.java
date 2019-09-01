package com.ecommerce.application.eShopping.service;

import com.ecommerce.application.eShopping.entityBeans.Address;

public interface AddressService {
	
	public String saveAddress(Address n);

	public void deleteAddress();

	public void getAddress();

	public void updateAddress();

	public Iterable<Address> findAll();
	
	public Address findbyID(Integer id);
	
}
