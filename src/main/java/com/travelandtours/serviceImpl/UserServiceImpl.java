package com.travelandtours.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.travelandtours.model.User;
import com.travelandtours.repository.UserRepository;
import com.travelandtours.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void userRegister(User usr) {
		userRepo.save(usr);
		
	}

	@Override
	public User userLogin(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
		
	}
}
