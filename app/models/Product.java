package models;

import java.io.File;
import java.io.FileInputStream;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Product extends Model {

	@Required
	public String title;
	
	@Lob
	public String description;
	
	@Required
	@Min(0)
	public Double price;

	public Blob photo;
	
	
//	public void setPhoto(File f) {
//		this.photo = new Blob();
//		this.photo.set(new FileInputStream(f), type);
//	}
//	
	@Override
	public String toString() {
		return title + " - " + price;
	}
}
