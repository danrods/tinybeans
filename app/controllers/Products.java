package controllers;

import play.*;
import play.data.validation.Valid;
import play.db.jpa.Blob;
import play.db.jpa.JPA;
import play.mvc.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import models.*;

public class Products extends Controller {

	public static void newProduct() {
		render();
	}
	
	public static void renderPhoto(Long id) {
		Product p = Product.findById(id);
		
		if(p != null && p.photo != null && p.photo.exists()) {
			Blob photo = p.photo;
			response.setContentTypeIfNotSet(photo.type());
			renderBinary(photo.get());
		}
		else {
			response.setContentTypeIfNotSet("text");
			renderBinary(new File("public/images/defaultProduct.jpg"));
		}
		
	}
	
	public static void addProduct(@Valid Product p) {
		System.out.println("!!! Adding Product: " + p);
		
		if(validation.hasErrors()) {
	       params.flash(); // add http parameters to the flash scope
	       validation.keep(); // keep the errors for the next request
	       newProduct();
	       return;
	    }
		
		Product saved = p.save();
		System.out.println("Successfully saved product: " + saved);
		index();
	}
	
	public static void purchase(long productId) {
//		Product p = Product.findById(productId);
//		
//		if(p != null) {
//			Payments.Index(p);
//			return;
//		}
		Payments.Index(productId);
		
//		error("No Product found for ID: " + productId);
	}
	
    public static void index() {
    		List<Product> products = Product.all().fetch(20);
        render(products);
    }

}