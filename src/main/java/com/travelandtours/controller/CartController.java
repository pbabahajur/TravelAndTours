package com.travelandtours.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.travelandtours.model.Cart;
import com.travelandtours.model.TourPackage;
import com.travelandtours.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/view")
	public String viewCart() {
		return "CartListForm";
	}

	@GetMapping("/add")
	public String addToCart(@RequestParam int id, HttpSession session, Model model, RedirectAttributes redirectAttribute) throws SQLException {
		int sessionId = (int) session.getAttribute("userId");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelandtours", "root", "root1");
		Statement statement = con.createStatement();
		
		String selectQuery = "SELECT * FROM cart WHERE pkg_id = '"+id+"' AND user_id = '"+sessionId+"'";				
		ResultSet resultSet = statement.executeQuery(selectQuery);
		
		if(resultSet.next()) {
			
			 redirectAttribute.addFlashAttribute("itemExists","Item already in cart.");
			

	        
		} else {
			int price = 0;
			
	        cartService.addPackage(id, sessionId);
	        String selectQuery1 = "SELECT * FROM package_table WHERE id = '"+id+"'";
	        ResultSet rs = statement.executeQuery(selectQuery1);
	        if(rs.next()) {
	        	price = rs.getInt("pkg_price");
	    		
	        	String insertQuery = "Update cart Set count = 1, total_price = '"+price+"' WHERE pkg_id = '"+id+"' AND user_id = '"+sessionId+"'" ;
	        	
	        	
	        	statement.executeUpdate(insertQuery);
	        }
		}
		con.close();
		statement.close();
		return "redirect:/package/view";
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getCartImage(@PathVariable int id) {
	     Cart cart = cartService.getPackageById(id);
	    
	    if (cart != null && cart.getPhoto() != null) {
	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG depending on the image type
	                .body(cart.getPhoto());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 if image is not found
	    }
	}


	@GetMapping("list")
	public String listPackage(Model model, HttpSession session) throws SQLException {
		List<Cart> cart = cartService.getAllPackage();
		List<Cart> currentUserCart = new ArrayList<>();
		int sessionId = (int) session.getAttribute("userId");
	for (Cart ct : cart) {
		
			if (ct.getUserId() == sessionId) {
				currentUserCart.add(ct);
				
			}
			
	}
	    model.addAttribute("cList", currentUserCart);
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelandtours", "root", "root1");
	    Statement statement = con.createStatement();
	    int totalPrice = 0;
		String selectQuery = "SELECT total_price FROM cart WHERE user_id = '"+sessionId+"'";
        ResultSet resultSet = statement.executeQuery(selectQuery);
        
    	while(resultSet.next()) {
    		totalPrice += resultSet.getInt("total_Price");
    		
    		
    	}
    	model.addAttribute("totalPrice", totalPrice);
		return "CartListForm";
}

	

@GetMapping("/delete")
public String delete(@RequestParam int id) {
	cartService.deletePackage(id);
	return "redirect:/cart/list";
}

@GetMapping("/increase")
public String increaseCount(@RequestParam int id, HttpSession session, Model model) throws SQLException {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelandtours", "root", "root1");
    Statement statement = con.createStatement();
    
    int sessionId = (int) session.getAttribute("userId");
    
    
        String selectQuery = "SELECT * FROM cart WHERE id = '"+id+"'";                
        ResultSet resultSet = statement.executeQuery(selectQuery);
        if(resultSet.next()) {
        		int pkgPrice = (int)resultSet.getInt("pkg_price");
        		int newCount = resultSet.getInt("count")+1;
        		
        		int totalPrice1 = pkgPrice * newCount;
        		
            	String updateQuery = "UPDATE cart SET total_price = '"+totalPrice1+"', count = '"+newCount+"' WHERE id = '"+id+"' AND user_id = '"+sessionId+"'";
            	
            	statement.executeUpdate(updateQuery);	
        }
                     
    con.close();
    statement.close();
    return "redirect:/cart/list"; // Redirect to the cart list to reflect the changes
	
}

@GetMapping("/decrease")
public String decreaseCount(@RequestParam int id, HttpSession session, Model model) throws SQLException {
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelandtours", "root", "root1");
    Statement statement = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
    int sessionId = (int) session.getAttribute("userId");
    
    
        String selectQuery = "SELECT * FROM cart WHERE id = '"+id+"' AND user_id = '"+sessionId+"'";                
        ResultSet resultSet = statement.executeQuery(selectQuery);
           
        
        		if(resultSet.next()) {
        			
        		int newCount = resultSet.getInt("count")-1;
        		if(newCount < 1) {
        			resultSet.deleteRow();
        		}
        		else {
          			
          			int pkgPrice = (int)resultSet.getInt("pkg_price");
            		
            	    int totalPrice = pkgPrice * newCount;
                    String updateQuery = "UPDATE cart SET total_price = '"+totalPrice+"',count = '"+newCount+"' WHERE id = '"+id+"' AND user_id = '"+sessionId+"'";
                          statement.executeUpdate(updateQuery);
        		}
        		
          		}
        				
        
        		
        
    return "redirect:/cart/list"; // Redirect to the cart list to reflect the changes
	
}
}

