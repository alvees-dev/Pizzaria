package br.com.magna.cardapio;

import java.util.ArrayList;
import java.util.List;

public class Bebida {

	private String nome;
	private double preco;

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public Bebida(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public List<Bebida> listaBebida() {
		List<Bebida> bebidas = new ArrayList<>();

		bebidas.add(new Bebida("Agua", 5.50));
		bebidas.add(new Bebida("Coca-cola", 12.00));
		bebidas.add(new Bebida("Fanta", 8.50));
		bebidas.add(new Bebida("Pepsi", 8.50));
		bebidas.add(new Bebida("Suco", 7.00));
		return bebidas;
	}

	public void exibirBebidas() {
		System.out.println("------ BEBIDAS ------\n");
		for (int i = 0; i < listaBebida().size(); i++) {
			System.out.println((i + 1) + ". " + listaBebida().get(i) + "\n");
		}
	}

	@Override
	public String toString() {
		return nome + " 	 |  R$ " + preco;
	}

}
