package org.infinite.tap.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private final String name;
    private final Category category;
    private final double price;

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }


    public static List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("product1", Category.BOOKS, 10));
        products.add(new Product("product2", Category.TOYS, 12));
        products.add(new Product("product3", Category.ELECTRONICS, 10.66));
        products.add(new Product("product4", Category.CLOTHES, 76));
        products.add(new Product("product5", Category.TOYS, 44));

        return products;
    }
}

