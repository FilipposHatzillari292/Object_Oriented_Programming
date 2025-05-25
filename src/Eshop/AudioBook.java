package Eshop;

public class AudioBook extends Book {
    private final int durationMinutes;
    private final String narrator;

    public AudioBook(String isbn, String title, double price, String author, int durationMinutes, String narrator) {
        super(isbn, title, price, author);
        this.durationMinutes = durationMinutes;
        this.narrator = narrator;
    }

    @Override
    public void display() {
        System.out.println("Audio Book - " + title + " by " + author);
        System.out.println("ISBN: " + isbn + ", Duration: " + durationMinutes + " mins, Narrator: " + narrator);
        System.out.println("Price: $" + price + "\n");
    }
}