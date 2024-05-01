package br.com.magna.cardapio;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private String nome;
	private double preco;

	public Pizza(String sabor, double preco) {
		this.nome = sabor;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public List<Pizza> listaPizza() {
		List<Pizza> pizzas = new ArrayList<>();

		pizzas.add(new Pizza("Mussarela", 39.90));
		pizzas.add(new Pizza("Portuguesa", 39.90));
		pizzas.add(new Pizza("Toscana", 44.90));
		pizzas.add(new Pizza("Quatro Queijos", 44.90));
		pizzas.add(new Pizza("Bolonhesa", 42.90));
		pizzas.add(new Pizza("Calabresa", 42.90));
		pizzas.add(new Pizza("Frango c/ Catupiry", 46.90));
		pizzas.add(new Pizza("Peperone", 42.90));
		pizzas.add(new Pizza("Provolone", 49.90));
		pizzas.add(new Pizza("Napolitana", 44.90));
		return pizzas;
	}

	public void exibirPizzas() {
		System.out.println("------ Pizzas ------\n");
		for (int i = 0; i < listaPizza().size(); i++) {
			System.out.println((i + 1) + ". " + listaPizza().get(i) + "\n");
		}
	}

	@Override
	public String toString() {
		return nome + "  |  R$ " + preco;
	}
}
