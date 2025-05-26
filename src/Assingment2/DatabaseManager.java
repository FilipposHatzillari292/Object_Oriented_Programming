package Assingment2;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection("http://localhost/phpmyadmin/index.php?route=/sql&pos=0&db=hangman_db&table=users", "root", "your_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void updateScore(String username, int score) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET score = score + ? WHERE username = ?");
            stmt.setInt(1, score);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getLeaderboard() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, score FROM users ORDER BY score DESC LIMIT 10");
            while (rs.next()) {
                list.add(new User(rs.getString("username"), "", rs.getInt("score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
