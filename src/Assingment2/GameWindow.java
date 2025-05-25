package Assingment2;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private HangmanGame game;
    private JLabel wordLabel, statusLabel;
    private JPanel lettersPanel;
    private String username;
    private DatabaseManager db;

    public GameWindow(String username, DatabaseManager db) {
        this.username = username;
        this.db = db;
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        try {
            String word = WordAPIClient.getRandomWord();
            game = new HangmanGame(word);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        wordLabel = new JLabel(game.getDisplayedWord(), SwingConstants.CENTER);
        statusLabel = new JLabel("Wrong guesses: 0", SwingConstants.CENTER);

        lettersPanel = new JPanel(new GridLayout(3, 9));
        for (char c = 'a'; c <= 'z'; c++) {
            JButton btn = new JButton(String.valueOf(c));
            char finalC=c;
            btn.addActionListener( e -> guessLetter( finalC, btn));
            lettersPanel.add(btn);
        }

        add(wordLabel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(lettersPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void guessLetter(char c, JButton btn) {
        btn.setEnabled(false);
        game.guess(c);
        wordLabel.setText(game.getDisplayedWord());
        statusLabel.setText("Wrong guesses: " + game.wrongGuesses);

        if (game.isGameOver()) {
            String message = game.isWon() ? "You won!" : "You lost!";
            JOptionPane.showMessageDialog(this, message);
            try {
                db.updateScore(username, game.isWon() ? 10 : 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispose();
            new LoginWindow();
        }
    }
}


