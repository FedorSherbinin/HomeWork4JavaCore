package Task2;

import java.util.ArrayList;
import java.util.List;

// Исключения
class CustomerException extends Exception {
    public CustomerException(String message) {
        super(message);
    }
}

class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }
}

class AmountException extends Exception {
    public AmountException(String message) {
        super(message);
    }
}

// Интерфейсы для товаров и покупателей
interface Validatable {
    boolean isValid();
}

// Класс Покупатель
class Customer implements Validatable {
    private String fullName;
    private int age;
    private String phone;

    public Customer(String fullName, int age, String phone) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isValid() {
        return fullName != null && !fullName.isEmpty() && phone != null && !phone.isEmpty();
    }

    @Override
    public String toString() {
        return "Customer [Full Name: " + fullName + ", Age: " + age + ", Phone: " + phone + "]";
    }
}

// Класс Товар
class Product implements Validatable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean isValid() {
        return name != null && !name.isEmpty() && price > 0;
    }

    @Override
    public String toString() {
        return "Product [Name: " + name + ", Price: " + price + "]";
    }
}

// Класс Заказ
class Order {
    private Customer customer;
    private Product product;
    private int quantity;

    public Order(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order [Customer: " + customer.getFullName() + ", Product: " + product.getName() + ", Quantity: " + quantity + "]";
    }
}

// Основной класс
public class OnlineStore {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        // Инициализация массива покупателей
        customers.add(new Customer("Иван Иванов", 30, "+79160000000"));
        customers.add(new Customer("Мария Петрова", 25, "+79160000001"));

        // Инициализация массива товаров
        products.add(new Product("Ноутбук", 55000));
        products.add(new Product("Телефон", 25000));
        products.add(new Product("Планшет", 20000));
        products.add(new Product("Часы", 15000));
        products.add(new Product("Наушники", 5000));

        // Примеры попыток создания заказов
        try {
            // Корректные заказы
            processPurchase(customers.get(0), products.get(0), 1);  // Успешная покупка
            processPurchase(customers.get(1), products.get(1), 2);  // Успешная покупка

            // Неверное количество
            processPurchase(customers.get(0), products.get(2), -1); // Неверное количество

            // Неверный покупатель (должен вызвать исключение)
            processPurchase(new Customer("Неизвестный покупатель", 0, "0"), products.get(3), 1); // Неверный пользователь

            // Неверный товар
            processPurchase(customers.get(0), new Product("Неизвестный товар", 0), 5); // Неверный товар

        } catch (CustomerException e) {
            System.out.println("Обработка исключения: " + e.getMessage());
            System.exit(1); // Завершение приложения при неверном покупателе
        } catch (ProductException | AmountException e) {
            // Обработка исключений ProductException и AmountException
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Вывод информации о заказах
        System.out.println("Общее количество совершённых покупок: " + orders.size());
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // Метод для обработки покупки и заполнения массива заказов
    private static void processPurchase(Customer customer, Product product, int quantity) throws CustomerException, ProductException, AmountException {
        if (product == null || !product.isValid()) {
            throw new ProductException("Ошибка: неверный товар - " + product);
        }

        if (quantity <= 0 || quantity > 100) {
            if (quantity <= 0) {
                System.out.println("Количество некорректно. Устанавливаем количество в 1.");
                quantity = 1;
            } else {
                throw new AmountException("Количество слишком велико: " + quantity);
            }
        }

        if (customer == null || !customer.isValid()) {
            throw new CustomerException("Неверный покупатель: " + customer);
        }

        orders.add(new Order(customer, product, quantity));
    }
}
