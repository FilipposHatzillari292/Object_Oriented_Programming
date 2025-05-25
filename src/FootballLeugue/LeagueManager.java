package FootballLeugue;

import java.util.ArrayList;
import java.util.List;

public class LeagueManager {
    private List<Game> games;

    public LeagueManager() {
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void showTeamPerformance(String teamName) {
        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (Game game : games) {
            String home = game.getHomeTeam();
            String away = game.getAwayTeam();
            int homeScore = game.getHomeScore();
            int awayScore = game.getAwayScore();

            if (teamName.equalsIgnoreCase(home)) {
                if (homeScore > awayScore) wins++;
                else if (homeScore < awayScore) losses++;
                else draws++;
            } else if (teamName.equalsIgnoreCase(away)) {
                if (awayScore > homeScore) wins++;
                else if (awayScore < homeScore) losses++;
                else draws++;
            }
        }

        System.out.println("Performance for " + teamName + ":");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
    }
}
