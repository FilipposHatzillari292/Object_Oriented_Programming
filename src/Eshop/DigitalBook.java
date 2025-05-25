package Eshop;

public class DigitalBook extends Book {
    private final int fileSizeKb;

    public DigitalBook(String isbn, String title, double price, String author, int fileSizeKb) {
        super(isbn, title, price, author);
        this.fileSizeKb = fileSizeKb;
    }

    @Override
    public void display() {
        System.out.println("Digital Book - " + title + " by " + author);
        System.out.println("ISBN: " + isbn + ", Size: " + fileSizeKb + "KB");
        System.out.println("Price: $" + price + "\n");
    }
}