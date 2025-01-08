package br.com.magna.pizzaria.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.Pizza;
import br.com.magna.pizzaria.service.CartService;
import br.com.magna.pizzaria.service.OrderService;
import br.com.magna.pizzaria.utils.ConsoleUtils;

public class CartManager {
	
    private ConsoleUtils consoleUtils = new ConsoleUtils();
    private Scanner scanner;
    private OrderService orderService;
    private Pizza pizza;
    private Drinks drink;
    
    public CartManager(OrderService orderService) {
        this.scanner = new Scanner(System.in);
        this.pizza = new Pizza();
        this.drink = new Drinks();
        this.orderService = orderService;
    }
    
    private CartService getCartService() {
    	return orderService.getCartService();
    	
    }

	public void chooseItems() {
		boolean itemsChosen = false;

		while (!itemsChosen) {
			getCartService().displayCart();
			displayMenu();

			try {
				int option = scanner.nextInt();
				scanner.nextLine();

				itemsChosen = handleMenuOption(option);
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida");
				scanner.nextLine();
			}
		}
	}

	private void displayMenu() {
		System.out.println("\nO que gostaria de pedir?\n");
		System.out.println("1. Pizza Inteira");
		System.out.println("2. Pizza Meia");
		System.out.println("3. Bebida");
		System.out.println("4. Remover item");
		System.out.println("5. Concluir Pedido");
		System.out.println("6. Cancelar Compra");
	}

	private boolean handleMenuOption(int option) {
		return switch (option) {
		case 1 -> {
			selectWholePizza();
			yield false;
		}
		case 2 -> {
			selectHalfPizza();
			yield false;
		}
		case 3 -> {
			selectDrink();
			yield false;
		}
		case 4 -> {
			handleRemoveItem();
			yield false;
		}
		case 5 -> handleOrderCompletion();
		case 6 -> {
			System.out.println("Até a próxima!");
			System.exit(0);
			yield false;
		}
		default -> {
			System.out.println("Opção inválida");
			yield false;
		}
		};
	}

	private void selectWholePizza() {
	    while (true) {
	        consoleUtils.clear();
	        pizza.showPizzas();
	        System.out.println("\nSelecione a Pizza: ");
	        try {
	            int choice = scanner.nextInt();
	            if (choice > 0 && choice <= pizza.listPizza().size()) {
	                getCartService().addItem(pizza.listPizza().get(choice - 1));
	                break;
	            }
	            System.out.println("Opção inválida");
	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida");
	            scanner.nextLine();
	        }
	    }
	}

	private void selectHalfPizza() {
	    while (true) {
	        pizza.showPizzas();
	        try {
	            System.out.println("Selecione a primeira pizza: ");
	            int first = scanner.nextInt();
	            System.out.println("Selecione a segunda pizza: ");
	            int second = scanner.nextInt();

	            if (isValidPizzaSelection(first, second)) {
	                getCartService().addHalfPizza(pizza.listPizza().get(first - 1),
	                        pizza.listPizza().get(second - 1));
	                break;
	            }
	            System.out.println("Seleção inválida");
	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida");
	            scanner.nextLine();
	        }
	    }
	}

	private void selectDrink() {
	    while (true) {
	        consoleUtils.clear();
	        drink.showDrinks();
	        System.out.println("\nSelecione a bebida: ");
	        try {
	            int choice = scanner.nextInt();
	            if (choice > 0 && choice <= drink.listDrinks().size()) {
	                getCartService().addItem(drink.listDrinks().get(choice - 1));
	                break;
	            }
	            System.out.println("Opção inválida");
	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida");
	            scanner.nextLine();
	        }
	    }
	}

	private void handleRemoveItem() {
	    if (getCartService().isEmpty()) {
	        consoleUtils.clear();
	        System.out.println("Não há itens para remover");
	    } else {
	        removeItem();
	    }
	}

	private void removeItem() {
	    getCartService().displayCart();
	    System.out.println("\nDigite o número do item para remover: ");
	    try {
	        int index = scanner.nextInt() - 1;
	        if (index >= 0 && index < getCartService().getItems().size()) {
	            getCartService().removeItem(index);
	        } else {
	            System.out.println("Índice inválido");
	        }
	    } catch (InputMismatchException e) {
	        consoleUtils.clear();
	        System.out.println("Entrada inválida");
	        scanner.nextLine();
	    }
	}
	
	private boolean isValidPizzaSelection(int first, int second) {
	    return first > 0 && first <= pizza.listPizza().size() && 
	           second > 0 && second <= pizza.listPizza().size() && 
	           first != second;
	}

	private boolean handleOrderCompletion() {
	    if (!getCartService().isEmpty()) {
	        return true;
	    } else {
	        consoleUtils.clear();
	        System.out.printf("Carrinho vazio!");
	        return false;
	    }
	}
}