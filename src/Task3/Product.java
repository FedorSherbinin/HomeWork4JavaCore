package Task3;

public class Product implements Validatable {
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
