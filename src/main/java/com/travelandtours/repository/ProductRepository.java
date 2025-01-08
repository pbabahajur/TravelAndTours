package com.travelandtours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtours.model.TourPackage;

public interface ProductRepository extends JpaRepository<TourPackage, Integer>{

}
