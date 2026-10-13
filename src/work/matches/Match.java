package work.matches;
import work.Comparable;
import work.Helpers;
import work.WithID;
import work.changes.OperationType;
import work.tournaments.Player;

import java.util.Arrays;
import java.util.HashMap;
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
                        IDToPlayer.apply(record.player2ID()).orElse(null)
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

    public boolean isWinnerNumSame(Optional<Integer> other) {
        if (winnerNum.isEmpty()) {
            return other.isEmpty();
        } else {
            if (other.isEmpty()) {
                return false;
            } else {
                return winnerNum.get().equals(other.get());
            }
        }

    }

    public Optional<HashMap<Player, Integer>> getScoreChanges(Match other, OperationType operationType, int winPoints, int tiePoints) {
        if (!hasPotentialCorrection(other, operationType)) {
            Optional.empty();
        }

        HashMap<Player, Integer> changes = getScoreChanges(winPoints, tiePoints);
        changes.replaceAll((player, score) -> -changes.get(player));
        return Optional.of(Helpers.add(
                changes, operationType == OperationType.VOID_RESULT ? new HashMap<>() : other.getScoreChanges(winPoints, tiePoints)
        ));
    }

    public HashMap<Player, Integer> getScoreChanges(int winPoints, int tiePoints) {
        HashMap<Player, Integer> changes = new HashMap<>();
        getResult().ifPresentOrElse(
                player -> changes.put(player, winPoints),
                () -> Arrays.stream(players).forEach(player -> changes.put(player, tiePoints))
        );
        return changes;
    }

    public boolean hasPotentialCorrection(Match other, OperationType operationType) {
        return operationType == OperationType.VOID_RESULT && other == null ||
                (ID == other.getID() && Arrays.equals(players, other.getPlayers()) &&
                        (
                                operationType == OperationType.CHANGE_TIE_STATUS && winnerNum.isPresent() != other.getWinnerNum().isPresent()
                                || operationType == OperationType.CHANGE_WINNER && !isWinnerNumSame(other.getWinnerNum())
                        )
                );
    }

    public Player[] getPlayers() {
        return players.clone();
    }

    public Optional<Integer> getWinnerNum() {
        return winnerNum;
    }

}
