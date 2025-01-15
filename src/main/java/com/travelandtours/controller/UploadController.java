package com.travelandtours.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.travelandtours.model.Gallery;
import com.travelandtours.service.GalleryService;

@Controller
public class UploadController {
	
	@Autowired 
	private GalleryService galleryService;
	
	@GetMapping("/upload")
	public String getUpload() {
		return "UploadForm";
	}
	
	@PostMapping("/upload")
	public String postUpload(@RequestParam("image") MultipartFile image, @ModelAttribute Gallery gallery ,Model model) throws IOException {
		if(!image.isEmpty()) {
			        gallery.setPhoto(image.getBytes()); // Set photo bytes to the entity
			        model.addAttribute("message", "Upload successful!");
			    } 
//			if(imgsize>1024) {
//				model.addAttribute("invalid", "Invalid img size. Max size = 200kb");
//				return "UploadForm";
//			}
//			else {
//				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/"+image.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//				model.addAttribute("message", "Upload Success!");
//				return "UploadForm";
//			}
			
		//}
		
		galleryService.addPhoto(gallery);
		
		return "UploadForm";
	}
	

}
