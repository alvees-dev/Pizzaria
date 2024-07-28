package br.com.magna.cardapio;

public class MeiaPizza {
	
    private Pizza pizza1;
    private Pizza pizza2;

    public MeiaPizza(Pizza pizza1, Pizza pizza2) {
        this.pizza1 = pizza1;
        this.pizza2 = pizza2;
    }

    public double getPreco() {
        return Math.max(pizza1.getPreco(), pizza2.getPreco());
    }
    
    public String getName() {
    	return "Meia " + pizza1.getNome() + " Meia " + pizza2.getNome();
    }

    @Override
    public String toString() {
        return "Meia " + pizza1.getNome() + " Meia " + pizza2.getNome() + " | R$";
    }
}

