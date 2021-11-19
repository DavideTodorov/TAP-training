package org.infinite.tap.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> products;
    private final LocalDate orderDate;
    private final Status status;

    public Order(List<Product> products, LocalDate orderDate, Status status) {
        this.products = products;
        this.orderDate = orderDate;
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Status getStatus() {
        return status;
    }


    public static List<Order> getAll() {
        List<Order> orders = new ArrayList<>();


        Order order1 = new Order(List.of(new Product("book1", Category.BOOKS, 999999),
                new Product("clothes1", Category.CLOTHES, 30.4)), LocalDate.now(), Status.CANCELED);

        Order order2 = new Order(List.of(new Product("electronics", Category.ELECTRONICS, 88),
                new Product("clothes2", Category.CLOTHES, 130.2)), LocalDate.now(), Status.DELIVERED);

        Order order3 = new Order(List.of(new Product("toys", Category.TOYS, 5.4),
                new Product("clothes4", Category.CLOTHES, 30.4)), LocalDate.now(), Status.PENDING);

        Order order4 = new Order(List.of(new Product("book3", Category.BOOKS, 19),
                new Product("clothes5", Category.CLOTHES, 100.33)), LocalDate.now(), Status.CANCELED);

        Order order5 = new Order(List.of(new Product("toys3", Category.TOYS, 119),
                new Product("electronics5", Category.ELECTRONICS, 88)),
                LocalDate.of(2020, 9, 12), Status.DELIVERED);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        return orders;
    }
}

