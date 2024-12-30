package br.com.magna.pizzaria.model;

public class HalfPizza {
	
    private Pizza pizza1;
    private Pizza pizza2;

    public HalfPizza(Pizza pizza1, Pizza pizza2) {
        this.pizza1 = pizza1;
        this.pizza2 = pizza2;
    }

    public double getPrice() {
        return Math.max(pizza1.getPrice(), pizza2.getPrice());
    }
    
    public String getName() {
    	return String.format("%-20s %.2f%n%-20s",
    			"Meia " + pizza1.getName(), 
    			getPrice(), 
    			"Meia " + pizza2.getName());
    	//return String.format("Meia " + pizza1.getName() + " Meia " + pizza2.getName());
    }

    @Override
    public String toString() {
        return String.format("%n %-20s | R$ %.2f\n%-20s", 
            "Meia " + pizza1.getName(),
            getPrice(),
            "Meia " + pizza2.getName());
    }
}

