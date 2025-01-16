package com.travelandtours.service;

import java.util.List;
import com.travelandtours.model.Gallery;

public interface GalleryService {

	Gallery getPhotoById(int id);

	List<Gallery> getAllPhotos();

	void addPhoto(Gallery glr);
}
