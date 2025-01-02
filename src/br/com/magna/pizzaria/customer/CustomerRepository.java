package br.com.magna.pizzaria.customer;

import java.util.Scanner;

public class CustomerRepository {
    private final Scanner scanner;
    private Customer customer;

    public CustomerRepository() {
        this.scanner = new Scanner(System.in);
        this.customer = new Customer();
    }

    public void adicionaCadastro() {
        cadastrarNome();
        cadastrarEndereco();
    }

    private void cadastrarNome() {
        boolean nomeValido = false;
        
        while (!nomeValido) {
            System.out.println("\nDigite seu nome: ");
            String nome = scanner.nextLine().trim();

            if (validarNome(nome)) {
                customer.setNome(nome);
                nomeValido = true;
            } else {
                System.out.println("Nome inválido. Use apenas letras e espaços.");
            }
        }
    }

    private void cadastrarEndereco() {
        System.out.println("Digite seu endereço: ");
        String endereco = scanner.nextLine().trim();
        customer.setEndereco(endereco);
    }

    private boolean validarNome(String nome) {
        return nome != null && !nome.isEmpty() && nome.matches("^[a-zA-Z ]+$");
    }

    public void exibirCadastro() {
        System.out.printf("%30s%n", "DADOS CLIENTE");
        System.out.println("NOME: " + customer.getNome());
        System.out.println("ENDERECO: " + customer.getEndereco());
    }

    public Customer getCustomer() {
        return customer;
    }
}