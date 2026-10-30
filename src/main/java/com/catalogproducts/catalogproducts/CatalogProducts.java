package com.catalogproducts.catalogproducts;

import java.util.*;

public class CatalogProducts{
    private final Map<Integer, Product> products = new HashMap<>();
    private final OrdMap<Integer, Product> productOrdMap = new OrdMap<>();
    private final Set<Integer> favId = new HashSet<>();

    public CatalogProducts(){}

    public void addProduct(String name, double price){
        if (name == null || price <= 0){
            throw new IllegalArgumentException("O nome produto não pode ser vazio\nou de preço abaixo/igual a 0.");
        }

        Product novo = new Product(name, price);
        products.put(novo.getId(), novo);
    }

    public boolean removeProduct(int id){
        if (products.isEmpty()){
            throw new IllegalArgumentException("Nenhum produto adicionado.");
        }

        favId.remove(id);
        return products.remove(id) != null;
    }


    public Map<Integer, Product> ordPrice(){
        if (products.isEmpty()) return Collections.emptyMap();
        return productOrdMap.orderMap(products, Comparator.comparingDouble(entry -> entry.getValue().getPrice()));
    }

    public Map<Integer, Product> ordName(){
        if (products.isEmpty()) return Collections.emptyMap();
        return productOrdMap.orderMap(products, Comparator.comparing(entry -> entry.getValue().getName()));
    }

    public List<Product> abovePrice(double price){
        if (products.isEmpty()){
            throw new IllegalArgumentException("Lista vazia.");
        }

        List<Product> result = new ArrayList<>();

        for (Map.Entry<Integer, Product> entry : getProducts().entrySet()) {
            if (entry.getValue().getPrice() > price) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

    public List<Product> belowPrice(double price){
        if (products.isEmpty()){
            throw new IllegalArgumentException("Lista vazia.");
        }

        List<Product> result = new ArrayList<>();

        for (Map.Entry<Integer, Product> entry : getProducts().entrySet()) {
            if (entry.getValue().getPrice() <= price) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

    public boolean toggleFavorite(int id){
        if (products.isEmpty()){
            throw new IllegalArgumentException("Catalogo vazio.");
        }
        if (products.containsKey(id)) {
            if (favId.contains(id)) {
                favId.remove(id);
                return false;
            } else {
                favId.add(id);
                return true;
            }
        }
        throw new IllegalArgumentException("Produto com ID " + id + " não existe no catálogo.");
    }

    public Map<Integer, Product> showCatalog(){
        if (products.isEmpty()){
            return Collections.emptyMap();
        }

        return getProducts();
    }

    public Product findProductId(int id){
        if(id <= 0 || products.isEmpty()){
            return null;
        }

        return products.get(id);
    }

    public boolean removeAsName(String name){
        if (products.isEmpty()){
            throw new IllegalArgumentException("Lista vazia ou nula");
        }
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nome invalido.");
        }

        Iterator<Map.Entry<Integer, Product>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Product> entry = iterator.next();
            Product product = entry.getValue();
            if(product != null && product.getName() != null && product.getName().equalsIgnoreCase(name.trim())){
                iterator.remove();
                favId.remove(product.getId());
                return true;
            }
        }

        return false;
    }


    public List<Product> findProductName(String name){
        if (name == null || name.trim().isEmpty() || products.isEmpty()){
            return Collections.emptyList();
        }

        String lowerName = name.trim().toLowerCase();

        return products.values().stream().
                filter(p -> p != null && p.getName() != null && p.getName()
                        .toLowerCase().contains(lowerName))
                .toList();

    }

    public List<Product> getFavsId() {
        if (favId.isEmpty()){
            return Collections.emptyList();
        }
        List<Product> favs = new ArrayList<>();

        for (int id: favId){
            Product p = products.get(id);
            if(p != null){
                favs.add(p);
            }
        }
        return favs;
    }

    public Map<Integer, Product> getProducts() {
        return Map.copyOf(products);
    }
}
