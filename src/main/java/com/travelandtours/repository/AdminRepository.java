package com.travelandtours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtours.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Admin findByUsernameAndPassword(String username, String password);
	

}
