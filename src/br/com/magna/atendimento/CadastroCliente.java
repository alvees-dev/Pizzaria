package br.com.magna.atendimento;

import java.util.Scanner;

public class CadastroCliente {

	private String nome = "G";
	private String endereco;

	Scanner scanner = new Scanner(System.in);

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void adicionaCadastro() {

		//loop que verifica se os caracteres digitados são letras
		do {
			System.out.println("\nDigite seu nome: ");
			this.nome = scanner.nextLine();
			
			//condição que verifica se os caracteres digitados são letras
			if (!nome.matches("^[a-zA-Z]+$")) {
				System.out.println("Nome inválido");
			}
			
		} while (!nome.matches("^[a-zA-Z]+$"));

		System.out.println("Digite seu endereço: ");
		this.endereco = scanner.nextLine();
	}

	public void exibirCadastro() {
		System.out.println("Dados do Cliente");
		System.out.println("Nome: " + nome);
		System.out.println("Endereço: " + endereco);
	}
}
