package com.travelandtours.controller;


import java.io.File;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.travelandtours.model.TourPackage;

import com.travelandtours.service.PackageService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;




@Controller
@RequestMapping("package")
public class PackageController {
	
	@Autowired
	private PackageService pkgService;
	
	
	@GetMapping("/add")
	public String getPackage() {
		return "PackageAddForm";
	}
	
	//reading id from path variable
	@PostMapping("/add")
	public String postPackage(
	        @RequestParam("image") MultipartFile photoFile, // Explicitly handle the photo upload
	        @ModelAttribute TourPackage tourPkg, // Map the rest of the object
	        Model model) throws IOException {
		//long imgsize = photoFile.getSize()/1024;//size in kg
	    // Validate and convert the photo file to a byte array
	    if (!photoFile.isEmpty()){
	        tourPkg.setPhoto(photoFile.getBytes()); // Set photo bytes to the entity
	    } 
	    	else {
	        model.addAttribute("error", "Photo is required.");
	        return "PackageAddForm";
	    }
	    

	    // Save the TourPackage to the database
	    pkgService.addPackage(tourPkg);

	    // Add success message and redirect
	    model.addAttribute("message", "Package added successfully!");
	    return "redirect:/package/add";
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getPackageImage(@PathVariable int id) {
	    TourPackage tourPackage = pkgService.getPackageById(id);
	    
	    if (tourPackage != null && tourPackage.getPhoto() != null) {
	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG depending on the image type
	                .body(tourPackage.getPhoto());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 if image is not found
	    }
	}
	
	
	@GetMapping("/view")
	public String viewPackage(Model model) {
		
		model.addAttribute("pList", pkgService.getAllPackage());
		
		return "PackageViewForm";
	}
	
	@GetMapping("list")
	public String listPackage(Model model) {
		model.addAttribute("pList", pkgService.getAllPackage());
		
		
		return "PackageListForm";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id) {
		pkgService.deletePackage(id);
		return "redirect:/package/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model) {
		model.addAttribute("pModel", pkgService.getPackageById(id));
	
		return "PackageEditForm";
		
	}
	@PostMapping("/update")
	public String update(@ModelAttribute TourPackage pkg) {
		pkgService.updatePackage(pkg);
		return "redirect:/package/list";
	}
	
}
