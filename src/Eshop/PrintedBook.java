package Eshop;

public class PrintedBook extends Book {
    private final String coverType; // "Hardcover" or "Paperback"
    private final int pages;
    private final String publisher;
    private final int availableCopies;

    public PrintedBook(String isbn, String title, double price, String author,
                       String coverType, int pages, String publisher, int availableCopies) {
        super(isbn, title, price, author);
        this.coverType = coverType;
        this.pages = pages;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
    }

    @Override
    public void display() {
        System.out.println("Printed Book - " + title + " by " + author);
        System.out.println("ISBN: " + isbn + ", Cover: " + coverType + ", Pages: " + pages);
        System.out.println("Publisher: " + publisher + ", Copies: " + availableCopies);
        System.out.println("Price: $" + price + "\n");
    }
}
