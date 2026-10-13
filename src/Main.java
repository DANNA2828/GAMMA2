import work.Leaderboard;
import work.Player;

public class Main {

    public static void main(String[] args) {

        Leaderboard<Player> a = new Leaderboard<>();
        a.addToLeaderboard(new Player(1, "a", 12));
        a.getStandings();

    }
}