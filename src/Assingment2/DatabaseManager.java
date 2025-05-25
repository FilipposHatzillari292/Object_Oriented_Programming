package Assingment2;


import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private Connection conn;

    public void connect() throws SQLException {
        conn = DriverManager.getConnection("https://demo.phpmyadmin.net/master-config/public/index.php?route=/database/structure&db=hangman", "root", "password");
    }

    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public boolean registerUser(String username, String password) throws SQLException {
        String check = "SELECT * FROM users WHERE username=?";
        PreparedStatement checkStmt = conn.prepareStatement(check);
        checkStmt.setString(1, username);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) return false;

        String insert = "INSERT INTO users (username, password, score) VALUES (?, ?, 0)";
        PreparedStatement stmt = conn.prepareStatement(insert);
        stmt.setString(1, username);
        stmt.setString(2, password);
        return stmt.executeUpdate() > 0;
    }

    public void updateScore(String username, int score) throws SQLException {
        String update = "UPDATE users SET score = score + ? WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(update);
        stmt.setInt(1, score);
        stmt.setString(2, username);
        stmt.executeUpdate();
    }

    public List<String> getLeaderboard() throws SQLException {
        List<String> list = new ArrayList<>();
        String query = "SELECT username, score FROM users ORDER BY score DESC LIMIT 10";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            list.add(rs.getString("username") + " - " + rs.getInt("score"));
        }
        return list;
    }
}
