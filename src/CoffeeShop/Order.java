package CoffeeShop;

public class Order {
    private final String customerName;
    private final double amount;
    private final String date;

    public Order(String customerName, double amount, String date) {
        this.customerName = customerName;
        this.amount = amount;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String toCSV() {
        return customerName + "," + amount + "," + date;
    }

    public static Order fromCSV(String line) {
        String[] parts = line.split(",");
        return new Order(parts[0], Double.parseDouble(parts[1]), parts[2]);
    }
}
