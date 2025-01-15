package com.travelandtours.service;


import java.util.List;


import com.travelandtours.model.Gallery;
import com.travelandtours.model.TourPackage;

public interface GalleryService {
	
		Gallery getPhotoById(int id);
		List<Gallery> getAllPhotos();
		void addPhoto(Gallery glr);
		
		
	

}
