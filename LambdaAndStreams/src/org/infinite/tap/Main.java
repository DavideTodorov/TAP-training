package org.infinite.tap;

import org.infinite.tap.domain.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Customer> customers = Customer.getAll();
        List<Order> orders = Order.getAll();
        List<Product> products = Product.getAll();

        // 1. Refactor with Streams
        List<Customer> secondTierCustomer = customers
                .stream()
                .filter(c -> c.getTier() == 2)
                .collect(Collectors.toList());

        System.out.println();

        // 2. Find all canceled orders
        List<Order> canceledOrders = orders
                .stream()
                .filter(o -> o.getStatus() == Status.CANCELED)
                .collect(Collectors.toList());

        System.out.println();


        // 3. Find all pending orders created on Monday
        List<Order> pendingOrders = orders
                .stream()
                .filter(o -> o.getStatus() == Status.PENDING &&
                        o.getOrderDate().getDayOfWeek() == DayOfWeek.MONDAY)
                .collect(Collectors.toList());

        System.out.println();

        // 4. Find the names of all tier 1 customers
        List<String> tier1Names = customers
                .stream()
                .filter(c -> c.getTier() == 1)
                .map(Customer::getName)
                .collect(Collectors.toList());

        System.out.println();

        // 5. Find the sum of all TOYS products prices
        double toysSum = products
                .stream()
                .filter(p -> p.getCategory() == Category.TOYS)
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println();

        // 6. Find how many clothes have been ordered
        long countClothes = orders
                .stream()
                .mapToLong(o -> o.getProducts().stream()
                        .filter(p -> p.getCategory() == Category.CLOTHES)
                        .count())
                .sum();

        System.out.println();

        // 7. Find if we have a tier 3 customer named "George"
        boolean hasGeorge = customers
                .stream()
                .anyMatch(c -> c.getTier() == 3 && c.getName().endsWith("George"));

        System.out.println();

        // 8. Find if all orders from September are delivered
        boolean areAllDelivered = orders
                .stream()
                .allMatch(o -> o.getOrderDate().getMonth() == Month.SEPTEMBER &&
                        o.getStatus() == Status.DELIVERED);
        System.out.println();

        // 9. Find the category of the max priced product that have ever been ordered
        Category category = orders
                .stream()
                .sorted((o1, o2) -> {

                    List<Product> products1 = o1.getProducts();
                    Product mostExpensiveProduct1 = products1
                            .stream()
                            .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                            .limit(1)
                            .collect(Collectors.toList()).get(0);


                    List<Product> products2 = o2.getProducts();
                    Product mostExpensiveProduct2 = products2
                            .stream()
                            .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                            .limit(1)
                            .collect(Collectors.toList()).get(0);


                    return Double.compare(mostExpensiveProduct2.getPrice(), mostExpensiveProduct1.getPrice());
                })
                .limit(1)
                .map(o -> o.getProducts().get(0))
                .map(p -> p.getCategory())
                .collect(Collectors.toList()).get(0);

        System.out.println();


        // 10. Sum the prices in each category of products
        Map<Category, Double> pricesSum = products
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)));

        System.out.println();

        // 11. Count all the products with map and reduce operations only
        Integer productCount = products
                .stream()
                .mapToInt(p -> 1)
                .reduce(0, Integer::sum);

        System.out.println();

        // 12. For given number n generate a list of square of each number in [1..n]
        int number = 3;
        List<Integer> squaredNumbers = Stream
                .iterate(1, n -> n + 1)
                .limit(number)
                .map(i -> i * i)
                .collect(Collectors.toList());

        System.out.println();

        // 13. Generate 10 random numbers
        List<Integer> randomNumbers = new Random()
                .ints(10, 0, 100)
                .boxed()
                .collect(Collectors.toList());

        System.out.println();


        // 14. Find all orders containing any electronics
        List<Order> ordersWithElectronics = orders
                .stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory() == Category.ELECTRONICS))
                .collect(Collectors.toList());

        System.out.println();



        //ToDo
        // 15. Implement a collector that concatenates String - StringJoiner implements Collector
        // String names = Customer.getAll().collect(new StringJoiner());


    }

    private static List<Product> initiliazeProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("product1", Category.BOOKS, 10));
        products.add(new Product("product2", Category.TOYS, 12));
        products.add(new Product("product3", Category.ELECTRONICS, 10.66));
        products.add(new Product("product4", Category.CLOTHES, 76));
        products.add(new Product("product5", Category.TOYS, 44));

        return products;
    }

    private static List<Order> initializeOrders() {
        List<Order> orders = new ArrayList<>();


        Order order1 = new Order(List.of(new Product("book1", Category.BOOKS, 999999),
                new Product("clothes1", Category.CLOTHES, 30.4)), LocalDate.now(), Status.CANCELED);

        Order order2 = new Order(List.of(new Product("electronics", Category.ELECTRONICS, 88),
                new Product("clothes2", Category.CLOTHES, 130.2)), LocalDate.now(), Status.DELIVERED);

        Order order3 = new Order(List.of(new Product("toys", Category.TOYS, 5.4),
                new Product("clothes4", Category.CLOTHES, 30.4)), LocalDate.of(2021,11,15), Status.PENDING);

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

    private static List<Customer> initializeCustomer() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("name" + i, i));
        }

        return customers;
    }
}
