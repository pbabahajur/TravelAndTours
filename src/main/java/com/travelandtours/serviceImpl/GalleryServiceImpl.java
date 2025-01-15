package com.travelandtours.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelandtours.model.Cart;
import com.travelandtours.model.Gallery;
import com.travelandtours.repository.GalleryRepository;
import com.travelandtours.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryRepository galleryRepo;
	
	@Override
	public Gallery getPhotoById(int id) {
		// TODO Auto-generated method stub
		return galleryRepo.findById(id).get();
	}

	@Override
	public List<Gallery> getAllPhotos() {
		// TODO Auto-generated method stub
		return galleryRepo.findAll();
	}

	@Override
	public void addPhoto(Gallery glr) {
		galleryRepo.save(glr);
		
	}
}
