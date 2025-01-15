package com.travelandtours.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

	@Controller
	public class Home {
		
		@GetMapping("/adminHome")
		public String getAdminHome() {
			return "adminHome";
		}
		
		@GetMapping("/userHome")
		public String getUserHome() {
			return "Home";
		}
}
