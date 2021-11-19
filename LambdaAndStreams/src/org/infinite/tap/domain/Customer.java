package org.infinite.tap.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final int tier;

    public Customer(String name, int tier) {
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    public static List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("name" + i, i));
        }

        return customers;
    }
}
