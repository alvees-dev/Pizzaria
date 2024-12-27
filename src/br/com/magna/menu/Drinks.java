package br.com.magna.menu;

import java.util.ArrayList;
import java.util.List;

public class Drinks {

	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public Drinks(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public List<Drinks> listDrinks() {
		List<Drinks> drinks = new ArrayList<>();

		drinks.add(new Drinks("Agua", 5.50));
		drinks.add(new Drinks("Coca-cola", 12.00));
		drinks.add(new Drinks("Fanta", 8.50));
		drinks.add(new Drinks("Pepsi", 8.50));
		drinks.add(new Drinks("Suco", 7.00));
		return drinks;
	}

	public void showDrinks() {
		System.out.println("------ BEBIDAS ------\n");
		for (int i = 0; i < listDrinks().size(); i++) {
			System.out.println((i + 1) + ". " + listDrinks().get(i) + "\n");
		}
	}

	@Override
    public String toString() {
        return String.format("%-23s | R$ %.2f", name, price);
    }

}
