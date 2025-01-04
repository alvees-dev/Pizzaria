package br.com.magna.pizzaria.service;

public class Order {
    private Cart cartService;
    private String paymentMethod;

    public Order() {
        this.cartService = new Cart();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Cart getCartService() {
        return cartService;
    }
}