package Task3;

public class Main {
    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();
        store.initializeData();
        store.processOrders();
        store.printOrders();
        store.congratulateEmployees();
    }
}
