package com.travelandtours.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "package_table")
public class TourPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pkg_name;
	private String pkg_details;
	private int pkg_price;
	private int totalOrderPrice;
	
	
	
	
	

}