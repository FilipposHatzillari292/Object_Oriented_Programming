package AssingmentTesting;

import javax.swing.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private DatabaseManager db;

    public LoginWindow() {
        db = new DatabaseManager();

        setTitle("Hangman - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 130, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 130, 25);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(30, 110, 100, 25);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 110, 100, 25);
        add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());

        setVisible(true);
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        if (db.login(user, pass)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new GameWindow(new User(user, pass, 0), db);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }

    private void register() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        if (db.register(user, pass)) {
            JOptionPane.showMessageDialog(this, "Registered! Now log in.");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. User may already exist.");
        }
    }
}
