package com.travelandtours.serviceImpl;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelandtours.model.Cart;
import com.travelandtours.model.TourPackage;
import com.travelandtours.repository.CartRepository;
import com.travelandtours.repository.TourPackageRepository;

import com.travelandtours.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired 
	private TourPackageRepository tourRepo;
	
	@Override
	public void addPackage(Integer id, Integer user) {
		Cart ct = new Cart();
		TourPackage tp = tourRepo.findById(id).get();
		if(tp != null) {
			Cart cart = new Cart(tp.getPkg_details(), tp.getPkg_name(),tp.getPkg_price(),tp, user, ct.getTotalPrice(), tp.getPhoto());
			cartRepo.save(cart);
		}
	}

	@Override
	public void deletePackage(int id) {
		cartRepo.deleteById(id);
	}

	@Override
	public List<Cart> getAllPackage() {
		
		 return cartRepo.findAll(); 
	}

	@Override
	public Cart getPackageById(int id) {
		// TODO Auto-generated method stub
		return cartRepo.findById(id).get();
	}
}
