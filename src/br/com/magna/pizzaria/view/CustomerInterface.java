// CustomerInterface.java
package br.com.magna.pizzaria.view;

import br.com.magna.pizzaria.customer.CustomerRepository;
import br.com.magna.pizzaria.payment.PaymentProcessor;
import br.com.magna.pizzaria.receipt.ReceiptPrinter;
import br.com.magna.pizzaria.service.OrderService;


public class CustomerInterface {
	
    private MenuManager menuManager;
    private CartManager cartManager;
    private PaymentProcessor paymentProcessor;
    private ReceiptPrinter receiptPrinter;
    
    public CustomerInterface() {
    	
    	CustomerRepository customerRepository = new CustomerRepository();
    	OrderService orderService = new OrderService();
    	
		this.menuManager = new MenuManager(customerRepository);
		this.cartManager = new CartManager(orderService);
		this.paymentProcessor = new PaymentProcessor(orderService);
		this.receiptPrinter = new ReceiptPrinter(orderService, customerRepository);
	}

	public void start() {
		
        menuManager.displayWelcome();
        cartManager.chooseItems();
        paymentProcessor.processPayment();
        receiptPrinter.printReceipt();
    }
}