package br.com.magna.atendimento;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.magna.cardapio.Bebida;
import br.com.magna.cardapio.MeiaPizza;
import br.com.magna.cardapio.Pizza;

public class Carrinho {

	Scanner scan = new Scanner(System.in);
	protected double precoTotal;
	List<Object> carrinhos;

	public Carrinho() {
		this.carrinhos = new ArrayList<>();
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void adicionarItem(Object item) {
		carrinhos.add(item);

		if (item instanceof Pizza) {
			Pizza pizza = (Pizza) item;
			this.precoTotal += pizza.getPreco();
		} else if (item instanceof Bebida) {
			Bebida bebida = (Bebida) item;
			this.precoTotal += bebida.getPreco();
		}

		System.out.println(item);
	}

	public void adicionarMeiaPizza(Pizza pizza1, Pizza pizza2) {
	    MeiaPizza meiaPizza = new MeiaPizza(pizza1, pizza2);
	    this.precoTotal += meiaPizza.getPreco();
	    carrinhos.add(meiaPizza);
	}
	
	private void removerItem(int opcaoRemoverItem) {
	    opcaoRemoverItem = 0;

	    while (opcaoRemoverItem < this.carrinhos.size() || opcaoRemoverItem > this.carrinhos.size()) {
	        this.exibirCarrinho();
	        System.out.println("\n\nDigite o número do item que deseja remover: ");

	        try {
	            // Recebe opcao digitada
	            opcaoRemoverItem = scan.nextInt();

	            // verifica se a opção digitada esta entre as disponiveis
	            if (opcaoRemoverItem > 0 && opcaoRemoverItem <= this.carrinhos.size()) {
	                // remove o item desejado
	                Object itemRemovido = this.carrinhos.remove(opcaoRemoverItem - 1);
	                
	                // Verifica o tipo do item removido e ajusta o preço total
	                if (itemRemovido instanceof Pizza) {
	                    Pizza pizza = (Pizza) itemRemovido;
	                    this.precoTotal -= pizza.getPreco();
	                } else if (itemRemovido instanceof Bebida) {
	                    Bebida bebida = (Bebida) itemRemovido;
	                    this.precoTotal -= bebida.getPreco();
	                } else if (itemRemovido instanceof MeiaPizza) {
	                    MeiaPizza meiaPizza = (MeiaPizza) itemRemovido;
	                    this.precoTotal -= meiaPizza.getPreco();
	                }

	                System.out.println("\nItem removido: \n" + itemRemovido);
	                break; // Sai do loop se a opção for válida
	            } else {
	                System.out.println("Opção inválido. Por favor, digite um número válido.");
	            }
	        } catch (InputMismatchException e) {
	            //clear();
	            System.out.println("Opção inválida");
	            scan.nextLine();
	        }
	    }
	}


	public void exibirCarrinho() {
	    System.out.println("\n\n------- Carrinho -------");

	    int itens = 0;

	    while (itens < carrinhos.size()) {
	    	
	        Object item = carrinhos.get(itens);

	        if (item instanceof MeiaPizza) {
	            MeiaPizza meiaPizza = (MeiaPizza) item;
	            System.out.println((itens + 1) + ". " + meiaPizza.toString() + " " + meiaPizza.getPreco());
	        } else {
	            System.out.println((itens + 1) + ". " + item);
	        }

	        System.out.println("==================================");
	        itens++;
	    }

	    System.out.printf("%nPreço Total: R$ %.2f", this.precoTotal);
	}

	public void listarCarrinho() {
	    int MeiasPizzas = 0;

	    while (MeiasPizzas < carrinhos.size()) {
	        Object item = carrinhos.get(MeiasPizzas);

	        if (item instanceof MeiaPizza) {
	            MeiaPizza meiaPizza = (MeiaPizza) item;
	            System.out.println((MeiasPizzas + 1) + ". " + meiaPizza.toString() + " | " + meiaPizza.getPreco());
	        } else {
	            System.out.println((MeiasPizzas + 1) + ". " + item);
	        }

	        System.out.println();
	        MeiasPizzas++;
	    }

	    System.out.printf("Preço Total: R$ %.2f", this.precoTotal);
	}
}
