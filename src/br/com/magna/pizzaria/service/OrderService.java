package br.com.magna.pizzaria.service;

import java.util.List;

import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.HalfPizza;
import br.com.magna.pizzaria.model.Pizza;

public class OrderService {
	private CartService cartService;
	private String paymentMethod;

	public OrderService() {
		this.cartService = new CartService();
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void displayOrder() {
		
		List<Object> items = cartService.getItems();
		
		for (int i = 0; i < items.size(); i++) {
	        Object item = items.get(i);
	        
	        if (item instanceof HalfPizza halfPizza) {
	            System.out.printf("%-40s R$ %.2f%n", 
	                halfPizza.getName(), 
	                halfPizza.getPrice());
	        } 
	        else if (item instanceof Pizza pizza) {
	            System.out.printf("%-40s R$ %.2f%n", 
	                pizza.getName(), 
	                pizza.getPrice());
	        } 
	        else if (item instanceof Drinks drink) {
	            System.out.printf("%-40s R$ %.2f%n", 
	                drink.getName(), 
	                drink.getPrice());
	        }
	    }
	
	}
}