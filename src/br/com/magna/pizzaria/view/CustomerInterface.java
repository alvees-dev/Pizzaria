// CustomerInterface.java
package br.com.magna.pizzaria.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


import br.com.magna.pizzaria.customer.CustomerRepository;
import br.com.magna.pizzaria.model.Drinks;
import br.com.magna.pizzaria.model.HalfPizza;
import br.com.magna.pizzaria.model.Pizza;
import br.com.magna.pizzaria.service.Order;


public class CustomerInterface {
    private Scanner scan = new Scanner(System.in);
    private Order orderService;
    private CustomerRepository customerRepository;
    private Pizza pizza;
    private Drinks drink;

    public CustomerInterface() {
        this.orderService = new Order();
        this.customerRepository = new CustomerRepository();
        this.pizza = new Pizza("", 0);
        this.drink = new Drinks("", 0);
    }

    private void clear() {
        for (int i = 0; i <= 40; i++) {
            System.out.println();
        }
    }

    private void displayWelcome() {
        System.out.println("Seja Bem-Vindo a Pizzaria Alves\n-------------------------------");

        int mainMenu = 0;
        do {
            System.out.println("\n------- MENU PRINCIPAL --------\n");
            System.out.println("1. Ver cardápio");
            System.out.println("2. Pedido");
            System.out.println("3. Sair\n");
            System.out.println("Digite sua escolha: ");

            try {
                mainMenu = scan.nextInt();
                scan.nextLine();

                if (mainMenu == 1) {
                    pizza.showPizzas();
                    drink.showDrinks();
                } else if (mainMenu == 2) {
                    System.out.println("Para prosseguir com o pedido, é necessário se cadastrar!");
                    customerRepository.adicionaCadastro();
                    break;
                } else if (mainMenu == 3) {
                    System.out.println("Obrigado pelo acesso!");
                    System.exit(0);
                } else {
                    clear();
                    System.out.println("Opção inválida\n");
                }
            } catch (InputMismatchException e) {
                clear();
                System.out.println("Digito inválido. Digite uma opção válida.");
                scan.nextLine();
            }
        } while (true);
    }

