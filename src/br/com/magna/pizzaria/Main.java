package br.com.magna.pizzaria;

import br.com.magna.pizzaria.view.CustomerInterface;

public class Main {
	
	public static void main(String[] args) {
		
		CustomerInterface fazerPedido = new CustomerInterface();
		fazerPedido.start();
	}

}