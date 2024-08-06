package Task2;

public class Item {
    private String name;
    private int cost;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{name='" + name + "', cost=" + cost + "}";
    }
}
