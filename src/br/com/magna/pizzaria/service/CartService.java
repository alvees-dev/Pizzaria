package br.com.magna.pizzaria.service;

import java.util.ArrayList;
import java.util.List;

import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.HalfPizza;
import br.com.magna.pizzaria.model.Pizza;
import br.com.magna.pizzaria.utils.ConsoleUtils;

public class CartService {
	private ConsoleUtils consoleUtils = new ConsoleUtils();
	
	private List<Object> items;
	private double subTotal;

	public CartService() {
		this.items = new ArrayList<>();
		this.subTotal = 0.0;
	}

	public void addItem(Object item) {
		items.add(item);
		calculateSubTotal();
	}

	public void addHalfPizza(Pizza pizza1, Pizza pizza2) {
		HalfPizza halfPizza = new HalfPizza(pizza1, pizza2);
		items.add(halfPizza);
		calculateSubTotal();
	}

	public void removeItem(int index) {
		if (index >= 0 && index < items.size()) {
			items.remove(index);
			calculateSubTotal();
		}
	}

	private void calculateSubTotal() {
		subTotal = 0.0;
		for (Object item : items) {
			if (item instanceof Pizza) {
				subTotal += ((Pizza) item).getPrice();
			} else if (item instanceof Drinks) {
				subTotal += ((Drinks) item).getPrice();
			} else if (item instanceof HalfPizza) {
				subTotal += ((HalfPizza) item).getPrice();
			}
		}
	}
	
	public List<Object> getItems() {
		return items;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public void displayCart() {
		consoleUtils.clear();
	    System.out.println("\n------------ CARRINHO ------------\n");
	    if (items.isEmpty()) {
	        System.out.println("Está tão vazio aqui...");
	    }

	    for (int i = 0; i < items.size(); i++) {
	        System.out.println((i + 1) + ". " + items.get(i));
	        System.out.println("==================================");
	    }
	    System.out.printf("%n..................");
		System.out.printf("%nSubTotal: R$ %.2f%n", getSubTotal());
	}	
	
}