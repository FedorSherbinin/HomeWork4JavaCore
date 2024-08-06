package Task2;

import Task2.Exception.AmountException;
import Task2.Exception.CustomerException;
import Task2.Exception.ProductException;

import java.util.Arrays;

public class Shop {
    private final static Customer[] CUSTOMERS = {
            new Customer("Ivan", 20, "+1-222-333-44-55"),
            new Customer("Petr", 30, "+2-333-444-55-66")
    };

    private final static Item[] ITEMS = {
            new Item("Ball", 100),
            new Item("Sandwich", 1000),
            new Item("Table", 10000),
            new Item("Car", 100000),
            new Item("Rocket", 10000000)
    };

    private static Order[] orders = new Order[5];
    private static int orderCount = 0;

    public static boolean isCustomerValid(Customer customer) {
        return Arrays.stream(CUSTOMERS).anyMatch(c -> c.getName().equals(customer.getName()));
    }

    public static boolean isItemValid(Item item) {
        return Arrays.stream(ITEMS).anyMatch(i -> i.getName().equals(item.getName()));
    }

    public static Order buy(String customerName, String itemName, int amount) {
        Customer customer = Arrays.stream(CUSTOMERS)
                .filter(c -> c.getName().equals(customerName))
                .findFirst()
                .orElseThrow(() -> new CustomerException("Unknown customer: " + customerName));

        Item item = Arrays.stream(ITEMS)
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new ProductException("Unknown item: " + itemName));

        if (amount < 0 || amount > 100) {
            throw new AmountException("Incorrect amount: " + amount);
        }

        return new Order(customer, item, amount);
    }

    public static void addOrder(Order order) {
        if (orderCount >= orders.length) {
            throw new ArrayIndexOutOfBoundsException("Order array is full.");
        }
        orders[orderCount++] = order;
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public static Order[] getOrders() {
        return orders;
    }
}
