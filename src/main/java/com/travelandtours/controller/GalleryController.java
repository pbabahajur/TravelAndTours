package com.travelandtours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.travelandtours.model.Gallery;
import com.travelandtours.repository.GalleryRepository;
import com.travelandtours.service.GalleryService;

@Controller
public class GalleryController {

	@Autowired
	private GalleryRepository galleryRepo;
	@Autowired
	private GalleryService galleryService;

	@GetMapping("/photoGallery")
	public String photoGallery(Model model) {
		model.addAttribute("photoList", galleryRepo.findAll());
		return "ProductGallery";
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getPackageImage(@PathVariable int id) {
		Gallery gallery = galleryService.getPhotoById(id);

		if (gallery != null && gallery.getPhoto() != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG depending on the
																			// image type
					.body(gallery.getPhoto());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 if image is not found
		}
	}

}
