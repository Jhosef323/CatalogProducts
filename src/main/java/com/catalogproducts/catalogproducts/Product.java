package com.catalogproducts.catalogproducts;

import java.util.Objects;

public class Product {

    private final String name;
    private double price;
    private final int id;

    public Product (String name, double price){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.name = name;
        setPrice(price);
        this.id = IdGenerator.gerarIdProduct();
    }

    public void setPrice(double price) {
        if(price <= 0){
            throw new IllegalArgumentException("Preço não pode ser 0 ou negativo.");
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Product product = (Product) obj;
        return id == product.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId(){return id;}

    @Override
    public String toString(){
        return "Produto: " + this.name
                + ", Preço: " + this.price
                + ", ID: " + this.id;
    }
}
