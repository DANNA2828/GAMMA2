package work.tournaments;

import work.IDList;
import work.matches.Match;
import work.matches.MatchRecord;

import java.util.Arrays;

public class Tournament {

    private static final int MINIMUM_PLAYERS_TO_START = 4;

    private final int ID;
    private final String name;
    private final IDList<Player> players;
    private final IDList<Match> matches;
    private final Leaderboard<Player> leaderboard;
    private final int winPoints;
    private final int tiePoints;

    private boolean hasStarted;

    public Tournament(int ID, String name, Player[] players, TournamentType type) {
        this.ID = ID;
        this.name = name;
        this.players = new IDList<>(players);
        this.matches = new IDList<>();
        this.leaderboard = new Leaderboard<>();
        this.winPoints = type.getWinPoints();
        this.tiePoints = type.getTiePoints();

        Arrays.stream(players).forEach(this::registerPlayer);
    }

    public void registerPlayer(Player player) {
        if (!players.isRegistered(player) && !hasStarted) {
            players.register(player);
            leaderboard.addToLeaderboard(player);
        }
    }

    public void submit(Match match) {
        if (canSubmitResult(match)) {
            match.getResult().ifPresentOrElse(
                    player -> leaderboard.addPoints(player, winPoints),
                    () -> Arrays.stream(match.getPlayers()).forEach(player -> leaderboard.addPoints(player, tiePoints))
            );
            matches.register(match);
        }
    }

    public void submit(MatchRecord[] records) {
        for (MatchRecord record : records) {
            submit(record);
        }
    }

    public void start() {
        if (canStart()) {
            hasStarted = true;
        }
    }

    private boolean canStart() {
        return players.getSize() >= MINIMUM_PLAYERS_TO_START && !hasStarted;
    }

    private boolean canSubmitResult(Match match) {
        for (int i = 0; i < match.getPlayers().length - 1; i++) {
            if (
                    match.getPlayers()[i].isSame(match.getPlayers()[i+1])
                    || !players.isRegistered(match.getPlayers()[i])
                    || !players.isRegistered(match.getPlayers()[i+1])
            ) {
                return false;
            }
        }
        return !matches.isRegistered(match);
    }

    private boolean isTournamentIDCorrect(MatchRecord record) {
        return record.TournamentID() == ID;
    }

    private void submit(MatchRecord record) {
        Match match = Match.fromRecord(record, players::getByID);
        if (isTournamentIDCorrect(record) && canSubmitResult(match)) {
            submit(match);
        }
    }

}
