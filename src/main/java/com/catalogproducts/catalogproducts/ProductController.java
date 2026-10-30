package com.catalogproducts.catalogproducts;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    // Instancia o catálogo (em um cenário real, deveria estar injetado ou em banco de dados)
    private final CatalogProducts catalog = new CatalogProducts();

    // Rota para listar o Catalogo (GET)
    @GetMapping
    public Map<Integer, Product> getCatalog(){
        return catalog.showCatalog();
    }

    // Rota para Adicionar Produto (POST)
    @PostMapping
    public String addProduct(@RequestBody ProductRequest request){
        catalog.addProduct(request.getName(), request.getPrice());
        return "Produto adicinado com sucesso!";
    }

    // Rota para Favorita/Desfavoritar (POST)
    @PostMapping
    public boolean toggleFavorite(@PathVariable int id){
        return catalog.toggleFavorite(id);
    }

    // Rota para Listar Favoritos
    @GetMapping
    public List<Product> getFavorites(){
        return catalog.getFavsId();
    }
}

// Classe auxiliar para receber o JSON do Bubble
class ProductRequest{
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
