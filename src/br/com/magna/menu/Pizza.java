package br.com.magna.menu;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private String name;
	private double price;

	public Pizza(String taste, double price) {
		this.name = taste;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public List<Pizza> listPizza() {
		List<Pizza> pizzas = new ArrayList<>();

		pizzas.add(new Pizza("Bolonhesa", 42.90));
		pizzas.add(new Pizza("Calabresa", 42.90));
		pizzas.add(new Pizza("Frango c/ Catupiry", 46.90));
		pizzas.add(new Pizza("Mussarela", 39.90));
		pizzas.add(new Pizza("Napolitana", 44.90));
		pizzas.add(new Pizza("Peperone", 42.90));
		pizzas.add(new Pizza("Portuguesa", 39.90));
		pizzas.add(new Pizza("Provolone", 49.90));
		pizzas.add(new Pizza("Quatro Queijos", 44.90));
		pizzas.add(new Pizza("Toscana", 44.90));

		return pizzas;
	}

	public void showPizzas() {
		System.out.println("--------- Pizzas ---------\n");
		for (int i = 0; i < listPizza().size(); i++) {
			System.out.printf("%02d", i + 1);
			System.out.printf(".  " + listPizza().get(i) + "%n%n");

		}
	}

	@Override
    public String toString() {
        return String.format("%n %-20s | R$ %.2f", name, price);
    }
}
