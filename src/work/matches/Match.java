package work.matches;
import work.Comparable;
import work.WithID;
import work.tournaments.Player;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class Match implements Result<Player>, WithID, Comparable<Match> {

    public static final int MATCH_PLAYER_NUM = 2;
    private static final Random random = new Random();

    private final int ID;
    private final Player[] players;
    private final Optional<Integer> winnerNum;

    public static Match fromRecord(MatchRecord record, Function<Integer, Optional<Player>> IDToPlayer) {
        return new Match(
                record.matchID(),
                new Player[] {
                        IDToPlayer.apply(record.player1ID()).orElse(null),
                        IDToPlayer.apply(record.player2ID())
                },
                record.winnerNum()
        );
    }

    public Match(int ID, Player[] players, Optional<Integer> winnerNum) {
        this.ID = ID;
        this.players = players.length == MATCH_PLAYER_NUM ? players : new Player[MATCH_PLAYER_NUM];
        if (winnerNum.isPresent() && winnerNum.get() > players.length) {
            winnerNum = Optional.empty();
        }
        this.winnerNum = winnerNum;
    }

    @Override
    public Optional<Player> getResult() {
        return winnerNum.map(num -> players[num-1]);
    }

    @Override
    public boolean isTie() {
        return winnerNum.isEmpty();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public boolean isSame(Match other) {
        return this.equals(other) || ID == other.getID();
    }

    public Player[] getPlayers() {
        return players.clone();
    }

}
