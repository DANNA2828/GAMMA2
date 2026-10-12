package work;

import work.matches.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tournament {

    private static final int MINIMUM_PLAYERS_TO_START = 4;
    private static final int WIN_POINTS = 3;
    private static final int TIE_POINTS = 1;

    private int ID;
    private String name;
    private ArrayList<Player> players;
    private Leaderboard<Player> leaderboard;

    private boolean hasStarted;

    public Tournament(int ID, String name, Player[] players) {
        this.ID = ID;
        this.name = name;
        this.players = new ArrayList<>();
        this.leaderboard = new Leaderboard<>();
        Arrays.stream(players).forEach(this::registerPlayer);
    }

    public boolean registerPlayer(Player player) {
        if (!isRegistered(player) && !hasStarted) {
            players.add(player);
            leaderboard.addToLeaderboard(player);
            return true;
        }
        return false;
    }

    public boolean submitMatch(Match match) {
        if (canSubmitResult(match)) {
            match.getResult().ifPresentOrElse(
                    player -> leaderboard.addPoints(player, WIN_POINTS),
                    () -> Arrays.stream(match.getPlayers()).forEach(player -> leaderboard.addPoints(player, TIE_POINTS))
            );
            match.setSubmitted(true);
            return true;
        }
        return false;
    }

    public boolean start() {
        if (canStart()) {
            hasStarted = true;
            return true;
        }
        return false;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private boolean canStart() {
        return players.size() >= MINIMUM_PLAYERS_TO_START;
    }

    private boolean isRegistered(Player player) {
        for (Player p : players) {
            if (p.equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean canSubmitResult(Match match) {
        for (int i = 0; i < match.getPlayers().length - 1; i++) {
            if (
                    match.getPlayers()[i].equals(match.getPlayers()[i+1])
                    || !isRegistered(match.getPlayers()[i])
                    || !isRegistered(match.getPlayers()[i])
            ) {
                return false;
            }
        }
        return !match.wasSubmitted();
    }

}
