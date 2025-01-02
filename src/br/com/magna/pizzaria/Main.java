package br.com.magna.pizzaria;

import br.com.magna.pizzaria.view.CustomerInterface;

public class Main {
	
	public static void main(String[] args) {
		
		CustomerInterface placeOrder = new CustomerInterface();
		placeOrder.start();
	}

}
