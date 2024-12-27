package br.com.magna.service;

import br.com.magna.atendimento.CustomerInterface;

public class Main {
	
	public static void main(String[] args) {
		
		CustomerInterface fazerPedido = new CustomerInterface();
		fazerPedido.start();
	}

}
