package br.com.magna.pizzaria.model;

public class HalfPizza {
	
    private Pizza pizza1;
    private Pizza pizza2;

    public HalfPizza(Pizza pizza1, Pizza pizza2) {
        this.pizza1 = pizza1;
        this.pizza2 = pizza2;
    }

    public String getHalfPizza() {
    	return String.format("%n%n%-35s R$ %.2f%n%-20s",
    			"Meia " + pizza1.getName(), 
    			getPrice(), 
    			"Meia " + pizza2.getName());
    }
    
    public double getPrice() {
        return Math.max(pizza1.getPrice(), pizza2.getPrice());
    }
    
    @Override
    public String toString() {
        return String.format("%n%-23s | R$ %.2f\n%-20s", 
            "Meia " + pizza1.getName(),
            getPrice(),
            "Meia " + pizza2.getName());
    }
}

