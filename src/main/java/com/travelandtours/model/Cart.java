package com.travelandtours.model;

import java.io.File;
import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.servlet.http.Part;

@Entity
@Table(name = "cart") // Explicitly specify the table name if needed
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pkg_details;
    private String pkg_name;
    private int pkg_price;
    private int count;
    private int totalPrice;
    @ManyToOne
    private TourPackage pkg;
    @Lob
    private byte[] photo;
    private Integer userId;

    // Default constructor
    public Cart() {
    }

    // Parameterized constructor
    public Cart(String pkg_details, String pkg_name, int pkg_price, TourPackage pkg, Integer userId, int totalPrice, byte[] photo) {
        this.pkg_details = pkg_details;
        this.pkg_name = pkg_name;
        this.pkg_price = pkg_price;
        this.pkg = pkg;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.photo = photo;
       
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPkg_details() {
        return pkg_details;
    }

    public void setPkg_details(String pkg_details) {
        this.pkg_details = pkg_details;
    }

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public int getPkg_price() {
        return pkg_price;
    }

    public void setPkg_price(int pkg_price) {
        this.pkg_price = pkg_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TourPackage getPkg() {
        return pkg;
    }

    public void setPkg(TourPackage pkg) {
        this.pkg = pkg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
}
