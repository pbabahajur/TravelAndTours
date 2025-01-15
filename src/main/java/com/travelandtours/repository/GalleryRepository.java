package com.travelandtours.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtours.model.Gallery;



	public interface GalleryRepository extends JpaRepository<Gallery, Integer>{
		
	}

