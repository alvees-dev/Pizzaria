package br.com.magna.pizzaria.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.pizzaria.customer.CustomerRepository;
import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.Pizza;
import br.com.magna.pizzaria.utils.ConsoleUtils;

public class MenuManager {
	
    private Scanner scanner;
    private Pizza pizza;
    private Drinks drink;
    private CustomerRepository customerRepository;
    private ConsoleUtils consoleUtils;
    
    public MenuManager() {
    	
    }
    
    public MenuManager(CustomerRepository customerRepository) {
    	
    	this.consoleUtils = new ConsoleUtils();
        this.scanner = new Scanner(System.in);
        this.pizza = new Pizza();
        this.drink = new Drinks();
        this.customerRepository = customerRepository;
    }
    
    public boolean displayWelcome() {
        System.out.println("Seja Bem-Vindo a Pizzaria Alves\n-------------------------------");
        return showMainMenu();
    }
    
    private boolean showMainMenu() {
    	
        int mainMenu = 0;
        do {
            System.out.println("\n------- MENU PRINCIPAL --------\n");
            System.out.println("1. Ver cardápio");
            System.out.println("2. Pedido");
            System.out.println("3. Sair\n");
            System.out.println("Digite sua escolha: ");

            try {
                mainMenu = scanner.nextInt();
                scanner.nextLine();

                switch (mainMenu) {
                    case 1:
                        showMenu();
                        break;
                    case 2:
                        return handleCustomerRegistration();
                    case 3:
                        System.out.println("Obrigado pelo acesso!");
                        System.exit(0);
                    default:
                        consoleUtils.clear();
                        System.out.println("Opção inválida\n");
                }
            } catch (InputMismatchException e) {
                consoleUtils.clear();
                System.out.println("Digito inválido. Digite uma opção válida.");
                scanner.nextLine();
            }
        } while (true);
    }
    
    private void showMenu() {
        pizza.showPizzas();
        drink.showDrinks();
    }
    
    private boolean handleCustomerRegistration() {
        System.out.println("Para prosseguir com o pedido, é necessário se cadastrar.");
        customerRepository.addCustomer();
        return true;
    }
}