package models;

import java.io.File;
import java.io.FileInputStream;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Payment extends Model {

	@Required
	public Long paid;
	
	@JoinColumn(name = "product", referencedColumnName = "id")
	@OneToOne
	public Product product;

	public String comments;
	
	public String paymentReference;
	
//	public void setPhoto(File f) {
//		this.photo = new Blob();
//		this.photo.set(new FileInputStream(f), type);
//	}
//	
	@Override
	public String toString() {
		return "Payment[" + product.title + " - " + paid + "]";
	}
}
