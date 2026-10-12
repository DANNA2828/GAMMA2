package work.matches;
import work.Player;
import java.util.Optional;
import java.util.Random;

public class Match implements Result<Player> {

    public static final int MATCH_PLAYER_NUM = 2;
    private static final Random random = new Random();

    private final Player[] players;
    private final Optional<Player> winner;
    private boolean submitted;

    public Match(Player[] players) {
        if (players.length == MATCH_PLAYER_NUM) {
            this.players = players;
        } else {
            this.players = new Player[MATCH_PLAYER_NUM];
            if (players.length > MATCH_PLAYER_NUM) {
                for (int i = 0; i < MATCH_PLAYER_NUM; i++) {
                    this.players[i] = players[i];
                }
            }
        }

        int rand = random.nextInt(0, MATCH_PLAYER_NUM + 1);
        this.winner = rand == MATCH_PLAYER_NUM ? Optional.empty() : Optional.of(players[rand]);
        this.submitted = false;
    }

    @Override
    public Optional<Player> getResult() {
        return winner;
    }

    @Override
    public boolean isTie() {
        return winner.isEmpty();
    }

    public boolean wasSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted || this.submitted;
    }

    public Player[] getPlayers() {
        return players.clone();
    }

}
