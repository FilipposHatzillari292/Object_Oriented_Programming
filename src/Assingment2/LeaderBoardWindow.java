package Assingment2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class LeaderboardWindow extends JFrame {
    public LeaderboardWindow(DatabaseManager db) {
        setTitle("Leaderboard");
        setSize(300, 400);
        setLayout(new BorderLayout());

        List<User> users = db.getLeaderboard();
        JTextArea area = new JTextArea();
        area.setEditable(false);

        int rank = 1;
        for (User u : users) {
            area.append(rank++ + ". " + u.getUsername() + " - " + u.getScore() + "\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);
        setVisible(true);
    }
}
