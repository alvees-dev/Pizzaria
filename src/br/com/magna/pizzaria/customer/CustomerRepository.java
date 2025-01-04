package br.com.magna.pizzaria.customer;

import java.util.Scanner;

public class CustomerRepository {
    private final Scanner scanner;
    private Customer customer;

    public CustomerRepository() {
        this.scanner = new Scanner(System.in);
        this.customer = new Customer();
    }

    public void addCustomer() {
        registerName();
        registerAddress();
    }

    private void registerName() {
        boolean isNameValid = false;
        
        while (!isNameValid) {
            System.out.println("\nDigite seu nome: ");
            String name = scanner.nextLine().trim();

            if (validateName(name)) {
                customer.setNome(name);
                isNameValid = true;
            } else {
                System.out.println("Nome inválido. Use apenas letras e espaços.");
            }
        }
    }

    private void registerAddress() {
        System.out.println("Digite seu endereço: ");
        String address = scanner.nextLine().trim();
        customer.setEndereco(address);
    }

    private boolean validateName(String nome) {
        return nome != null && !nome.isEmpty() && nome.matches("^[a-zA-Z ]+$");
    }

    public void displayCustomer() {
        System.out.printf("%30s%n", "DADOS CLIENTE");
        System.out.println("NOME: " + customer.getNome());
        System.out.println("ENDERECO: " + customer.getEndereco());
    }

    public Customer getCustomer() {
        return customer;
    }
}