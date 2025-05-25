package Eshop;

public abstract class Book {
    protected String isbn;
    protected String title;
    protected double price;
    protected String author;

    public Book(String isbn, String title, double price, String author) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public abstract void display();
}