    private void chooseItems() {
        boolean itemsChosen = false;

        while (!itemsChosen) {
            displayCart();
            System.out.println("\nO que gostaria de pedir?\n");
            System.out.println("1. Pizza Inteira");
            System.out.println("2. Pizza Meia");
            System.out.println("3. Bebida");
            System.out.println("4. Remover item");
            System.out.println("5. Concluir Pedido");
            System.out.println("6. Cancelar Compra");

            try {
                int option = scan.nextInt();
                scan.nextLine();

                switch (option) {
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
                        if (orderService.getCartService().isEmpty()) {
                            System.out.println("Não há itens para remover");
                        } else {
                            removeItem();
                        }
                        break;
                    case 5:
                        if (!orderService.getCartService().isEmpty()) {
                            itemsChosen = true;
                        } else {
                            System.out.println("Carrinho vazio!");
                        }
                        break;
                    case 6:
                        System.out.println("Até a próxima!");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void selectWholePizza() {
        while (true) {
            pizza.showPizzas();
            System.out.println("\nSelecione a Pizza: ");
            try {
                int choice = scan.nextInt();
                if (choice > 0 && choice <= pizza.listPizza().size()) {
                    orderService.getCartService().addItem(pizza.listPizza().get(choice - 1));
                    break;
                }
                System.out.println("Opção inválida");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void selectHalfPizza() {
        while (true) {
            pizza.showPizzas();
            try {
                System.out.println("Selecione a primeira pizza: ");
                int first = scan.nextInt();
                System.out.println("Selecione a segunda pizza: ");
                int second = scan.nextInt();

                if (first > 0 && first <= pizza.listPizza().size() &&
                    second > 0 && second <= pizza.listPizza().size() &&
                    first != second) {
                    orderService.getCartService().addHalfPizza(
                        pizza.listPizza().get(first - 1),
                        pizza.listPizza().get(second - 1)
                    );
                    break;
                }
                System.out.println("Seleção inválida");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void selectDrink() {
        while (true) {
            drink.showDrinks();
            System.out.println("\nSelecione a bebida: ");
            try {
                int choice = scan.nextInt();
                if (choice > 0 && choice <= drink.listDrinks().size()) {
                    orderService.getCartService().addItem(drink.listDrinks().get(choice - 1));
                    break;
                }
                System.out.println("Opção inválida");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void removeItem() {
        displayCart();
        System.out.println("\nDigite o número do item para remover: ");
        try {
            int index = scan.nextInt() - 1;
            if (index >= 0 && index < orderService.getCartService().getItems().size()) {
                orderService.getCartService().removeItem(index);
            } else {
                System.out.println("Índice inválido");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida");
            scan.nextLine();
        }
    }

    private void displayCart() {
        System.out.println("\n---------- CARRINHO ----------\n");
        var items = orderService.getCartService().getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
            System.out.println("==================================");
        }
        System.out.printf("SubTotal: R$ %.2f%n", orderService.getCartService().getSubTotal());
    }

    private void processPayment() {
        while (true) {
            System.out.println("\n-------- PAGAMENTO --------");
            System.out.println("1. Dinheiro");
            System.out.println("2. Pix");
            System.out.println("3. Cartão Débito");
            System.out.println("4. Cartão Crédito");
            System.out.println("5. Vale Refeição");
            System.out.println("6. Vale Alimentação");
            System.out.println("7. Voltar ao Carrinho");
            System.out.println("8. Cancelar Compra");

            try {
                int option = scan.nextInt();
                String payment = switch (option) {
                    case 1 -> "Dinheiro";
                    case 2 -> "Pix";
                    case 3 -> "Cartão Débito";
                    case 4 -> "Cartão Crédito";
                    case 5 -> "Vale Refeição";
                    case 6 -> "Vale Alimentação";
                    case 7 -> {
                        chooseItems();
                        yield null;
                    }
                    case 8 -> {
                        System.exit(0);
                        yield null;
                    }
                    default -> null;
                };

                if (payment != null) {
                    orderService.setPaymentMethod(payment);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scan.nextLine();
            }
        }
    }

    private void printReceipt() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String now = LocalDateTime.now().format(fmt);

        System.out.println("\n******************RECIBO******************\n");
        System.out.printf("%29s%n", "PIZZARIA ALVES");
        System.out.printf("%31s%n", "RUA DOS TESTE, 123");
        System.out.printf("%27s%n", "SÃO PAULO");
        System.out.printf("%31s%n", "FONE (11)99999-9999");
        System.out.println("CNPJ 99.999.999/0009-99 I.E. 999999999999");
        System.out.println(now + "     CCF:000001   COO:000001");
        System.out.println("------------------------------------------");
        System.out.printf("%28s%n", "CUPOM FISCAL");

        for (Object item : orderService.getCartService().getItems()) {
            if (item instanceof HalfPizza) {
                System.out.println(item);
            } else if (item instanceof Pizza pizza) {
                System.out.printf("%-20s R$ %.2f%n", pizza.getName(), pizza.getPrice());
            } else if (item instanceof Drinks drink) {
                System.out.printf("%-20s R$ %.2f%n", drink.getName(), drink.getPrice());
            }
        }

        System.out.printf("%nTotal: R$ %.2f%n", orderService.getCartService().getSubTotal());
        System.out.println("Forma de Pagamento: " + orderService.getPaymentMethod());
        System.out.println("============================================");
        customerRepository.exibirCadastro();
        System.out.println("============================================");
        System.out.printf("%30s%n", "VOLTE SEMPRE");
    }

    public void start() {
        displayWelcome();
        chooseItems();
        processPayment();
        printReceipt();
    }
}