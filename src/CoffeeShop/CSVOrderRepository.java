package CoffeeShop;

import java.io.*;
import java.util.*;

public class CSVOrderRepository implements OrderRepository {
    private final String fileName = "orders.csv";

    @Override
    public void addOrder(Order order) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(order.toCSV() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        List<Order> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Order order = Order.fromCSV(line);
                if (order.getDate().equals(date)) {
                    result.add(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return result;
    }

    @Override
    public double getTotalAmountByCustomer(String customerName) {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Order order = Order.fromCSV(line);
                if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                    total += order.getAmount();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return total;
    }
}
