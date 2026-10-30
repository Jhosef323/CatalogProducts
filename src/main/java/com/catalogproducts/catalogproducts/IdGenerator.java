package com.catalogproducts.catalogproducts;

public class IdGenerator {
    private static int proximoIdProduct = 1;
    public static int gerarIdProduct(){
        return proximoIdProduct++;
    }
}

