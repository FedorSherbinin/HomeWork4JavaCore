package Task2;

import Task2.Exception.AmountException;
import Task2.Exception.CustomerException;
import Task2.Exception.ProductException;

public class Main {
    public static void main(String[] args) {
        String[][] info = {
                {"Ivan", "Ball", "1"}, // корректный заказ
                {"Petr", "Sandwich", "-1"}, // некорректное количество -1
                {"Ivan", "Table", "150"}, // некорректное количество >100
                {"Petr", "Flower", "1"}, // неизвестный товар
                {"Fedor", "Car", "1"}, // неизвестный клиент
        };

        for (String[] orderInfo : info) {
            try {
                Order order = Shop.buy(orderInfo[0], orderInfo[1], Integer.parseInt(orderInfo[2]));
                Shop.addOrder(order);
            } catch (CustomerException | ProductException | AmountException e) {
                System.err.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Cannot add more orders: " + e.getMessage());
            } finally {
                System.out.println("Orders made: " + Shop.getOrderCount());
            }
        }
    }
}
