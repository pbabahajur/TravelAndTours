package com.travelandtours.controller;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelandtours.model.Admin;
import com.travelandtours.model.User;
import com.travelandtours.repository.UserRepository;
import com.travelandtours.serviceImpl.AdminServiceImpl;
import com.travelandtours.serviceImpl.UserServiceImpl;
import com.travelandtours.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller

public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.userRegister(user);
		return "LoginRegisterForm";
		
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, Model model, /*@RequestParam("g-recaptcha-response") String grepCode,*/ HttpSession session) throws IOException {
		
		//if(VerifyRecaptcha.verify(grepCode)) {
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			User usr = userService.userLogin(user.getEmail(), user.getPassword());
			User user1 = userRepository.findByEmail(user.getEmail());
			
			if(usr != null) {
				
				session.setAttribute("validuser", usr);
				int userId = user1.getId();
				session.setAttribute("userId", userId);
				session.setMaxInactiveInterval(200);
				
				return "Home";
				
			}else {
			model.addAttribute("message", "User not found!");
			return "LoginRegisterForm";
			}
		//}
		//model.addAttribute("message", "You are robot.");
		//return "LoginRegisterForm";
		
	
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "LoginRegisterForm";
	}
	
	@GetMapping("/adminLogin")
	public String login() {
		return "adminLogin";
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(@ModelAttribute Admin admin, Model model, /*@RequestParam("g-recaptcha-response") String grepCode,*/ HttpSession session) throws IOException {
		//if(VerifyRecaptcha.verify(grepCode)) {
			Admin adm = adminService.adminLogin(admin.getUsername(), admin.getPassword());
			
			
			if(adm != null) {
				
				session.setAttribute("validuser", adm);
				session.setMaxInactiveInterval(200);
				return "adminHome";
				
			}else {
			model.addAttribute("message", "User not found!");
			return "adminLogin";
			}
		//}
		//model.addAttribute("message", "You are robot.");
		//return "adminLogin";
		
	}	
	}
	
	
	

