package com.travelandtours.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gallery")
@Data
public class Gallery {
	// Explicitly specify the table name if needed

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @Lob
	    byte[] photo;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public byte[] getPhoto() {
			return photo;
		}
		public void setPhoto(byte[] photo) {
			this.photo = photo;
		}
}
