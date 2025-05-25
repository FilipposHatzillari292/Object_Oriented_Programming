package Eshop;

public class BookStore {
    public static void main(String[] args) {
        Book[] books = new Book[5];
        books[0] = new PrintedBook("123-A", "Java Fundamentals", 39.99, "John Doe",
                "Paperback", 500, "Tech Press", 10);
        books[1] = new DigitalBook("234-B", "Learning Python", 19.99, "Jane Smith", 2048);
        books[2] = new AudioBook("345-C", "The Silent Patient", 14.99, "Alex Michaelides", 480, "Tom Hanks");
        books[3] = new PrintedBook("456-D", "Design Patterns", 49.99, "Gamma et al.",
                "Hardcover", 395, "O'Reilly", 5);
        books[4] = new DigitalBook("567-E", "Clean Code", 29.99, "Robert C. Martin", 3000);

        System.out.println("Welcome to the Book Store!\nHere are our books:\n");

        for (Book book : books) {
            book.display();
        }
    }
}
