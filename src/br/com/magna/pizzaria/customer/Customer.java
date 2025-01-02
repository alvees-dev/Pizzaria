package br.com.magna.pizzaria.customer;

import java.util.Scanner;

public class Customer {

	private String nome = "G";
	private String endereco;

	Scanner scanner = new Scanner(System.in);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void adicionaCadastro() {

		// loop que verifica se os caracteres digitados são letras
		do {
			System.out.println("\nDigite seu nome: ");
			this.nome = scanner.nextLine();

			// condição que verifica se os caracteres digitados são letras
			if (!nome.matches("^[a-zA-Z ]+$")) {
				System.out.println("Nome inválido");
			}

		} while (!nome.matches("^[a-zA-Z ]+$"));

		System.out.println("Digite seu endereço: ");
		this.endereco = scanner.nextLine();
	}

	public void exibirCadastro() {
		System.out.printf("%30s", "DADOS CLIENTE\n");
		System.out.println("NOME: " + this.nome);
		System.out.println("ENDERECO: " + this.endereco);
	}
}
