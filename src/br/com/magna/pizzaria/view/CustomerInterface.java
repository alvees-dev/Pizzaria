package br.com.magna.pizzaria.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.pizzaria.customer.Customer;
import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.HalfPizza;
import br.com.magna.pizzaria.model.Pizza;
import br.com.magna.pizzaria.service.Cart;

public class CustomerInterface {

	private Pizza pizza;
	private Drinks drink;
	private Customer customerRegistration;
	private Cart cart;
	private Scanner scan;
	private String selectedPaymentOption;

	public CustomerInterface() {
		this.pizza = new Pizza();
		this.drink = new Drinks();
		this.cart = new Cart();
		this.scan = new Scanner(System.in);
		this.customerRegistration = new Customer();
		this.selectedPaymentOption = "";
	}

	private void clear() {
		for (int i = 0; i <= 40; i++) {
			System.out.println();
		}
	}

	private void displayWelcome() {
		System.out.println("""
				Seja Bem-Vindo a Pizzaria Alves
				-------------------------------""");

		int mainMenu = 0;

		do {
			System.out.println("""
					------- MENU PRINCIPAL --------

					1. Ver cardápio
					2. Pedido
					3. Sair
					        """);

			System.out.println("Digite sua escolha: ");

			try {
				mainMenu = scan.nextInt();

				if (mainMenu == 1) {
					pizza.showPizzas();
					drink.showDrinks();
					continue;
				} else if (mainMenu == 2) {
					System.out.println("Para prosseguir com o pedido, é necessário se cadastrar!");
					customerRegistration.adicionaCadastro();
					break;
				} else if (mainMenu == 3) {
					System.out.println("Obrigado pelo acesso!");
					System.exit(mainMenu);
					break;
				} else {
					clear();
					System.out.println("Opção inválida\n");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Digito inválido. Digite uma opção válida.");
				scan.nextLine();
			}

		} while (mainMenu != 2 || mainMenu != 1);
	}

	private void displayMenuOptions() {
		System.out.println("\n\nO que gostaria de pedir?\n\n" + "1. Pizza Inteira\n" + "2. Pizza Meia\n" + "3. Bebida\n"
				+ "4. Remover item\n" + "5. Concluir Pedido\n" + "6. Cancelar Compra");
	}

	private void chooseItems() {
		int orderMenu = 0;
		boolean itemsChosen = false;
		clear();

		while (!itemsChosen) {
			cart.displayCart();
			displayMenuOptions();

			try {
				orderMenu = scan.nextInt();
			} catch (InputMismatchException e) {
				scan.nextLine();
			}

			switch (orderMenu) {
			case 1:
				selectWholePizza();
				break;
			case 2:
				selectHalfPizza();
				break;
			case 3:
				selectDrink();
				break;
			case 4:
				if (cart.items.isEmpty()) {
					clear();
					System.out.println("Não há itens para serem removidos");
				} else {
					clear();
					removeItem();
				}
				break;
			case 5:
				if (cart.items.isEmpty()) {
					clear();
					System.out.println("Não é possível concluir a compra. Carrinho vazio.");
				} else {
					clear();
					itemsChosen = true;
				}
				break;
			case 6:
				System.out.println("Até a próxima! :)");
				System.exit(orderMenu);
				break;
			default:
				clear();
				System.out.println("Opção inválida");
				System.out.println("Selecione novamente: ");
				break;
			}
		}
	}

	private void selectWholePizza() {
		int selectWhole = 0;
		clear();

		do {
			pizza.showPizzas();
			System.out.println("\nSelecione a Pizza que Gostaria de pedir");

			try {
				selectWhole = scan.nextInt();

				if (selectWhole <= 0 || selectWhole >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.printf("Digito inválido.%n");
				System.out.printf("Selecione Novamente: %n%n");
				scan.nextLine();
			}
		} while (selectWhole <= 0 || selectWhole >= 11);
		cart.addItem(pizza.listPizza().get(selectWhole - 1));
		clear();
	}

	private void selectHalfPizza() {
		int selectHalf1 = 0;
		int selectHalf2 = 0;
		clear();

		do {
			pizza.showPizzas();
			try {
				System.out.printf("Selecione a primeira pizza: %n%n");
				selectHalf1 = scan.nextInt();

				if (selectHalf1 <= 0 || selectHalf1 >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
					continue;
				}
				System.out.println(pizza.listPizza().get(selectHalf1 - 1));

				System.out.printf("%n%nSelecione a segunda pizza: %n%n");
				selectHalf2 = scan.nextInt();

				if (selectHalf2 <= 0 || selectHalf2 >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
				}

				cart.addHalfPizza(pizza.listPizza().get(selectHalf1 - 1), pizza.listPizza().get(selectHalf2 - 1));

				if (selectHalf1 == selectHalf2) {
					clear();
					System.out
							.printf("%nAs pizzas selecionadas são iguais. Por favor, selecione pizzas diferentes.%n%n");
				}

			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
			}
		} while (selectHalf1 == selectHalf2);
		clear();
	}

	private void selectDrink() {
		int selectDrink = 0;

		while (selectDrink == 0) {
			drink.showDrinks();
			System.out.println("\nSelecione a bebida: ");
			try {
				selectDrink = scan.nextInt();
				if (selectDrink <= 0 || selectDrink >= 6) {
					drink.showDrinks();
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
					selectDrink = 0;
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
				selectDrink = 0;
			}
		}
		cart.addItem(drink.listDrinks().get(selectDrink - 1));
		clear();
	}

	private void removeItem() {
		int removeItemOption = 0;

		while (removeItemOption < cart.items.size() || removeItemOption > cart.items.size()) {
			cart.displayCart();
			System.out.println("\n\nDigite o número do item que deseja remover: ");

			try {
				removeItemOption = scan.nextInt();

				if (removeItemOption > 0 && removeItemOption <= cart.items.size()) {
					Object removedItem = cart.items.remove(removeItemOption - 1);

					if (removedItem instanceof Pizza) {
						Pizza pizza = (Pizza) removedItem;
						cart.subTotal -= pizza.getPrice();
					} else if (removedItem instanceof Drinks) {
						Drinks drink = (Drinks) removedItem;
						cart.subTotal -= drink.getPrice();
					} else if (removedItem instanceof HalfPizza) {
						HalfPizza halfPizza = (HalfPizza) removedItem;
						cart.subTotal -= halfPizza.getPrice();
					}

					System.out.println("\nItem removido: \n" + removedItem);
					break;
				} else {
					System.out.println("Opção inválido. Por favor, digite um número válido.");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Opção inválida");
				scan.nextLine();
			}
		}
	}

	private void displayPaymentOptions() {
		System.out.println(
				"\n\n-------- PAGAMENTO --------\n\n" + "Qual forma de pagamento?\n" + "1. Dinheiro\n" + "2. Pix\n"
						+ "3. Cartão Débito\n" + "4. Cartão Crédito\n" + "5. Vale Refeição\n" + "6. Vale Alimentação\n"
						+ "---------------------------\n" + "7. Voltar para o Carrinho\n" + "8. Cancelar a compra");
	}

	private void processPayment() {
		int paymentOption = 0;
		selectedPaymentOption = "";

		do {
			System.out.println("Selecione o opção");
			displayPaymentOptions();
			try {
				paymentOption = scan.nextInt();

				if (paymentOption == 1) {
					selectedPaymentOption = "Dinheiro";
					break;
				} else if (paymentOption == 2) {
					selectedPaymentOption = "Pix";
					break;
				} else if (paymentOption == 3) {
					selectedPaymentOption = "Cartão Débito";
					break;
				} else if (paymentOption == 4) {
					selectedPaymentOption = "Cartão Crédito";
					break;
				} else if (paymentOption == 5) {
					selectedPaymentOption = "Vale Refeição";
					break;
				} else if (paymentOption == 6) {
					selectedPaymentOption = "Vale Alimentações";
				} else if (paymentOption == 7) {
					chooseItems();
				} else if (paymentOption == 8) {
					System.exit(paymentOption);
				} else {
					System.out.println("Opção inválida.");
					System.out.println("Selecione novamente: ");
				}

			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
			}

		} while (paymentOption != 0);
	}

	public void printReceipt() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime currentTime = LocalDateTime.now();
		String dateTime = currentTime.format(dateFormat);
		StringBuilder receipt = new StringBuilder();
		clear();

		receipt.append("\n******************RECIBO******************\n\n");
		receipt.append(String.format("%29s", "PIZZARIA ALVES\n"));
		receipt.append(String.format("%31s", "RUA DOS TESTE, 123\n"));
		receipt.append(String.format("%27s", "SÃO PAULO\n\n"));
		receipt.append(String.format("%31s", "FONE (11)99999-9999\n"));
		receipt.append("CNPJ 99.999.999/0009-99 I.E. 999999999999\n");
		receipt.append(dateTime + "     CCF:000001   COO:000001\n");
		receipt.append("------------------------------------------\n");
		receipt.append(String.format("%28s", "CUPOM FISCAL\n"));
		receipt.append(String.format("%-4s %37s%n", "ITEM", "VALOR"));
		System.out.println(receipt.toString());
		cart.listCart();
		System.out.println("\n" + selectedPaymentOption);
		System.out.printf("============================================%n");
		customerRegistration.exibirCadastro();
		System.out.printf("%n============================================%n");
		System.out.printf("%30s", "VOLTE SEMPRE\n\n");
	}

	public void start() {
		displayWelcome();
		chooseItems();
		processPayment();
		printReceipt();
	}
}