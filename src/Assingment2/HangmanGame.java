package Assingment2;

public class HangmanGame {
    private String word;
    private char[] displayed;
    int wrongGuesses;
    private final int MAX_WRONG = 6;

    public HangmanGame(String word) {
        this.word = word;
        this.displayed = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            displayed[i] = (i == 0 || i == word.length() - 1) ? word.charAt(i) : '-';
        }
    }

    public boolean guess(char c) {
        boolean found = false;
        for (int i = 1; i < word.length() - 1; i++) {
            if (word.charAt(i) == c) {
                displayed[i] = c;
                found = true;
            }
        }
        if (!found) wrongGuesses++;
        return found;
    }

    public boolean isGameOver() {
        return wrongGuesses >= MAX_WRONG || new String(displayed).equals(word);
    }

    public String getDisplayedWord() {
        return new String(displayed);
    }

    public boolean isWon() {
        return new String(displayed).equals(word);
    }
}
