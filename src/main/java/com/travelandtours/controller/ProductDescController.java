package com.travelandtours.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelandtours.service.PackageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("description")
public class ProductDescController {
	
	@Autowired
	private PackageService pkgService;
	
	@GetMapping("/view")
	public String getDescription(@RequestParam int id, Model model) {
		model.addAttribute("pModel", pkgService.getPackageById(id));
		return "ProductDescription";
	}
	
	@GetMapping("/add")
	public String addToCart(@RequestParam int id, HttpSession session, Model model, RedirectAttributes redirectAttribute) throws SQLException {
		int sessionId = (int) session.getAttribute("userId");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelandtours", "root", "root1");
		Statement statement = con.createStatement();
		
		String selectQuery = "SELECT * FROM cart WHERE pkg_id = '"+id+"' AND user_id = '"+sessionId+"'";				
		ResultSet resultSet = statement.executeQuery(selectQuery);
		
		if(resultSet.next()) {
			
			 redirectAttribute.addFlashAttribute("itemExists","Sorry! This item is already in cart.");
			 return "redirect:/description/view?id=" + id;
			
		}
		else {
			return "redirect:/cart/add?id=" + id;
		}
		
			
		
	}
}
