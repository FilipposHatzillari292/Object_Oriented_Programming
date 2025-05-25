package Assingment1;

public class Electronics extends Product {
    private final String brand;
    private final int warrantyPeriod; // in months

    public Electronics(String name, double price, String brand, int warrantyPeriod) {
        super(name, price);
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        if (warrantyPeriod <= 0) {
            throw new IllegalArgumentException("Warranty period must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics: " + name + ", Price: $" + price +
                ", Brand: " + brand + ", Warranty: " + warrantyPeriod + " months");
    }

    @Override
    public void display() {
        displayDetails();
    }


    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
}
