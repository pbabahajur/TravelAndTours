package com.travelandtours.service;



import com.travelandtours.model.User;

public interface UserService {
	
	void userRegister(User usr);
	User userLogin(String email, String password);
	
	

}
