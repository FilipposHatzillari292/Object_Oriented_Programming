package Assingment1;

public class Clothing extends Product {
    private final String size;
    private final String material;
    private final String color;

    public Clothing(String name, double price, String size, String material, String color) {
        super(name, price);
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be null or empty");
        }
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material cannot be null or empty");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.size = size;
        this.material = material;
        this.color = color;
    }

    @Override
    public void displayDetails() {
        System.out.println("Clothing: " + name + ", Price: $" + price +
                ", Size: " + size + ", Material: " + material + ", Color: " + color);
    }

    @Override
    public void display() {
        displayDetails();
    }


    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }
}
