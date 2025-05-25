package Assingment1;

import java.util.*;

class InventorySystem {
    private static final List<Product> inventory = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inventory System Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Modify Product");
            System.out.println("4. Display All Products");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> searchProduct();
                case 3 -> modifyProduct();
                case 4 -> displayAll();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Choose category (1. Electronics, 2. Clothing, 3. Grocery): ");
        int category = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        switch (category) {
            case 1 -> {
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();
                System.out.print("Enter warranty (months): ");
                int warranty = scanner.nextInt();
                inventory.add(new Electronics(name, price, brand, warranty));
            }
            case 2 -> {
                System.out.print("Enter size: ");
                String size = scanner.nextLine();
                System.out.print("Enter material: ");
                String material = scanner.nextLine();
                System.out.print("Enter color: ");
                String color = scanner.nextLine();
                inventory.add(new Clothing(name, price, size, material, color));
            }
            case 3 -> {
                System.out.print("Enter weight (kg): ");
                double weight = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter expiration date: ");
                String expiry = scanner.nextLine();
                inventory.add(new Grocery(name, price, weight, expiry));
            }
            default -> System.out.println("Invalid category!");
        }
    }

    private static void searchProduct() {
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(searchName)) {
                product.display();
                found = true;
            }
        }

        if (!found) System.out.println("Product not found.");
    }

    private static void modifyProduct() {
        System.out.print("Enter product name to modify: ");
        String name = scanner.nextLine();

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new price: ");
                double newPrice = scanner.nextDouble();
                product.setPrice(newPrice);
                System.out.println("Price updated.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product p : inventory) {
                p.display();
            }
        }
    }
}
