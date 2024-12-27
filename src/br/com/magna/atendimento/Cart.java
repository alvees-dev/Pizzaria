package br.com.magna.atendimento;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.magna.menu.Drinks;
import br.com.magna.menu.HalfPizza;
import br.com.magna.menu.Pizza;

public class Cart {
	
    Scanner scan = new Scanner(System.in);
    protected double subTotal;
    List<Object> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public double getTotalPrice() {
        return subTotal;
    }

    public void addItem(Object item) {
        items.add(item);
        if (item instanceof Pizza) {
            Pizza pizza = (Pizza) item;
            this.subTotal += pizza.getPrice();
        } else if (item instanceof Drinks) {
            Drinks drink = (Drinks) item;
            this.subTotal += drink.getPrice();
        }
        System.out.println(item);
    }

    public void addHalfPizza(Pizza pizza1, Pizza pizza2) {
        HalfPizza halfPizza = new HalfPizza(pizza1, pizza2);
        this.subTotal += halfPizza.getPrice();
        items.add(halfPizza);
    }

    private void removeItem(int removeItemOption) {
        removeItemOption = 0;
        while (removeItemOption < this.items.size() || removeItemOption > this.items.size()) {
            this.displayCart();
            System.out.println("\n\nDigite o número do item que deseja remover: ");
            try {
                removeItemOption = scan.nextInt();
                if (removeItemOption > 0 && removeItemOption <= this.items.size()) {
                    Object removedItem = this.items.remove(removeItemOption - 1);
                    if (removedItem instanceof Pizza) {
                        Pizza pizza = (Pizza) removedItem;
                        this.subTotal -= pizza.getPrice();
                    } else if (removedItem instanceof Drinks) {
                        Drinks drink = (Drinks) removedItem;
                        this.subTotal -= drink.getPrice();
                    } else if (removedItem instanceof HalfPizza) {
                        HalfPizza halfPizza = (HalfPizza) removedItem;
                        this.subTotal -= halfPizza.getPrice();
                    }
                    System.out.println("\nItem removido: \n" + removedItem);
                    break;
                } else {
                    System.out.println("Opção inválida. Digite uma das opções listadas.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção não existe.");
                scan.nextLine();
            }
        }
    }

    public void displayCart() {
        System.out.println("\n\n---------- CARRINHO ----------\n");
        int itemCount = 0;
        while (itemCount < items.size()) {
            Object item = items.get(itemCount);
            if (item instanceof HalfPizza) {
                HalfPizza halfPizza = (HalfPizza) item;
                System.out.printf("%d. %s%n", (itemCount + 1), halfPizza.toString());
            } else {
                System.out.println((itemCount + 1) + ". " + item);
            }
            System.out.println("==================================");
            itemCount++;
        }
        System.out.printf("%nSubTotal: R$ %.2f", this.subTotal);
    }

    public void listCart() {
        for (int i = 0; i < items.size(); i++) {
            Object item = items.get(i);
            if (item instanceof HalfPizza) {
                HalfPizza halfPizza = (HalfPizza) item;
                System.out.printf("%s", halfPizza.getName());
            } else {
                if (item instanceof Pizza) {
                    Pizza pizza = (Pizza) item;
                    System.out.printf("%s %32s", pizza.getName(), pizza.getPrice());
                } else if (item instanceof Drinks) {
                    Drinks drink = (Drinks) item;
                    System.out.printf("%s %36s", drink.getName(), drink.getPrice());
                }
            }
            System.out.println();
        }
        System.out.printf("%nTOTAL: R$ %.2f", this.subTotal);
    }
}