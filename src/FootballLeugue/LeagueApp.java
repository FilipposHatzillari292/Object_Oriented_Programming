package FootballLeugue;

import java.util.Scanner;

public class LeagueApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LeagueManager manager = new LeagueManager();
        int choice;

        do {
            System.out.println("\nFOOTBALL LEAGUE MENU");
            System.out.println("1. Add Game");
            System.out.println("2. Team Performance");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter home team: ");
                    String home = scanner.nextLine();
                    System.out.print("Enter away team: ");
                    String away = scanner.nextLine();
                    System.out.print("Enter home team score: ");
                    int homeScore = scanner.nextInt();
                    System.out.print("Enter away team score: ");
                    int awayScore = scanner.nextInt();
                    scanner.nextLine();
                    Game game = new Game(home, away, homeScore, awayScore);
                    manager.addGame(game);
                    System.out.println("Game added successfully.");
                    break;

                case 2:
                    System.out.print("Enter team name to view performance: ");
                    String team = scanner.nextLine();
                    manager.showTeamPerformance(team);
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
