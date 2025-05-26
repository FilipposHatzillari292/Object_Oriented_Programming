package Assingment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameWindow extends JFrame {
    private User user;
    private DatabaseManager db;
    private String word;
    private char[] display;
    private int wrongAttempts = 0;
    private JLabel wordLabel, messageLabel;
    private JTextField guessField;
    private JButton guessBtn, replayBtn, logoutBtn, leaderboardBtn;

    public GameWindow(User user, DatabaseManager db) {
        this.user = user;
        this.db = db;
        setupUI();
        startGame();
    }

    private void setupUI() {
        setTitle("Hangman Game");
        setSize(400, 300);
        setLayout(new FlowLayout());

        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(wordLabel);

        messageLabel = new JLabel("Enter a letter:");
        add(messageLabel);

        guessField = new JTextField(1);
        add(guessField);

        guessBtn = new JButton("Guess");
        add(guessBtn);

        replayBtn = new JButton("Play Again");
        replayBtn.setEnabled(false);
        add(replayBtn);

        logoutBtn = new JButton("Logout");
        add(logoutBtn);

        leaderboardBtn = new JButton("Leaderboard");
        add(leaderboardBtn);

        guessBtn.addActionListener(e -> makeGuess());
        replayBtn.addActionListener(e -> startGame());
        logoutBtn.addActionListener(e -> logout());
        leaderboardBtn.addActionListener(e -> new LeaderboardWindow(db));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startGame() {
        word = APIClient.getRandomWord().toLowerCase();
        display = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            display[i] = (i == 0 || i == word.length() - 1) ? word.charAt(i) : '-';
        }
        wrongAttempts = 0;
        updateDisplay();
        guessField.setText("");
        guessField.setEnabled(true);
        guessBtn.setEnabled(true);
        replayBtn.setEnabled(false);
    }

    private void updateDisplay() {
        wordLabel.setText(String.valueOf(display));
    }

    private void makeGuess() {
        String guessText = guessField.getText().toLowerCase();
        if (guessText.length() != 1 || !Character.isLetter(guessText.charAt(0))) {
            JOptionPane.showMessageDialog(this, "Please enter a single letter.");
            return;
        }

        char guess = guessText.charAt(0);
        boolean correct = false;

        for (int i = 1; i < word.length() - 1; i++) {
            if (word.charAt(i) == guess) {
                display[i] = guess;
                correct = true;
            }
        }

        if (!correct) {
            wrongAttempts++;
        }

        updateDisplay();
        checkGameStatus();
        guessField.setText("");
    }

    private void checkGameStatus() {
        if (String.valueOf(display).equals(word)) {
            JOptionPane.showMessageDialog(this, "You won!");
            user.addScore(10);
            db.updateScore(user.getUsername(), 10);
            endGame();
        } else if (wrongAttempts >= 6) {
            JOptionPane.showMessageDialog(this, "You lost! The word was: " + word);
            endGame();
        }
    }

    private void endGame() {
        guessField.setEnabled(false);
        guessBtn.setEnabled(false);
        replayBtn.setEnabled(true);
    }

    private void logout() {
        db.updateScore(user.getUsername(), user.getScore());
        new LoginWindow();
        dispose();
    }
}

