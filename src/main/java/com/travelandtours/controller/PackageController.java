package com.travelandtours.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.travelandtours.model.TourPackage;

import com.travelandtours.service.PackageService;

import jakarta.servlet.http.HttpSession;




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
	public String postPackage(@ModelAttribute TourPackage tourPkg,HttpSession session, Model model) throws IOException{
		//setting pathId to current session

		pkgService.addPackage(tourPkg);
		return "PackageAddForm";
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
