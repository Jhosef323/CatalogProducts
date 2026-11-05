package com.catalogproducts.catalogproducts;

import java.util.Objects;
import jakarta.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column
    private boolean favorite = false;

    public Product(){}

    public Product (String name, double price){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        if(price <= 0){
            throw new IllegalArgumentException("Preço não pode ser 0 ou negativo.");

        }

        this.name = name;
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

    public boolean isFavorite() {return favorite;}

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        this.name = name;
    }

    public void setPrice(double price) {
        if(price <= 0){
            throw new IllegalArgumentException("Preço não pode ser 0 ou negativo.");
        }
        this.price = price;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

}
