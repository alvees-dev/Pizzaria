package br.com.magna.pizzaria.payment;

public enum PaymentMethod {
	
    CASH("DINHEIRO"),
    PIX("PIX"),
    DEBIT_CARD("CARTÃO DÉBITO"),
    CREDIT_CARD("CARTÃO CRÉDITO"),
    MEAL_VOUCHER("VALE REFEIÇÃO"),
    FOOD_VOUCHER("VALE ALIMENTAÇÃO"),
    RETURN_TO_CART("VOLTAR AO CARRINHO"),
    CANCEL_ORDER("CANCELAR COMPRA");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentMethod fromOption(int option) {
    	
        return switch (option) {
            case 1 -> CASH;
            case 2 -> PIX;
            case 3 -> DEBIT_CARD;
            case 4 -> CREDIT_CARD;
            case 5 -> MEAL_VOUCHER;
            case 6 -> FOOD_VOUCHER;
            case 7 -> RETURN_TO_CART;
            case 8 -> CANCEL_ORDER;
            default -> null;
        };
    }
}
