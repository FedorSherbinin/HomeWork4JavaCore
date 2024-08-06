package Task3;

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
    private Gender gender; // Новое свойство

    public Customer(String fullName, int age, String phone, Gender gender) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.gender = gender; // Инициализация нового свойства
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean isValid() {
        return fullName != null && !fullName.isEmpty() && phone != null && !phone.isEmpty();
    }

    @Override
    public String toString() {
        return "Customer [Full Name: " + fullName + ", Age: " + age + ", Phone: " + phone + ", Gender: " + gender + "]";
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

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        customers.add(new Customer("Иван Иванов", 30, "+79160000000", Gender.MALE));
        customers.add(new Customer("Мария Петрова", 25, "+79160000001", Gender.FEMALE));
        customers.add(new Customer("Алексей Смирнов", 40, "+79160000002", Gender.MALE));
        customers.add(new Customer("Ольга Кузнецова", 35, "+79160000003", Gender.FEMALE));
        customers.add(new Customer("Михаил Попов", 50, "+79160000004", Gender.MALE));
        customers.add(new Customer("Елена Сидорова", 28, "+79160000005", Gender.FEMALE));
        customers.add(new Customer("Дмитрий Волков", 45, "+79160000006", Gender.MALE));
        customers.add(new Customer("Наталья Васильева", 32, "+79160000007", Gender.FEMALE));
        customers.add(new Customer("Петр Иванов", 29, "+79160000008", Gender.MALE));
        customers.add(new Customer("Анна Михайлова", 27, "+79160000009", Gender.FEMALE));

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
            processPurchase(customers.get(2), products.get(2), 3);  // Успешная покупка
            processPurchase(customers.get(3), products.get(3), 1);  // Успешная покупка
            processPurchase(customers.get(4), products.get(4), 1);  // Успешная покупка
            processPurchase(customers.get(5), products.get(0), 2);  // Успешная покупка
            processPurchase(customers.get(6), products.get(1), 1);  // Успешная покупка
            processPurchase(customers.get(7), products.get(2), 5);  // Успешная покупка
            processPurchase(customers.get(8), products.get(3), 4);  // Успешная покупка
            processPurchase(customers.get(9), products.get(4), 3);  // Успешная покупка

            // Неверное количество
            processPurchase(customers.get(0), products.get(2), -1); // Неверное количество

            // Неверный покупатель (должен вызвать исключение)
            processPurchase(new Customer("Неизвестный покупатель", 0, "0", Gender.OTHER), products.get(3), 1); // Неверный пользователь

            // Неверный товар
            processPurchase(customers.get(1), new Product("Неизвестный товар", 0), 5); // Неверный товар

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

        // Поздравление сотрудников
        congratulateEmployees(customers, Holiday.NEW_YEAR, Holiday.MARCH_8, Holiday.FEBRUARY_23);
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

    // Метод для поздравления сотрудников
    private static void congratulateEmployees(List<Customer> employees, Holiday... holidays) {
        for (Customer employee : employees) {
            if (employee.getGender() == Gender.FEMALE && contains(holidays, Holiday.MARCH_8)) {
                System.out.println("Поздравляем " + employee.getFullName() + " с 8 марта!");
            } else if (employee.getGender() == Gender.MALE && contains(holidays, Holiday.FEBRUARY_23)) {
                System.out.println("Поздравляем " + employee.getFullName() + " с 23 февраля!");
            } else if (contains(holidays, Holiday.NEW_YEAR)) {
                System.out.println("Поздравляем " + employee.getFullName() + " с Новым Годом!");
            }
        }
    }

    // Вспомогательный метод для проверки наличия праздника
    private static boolean contains(Holiday[] holidays, Holiday holiday) {
        for (Holiday h : holidays) {
            if (h == holiday) {
                return true;
            }
        }
        return false;
    }
}
