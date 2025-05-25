package Assingment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseManager db = new DatabaseManager();

    public LoginWindow() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(new JLabel("Username"));
        add(usernameField);
        add(new JLabel("Password"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());

        try {
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    private void login() {
        try {
            if (db.loginUser(usernameField.getText(), new String(passwordField.getPassword()))) {
                dispose();
                new GameWindow(usernameField.getText(), db);
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register() {
        try {
            if (db.registerUser(usernameField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Registration successful");
            } else {
                JOptionPane.showMessageDialog(this, "Username taken");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
