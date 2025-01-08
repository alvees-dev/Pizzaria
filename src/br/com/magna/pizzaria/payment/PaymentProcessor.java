package br.com.magna.pizzaria.payment;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.pizzaria.service.OrderService;
import br.com.magna.pizzaria.utils.ConsoleUtils;

public class PaymentProcessor {
	
    private Scanner scan;
    private OrderService orderService;
    private ConsoleUtils consoleUtils;
    
    public PaymentProcessor() {
    	
    }

    public PaymentProcessor(OrderService orderService) {
    	this.consoleUtils = new ConsoleUtils();
        this.scan = new Scanner(System.in);
        this.orderService = orderService;
    }

    public void processPayment() {
    	
        while (true) {
            displayPaymentOptions();
            try {
                int option = scan.nextInt();
                PaymentMethod paymentMethod = PaymentMethod.fromOption(option);

                if (paymentMethod != null) {
                    if (handlePaymentChoice(paymentMethod)) {
                        return;
                    }
                } else {
                	consoleUtils.clear();
                    System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
            	consoleUtils.clear();
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void displayPaymentOptions() {
    	
        System.out.println("\n----------- PAGAMENTO -----------\n");
        for (PaymentMethod method : PaymentMethod.values()) {
            System.out.printf("%d. %s%n", method.ordinal() + 1, method.getDescription());
        }
    }

    private boolean handlePaymentChoice(PaymentMethod paymentMethod) {
    	
        switch (paymentMethod) {
            case CASH, PIX, DEBIT_CARD, CREDIT_CARD, MEAL_VOUCHER, FOOD_VOUCHER -> {
                orderService.setPaymentMethod(paymentMethod.getDescription());
                return true;
            }
            case RETURN_TO_CART -> {
                System.out.println("Retornando ao carrinho...");
                return true;
            }
            case CANCEL_ORDER -> {
                System.out.println("Compra cancelada!");
                System.exit(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + paymentMethod);
        }
        return false;
    }
}
