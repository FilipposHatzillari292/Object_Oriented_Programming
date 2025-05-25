package CoffeeShop;

import java.util.List;
import java.util.Scanner;

public class OrderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for storage choice
        System.out.println("Select storage method:");
        System.out.println("1. CSV File");
        System.out.println("2. MySQL (not implemented here)");
        int storageChoice = scanner.nextInt();
        scanner.nextLine();

        OrderRepository repo;
        if (storageChoice == 1) {
            repo = new CSVOrderRepository();
        } else {
            System.out.println("MySQL storage not implemented in this version.");
            return;
        }

        int choice;
        do {
            System.out.println("\nCoffee Shop Menu:");
            System.out.println("1. Add Order");
            System.out.println("2. View orders of a date");
            System.out.println("3. View total amount of a customer");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Total amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Date (yyyy/mm/dd): ");
                    String date = scanner.nextLine();
                    repo.addOrder(new Order(name, amount, date));
                    System.out.println("Order added.");
                    break;
                case 2:
                    System.out.print("Enter date (yyyy/mm/dd): ");
                    String searchDate = scanner.nextLine();
                    List<Order> orders = repo.getOrdersByDate(searchDate);
                    if (orders.isEmpty()) {
                        System.out.println("No orders on that date.");
                    } else {
                        for (Order o : orders) {
                            System.out.println("- " + o.getCustomerName() + ": $" + o.getAmount());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter customer name: ");
                    String customer = scanner.nextLine();
                    double total = repo.getTotalAmountByCustomer(customer);
                    System.out.println("Total paid by " + customer + ": $" + total);
                    break;
                case 0:
                    System.out.println("Exiting app.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }
}
