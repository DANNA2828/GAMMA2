import work.tournaments.Leaderboard;
import work.tournaments.Player;

public class Main {

    public static void main(String[] args) {

        Leaderboard<Player> a = new Leaderboard<>();
        a.register(new Player(1, "a", 12));
        a.getStandings();

    }
}