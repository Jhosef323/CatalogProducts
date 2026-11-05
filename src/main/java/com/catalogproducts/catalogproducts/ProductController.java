package com.catalogproducts.catalogproducts;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final CatalogProducts catalog;

    public ProductController(CatalogProducts catalog){
        this.catalog = catalog;
    }

    @GetMapping
    public List<Product> getCatalog(){
        return catalog.showCatalog();
    }

    // Rota para Adicionar Produto (POST)
    @PostMapping
    public Product addProduct(@RequestBody ProductRequest request){
        return catalog.addProduct(request.getName(),
                request.getPrice()
        );
    }

    @PostMapping("/{id}/favorite")
    public boolean toggleFavorite(@PathVariable int id){

        return catalog.toggleFavorite(id);
    }

    @GetMapping("/favorites")
    public List<Product> getFavorites(){
        return catalog.getFavsId();
    }

    @GetMapping({"/{id}"})
    public Product findProduct(@PathVariable int id){
        return catalog.findProductId(id);
    }

    @DeleteMapping("/{id}")
    public boolean removeProduct(@PathVariable int id){
        return catalog.removeProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String name){
        return catalog.findProductName(name);
    }

    @GetMapping("/sort/price")
    public Map<Integer, Product> sortPrice(){
        return catalog.ordPrice();
    }

    @GetMapping("/sort/name")
    public Map<Integer, Product> sortName(){
        return catalog.ordName();
    }
}

