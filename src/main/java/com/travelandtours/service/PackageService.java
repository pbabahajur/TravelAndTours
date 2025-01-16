package com.travelandtours.service;

import java.util.List;
import com.travelandtours.model.TourPackage;

public interface PackageService {

	void addPackage(TourPackage pkg);

	void deletePackage(int id);

	TourPackage getPackageById(int id);

	void updatePackage(TourPackage pkg);

	List<TourPackage> getAllPackage();

}
