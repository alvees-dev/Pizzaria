package br.com.magna.pizzaria.receipt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.magna.pizzaria.customer.CustomerRepository;
import br.com.magna.pizzaria.service.OrderService;
import br.com.magna.pizzaria.utils.ConsoleUtils;

public class ReceiptPrinter {
	private ConsoleUtils consoleUtils = new ConsoleUtils();
    private OrderService orderService;
    private CustomerRepository customerRepository;
    
    public ReceiptPrinter(OrderService orderService, CustomerRepository customerRepository) {
        this.orderService = orderService;
        this.customerRepository = customerRepository;
    }
    
    public void printReceipt() {
        printHeader();
        printOrderDetails();
        printFooter();
    }
    
    private void printHeader() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String now = LocalDateTime.now().format(fmt);
        
        consoleUtils.clear();
        System.out.println("\n******************RECIBO******************\n");
        System.out.printf("%29s%n", "PIZZARIA ALVES");
        System.out.printf("%31s%n", "RUA DOS TESTES, 123");
        System.out.printf("%27s%n", "SÃO PAULO");
        System.out.printf("%31s%n", "FONE (11)99999-9999");
        System.out.println("CNPJ 99.999.999/0009-99 I.E. 999999999999");
        System.out.println(now + "     CCF:000001   COO:000001");
        System.out.println("------------------------------------------");
        System.out.printf("%28s%n", "CUPOM FISCAL");
    }
    
    private void printOrderDetails() {
        orderService.displayOrder();
        System.out.printf("%nTOTAL: R$ %.2f%n", orderService.getCartService().getSubTotal());
        System.out.println(orderService.getPaymentMethod());
        System.out.println("============================================");
    }
    
    private void printFooter() {
        customerRepository.displayCustomer();
        System.out.println("============================================");
        System.out.printf("%30s%n", "VOLTE SEMPRE");
    }
}