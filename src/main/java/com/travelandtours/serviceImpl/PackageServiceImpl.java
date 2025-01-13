package com.travelandtours.serviceImpl;

import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.travelandtours.model.TourPackage;
import com.travelandtours.repository.TourPackageRepository;
import com.travelandtours.service.PackageService;

import jakarta.servlet.http.Part;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	private TourPackageRepository pkgRepo;
	@Override
	public void addPackage(TourPackage pkg) {
		
		pkgRepo.save(pkg);
		
	}

	@Override
	public void deletePackage(int id) {
		pkgRepo.deleteById(id);
		
	}

	@Override
	public TourPackage getPackageById(int id) {
		return pkgRepo.findById(id).get();
	}

	@Override
	public void updatePackage(TourPackage pkg) {
		pkgRepo.save(pkg);
		
	}

	@Override
	public List<TourPackage> getAllPackage() {
		return pkgRepo.findAll();
	}

}
