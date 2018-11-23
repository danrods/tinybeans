package controllers;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import models.Payment;
import models.Product;
import play.mvc.Controller;

public class Payments extends Controller{

	public static final String PUBLIC_API_KEY = "pk_test_Gx7rUZoF3zHuIiQTHdFSde8M";
	public static final String API_KEY = "sk_test_HRO6N0bjrFy0ET3TyWODYkOi";
	
	
	private static boolean wasSuccessful(Charge c) {
		return c.getPaid();
	}
	
	private static Charge chargeForProduct(long price, String email, String token) throws StripeException {
		Stripe.apiKey = API_KEY;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", price * 100);
		params.put("currency", "usd");
		params.put("source", token);
		params.put("receipt_email", email);
		
		return Charge.create(params);
	}
	
	private static Payment savePayment(Product p, Charge c, String comments) {
		Payment payment = new Payment();
		payment.paid = c.getAmount();
		payment.product = p;
		payment.comments = comments;
		payment.paymentReference = c.getId();
		return payment.save();
	}
	
	public static void charge(long productId, long price, String stripeEmail, String stripeToken, String comments)  {
		Product p = Product.findById(productId);
		
		if(p != null) {
			try {
				Charge charge = chargeForProduct(price, stripeEmail, stripeToken);
				
				if(! wasSuccessful(charge)) {
					throw new Exception("Payment failed!");
				}
				
				Payment paid = savePayment(p, charge, comments);
				success(paid.getId());
			}
			catch(Exception e) {
				error(e);
			}
			
			return;
		}
		
		error("Could not find product for purchase, try again!");
	}
	
	public static void success(long paymentId) {
		Payment paid = Payment.findById(paymentId);
		
		if(paid != null) {
			System.out.println("Just Made: " + paid.paid + " dollars! --> " + paid.getId());
			
			render(paid);
		}
		else {
			error("There was an issue verifying your payment!");
		}
	}
	
	public static void Index(long productId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("product", p);
//		map.put("api", API_KEY);
		
		Product product = Product.findById(productId);
		
		if(product != null) {
			renderArgs.put("product", product);
			renderArgs.put("api", PUBLIC_API_KEY);
	//		renderArgs.put("productId", product.getId().intValue());
			
			render();
			return;
		}
		
		error("No Product found for ID: " + productId);
	}
	
}
