package com.catalogproducts.catalogproducts;

public class IdGenerator {
    private static int proximoIdProduct = 1;
    private static int proximoIdOrder = 1;
    private static int proximoIdCustomer = 1;

    public static int gerarIdProduct(){
        return proximoIdProduct++;
    }

    public static int gerarIdOrder(){
        return proximoIdOrder++;
    }

    public static int gerarProximoIdCustomer() {
        return proximoIdCustomer++;
    }
}

