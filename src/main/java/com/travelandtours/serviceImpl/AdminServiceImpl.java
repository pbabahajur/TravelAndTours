package com.travelandtours.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.travelandtours.model.Admin;

import com.travelandtours.repository.AdminRepository;
import com.travelandtours.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminRepository adminRepo;
	@Override
	public Admin adminLogin(String username, String password) {
		return adminRepo.findByUsernameAndPassword(username, password);
	}

}
