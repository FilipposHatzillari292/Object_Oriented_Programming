package Assingment1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Grocery extends Product {
    private final double weight; // in kilograms
    private final LocalDate expirationDate;

    public Grocery(String name, double price, double weight, String expirationDate) {
        super(name, price);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiration date cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        try {
            this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd");
        }

        this.weight = weight;
    }

    @Override
    public void displayDetails() {
        System.out.println("Grocery: " + name + ", Price: $" + price +
                ", Weight: " + weight + "kg, Expiration Date: " + expirationDate);
    }

    @Override
    public void display() {
        displayDetails();
    }

    // Getters
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}

