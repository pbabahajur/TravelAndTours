package com.travelandtours.service;

import java.util.List;
import com.travelandtours.model.Cart;

public interface CartService {
	void addPackage(Integer productId, Integer user);
	void deletePackage(int id);
	List<Cart> getAllPackage();
	Cart getPackageById(int id);
	
	
}
