package Task2;

public class Customer {
    private String name;
    private int age;
    private String phone;

    public Customer(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + '\'' + ", age=" + age + ", phone='" + phone + "'}";
    }
}
