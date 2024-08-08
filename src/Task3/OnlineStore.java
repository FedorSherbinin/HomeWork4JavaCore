package Task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private List<Customer> customers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void initializeData() {
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
    }

    public void processOrders() {
        try {
            // Корректные заказы
            addOrder(customers.get(0), products.get(0), 1);  // Успешная покупка
            addOrder(customers.get(1), products.get(1), 2);  // Успешная покупка
            addOrder(customers.get(2), products.get(2), 3);  // Успешная покупка
            addOrder(customers.get(3), products.get(3), 1);  // Успешная покупка
            addOrder(customers.get(4), products.get(4), 1);  // Успешная покупка
            addOrder(customers.get(5), products.get(0), 2);  // Успешная покупка
            addOrder(customers.get(6), products.get(1), 1);  // Успешная покупка
            addOrder(customers.get(7), products.get(2), 5);  // Успешная покупка
            addOrder(customers.get(8), products.get(3), 4);  // Успешная покупка
            addOrder(customers.get(9), products.get(4), 3);  // Успешная покупка

            // Неверное количество
            addOrder(customers.get(0), products.get(2), -1); // Неверное количество

            // Неверный покупатель (должен вызвать исключение)
            addOrder(new Customer("Неизвестный покупатель", 0, "0", Gender.OTHER), products.get(3), 1); // Неверный пользователь

            // Неверный товар
            addOrder(customers.get(1), new Product("Неизвестный товар", 0), 5); // Неверный товар

        } catch (CustomerException e) {
            System.out.println("Обработка исключения: " + e.getMessage());
            System.exit(1); // Завершение приложения при неверном покупателе
        } catch (ProductException | AmountException e) {
            // Обработка исключений ProductException и AmountException
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void addOrder(Customer customer, Product product, int quantity) throws CustomerException, ProductException, AmountException {
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

    public void printOrders() {
        System.out.println("Общее количество совершённых покупок: " + orders.size());
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void congratulateEmployees() {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.getMonthValue() == 1 && currentDate.getDayOfMonth() == 1) {
            congratulateWithHoliday(Holiday.NEW_YEAR);
        } else if (currentDate.getMonthValue() == 3 && currentDate.getDayOfMonth() == 8) {
            congratulateWithHoliday(Holiday.MARCH_8);
        } else if (currentDate.getMonthValue() == 2 && currentDate.getDayOfMonth() == 23) {
            congratulateWithHoliday(Holiday.FEBRUARY_23);
        } else {
            System.out.println("Сегодня нет праздников.");
        }
    }

    private void congratulateWithHoliday(Holiday holiday) {
        for (Customer employee : customers) {
            if (holiday == Holiday.NEW_YEAR) {
                System.out.println("Поздравляем " + employee.getFullName() + " с Новым Годом!");
            } else if (holiday == Holiday.MARCH_8 && employee.getGender() == Gender.FEMALE) {
                System.out.println("Поздравляем " + employee.getFullName() + " с 8 марта!");
            } else if (holiday == Holiday.FEBRUARY_23 && employee.getGender() == Gender.MALE) {
                System.out.println("Поздравляем " + employee.getFullName() + " с 23 февраля!");
            }
        }
    }
}
