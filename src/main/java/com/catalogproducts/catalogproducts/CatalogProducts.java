package com.catalogproducts.catalogproducts;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
public class CatalogProducts{

    private final ProductRepository repository;

    public CatalogProducts(ProductRepository repository){
        this.repository = repository;
    }

    public Product addProduct(String name, double price){
        if (name == null || price <= 0){
            throw new IllegalArgumentException("Dado inválidos.");
        }

        Product novo = new Product(name, price);
        return repository.save(novo);
    }

    public List<Product> showCatalog(){
        return repository.findAll();
    }

    public boolean removeProduct(int id){
        if (!repository.existsById(id)){
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public Product findProductId(int id){
        return repository.findById(id).orElse(null);
    }

    public boolean toggleFavorite(int id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("" +
                        "Produto com ID " + id + " não existe."));

        product.setFavorite(!product.isFavorite());

        repository.save(product);

        return product.isFavorite();
    }

    public List<Product> getFavsId(){
        return repository.findByFavoriteTrue();
    }

    public Map<Integer, Product> ordPrice(){

        return repository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toMap(
                        Product::getId,
                        product -> product,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, Product> ordName(){

        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toMap(
                        Product::getId,
                        product -> product,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public List<Product> abovePrice(double price){
        return repository.findByPriceGreaterThan(price);
    }

    public List<Product> belowPrice(double price){
        return repository.findByPriceLessThanEqual(price);
    }

    public List<Product> findProductName(String name){
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }

        return repository.findByNameContainingIgnoreCase(name.trim());
    }

    public boolean removeAsName(String name){
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        List<Product> products = repository.findByNameContainingIgnoreCase(name.trim());

        return  products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .map(product -> {
                    repository.delete(product);
                    return true;
                })
                .orElse(false);
    }

}
