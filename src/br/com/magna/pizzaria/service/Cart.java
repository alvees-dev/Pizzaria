package br.com.magna.pizzaria.service;

import java.util.ArrayList;
import java.util.List;

import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.HalfPizza;
import br.com.magna.pizzaria.model.Pizza;

public class Cart {
	private List<Object> items;
	private double subTotal;

	public Cart() {
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
}