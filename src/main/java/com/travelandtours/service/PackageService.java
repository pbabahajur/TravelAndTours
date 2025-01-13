package com.travelandtours.service;

import java.io.InputStream;
import java.util.List;

import com.travelandtours.model.TourPackage;

import jakarta.servlet.http.Part;

public interface PackageService {
	
	void addPackage(TourPackage pkg);
	void deletePackage(int id);
	TourPackage getPackageById(int id);
	void updatePackage(TourPackage pkg);
	List<TourPackage> getAllPackage();
	
}
