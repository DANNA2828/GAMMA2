package work.tournaments;

import work.IDList;
import work.changes.ResultChange;
import work.matches.Match;
import work.matches.MatchRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Tournament {

    private static final int MINIMUM_PLAYERS_TO_START = 4;

    private final int ID;
    private final String name;
    private final IDList<Player> players;
    private final IDList<Match> matches;
    private final Leaderboard<Player> leaderboard;
    private final ArrayList<ResultChange<Player>> scoreChanges;

    private final int winPoints;
    private final int tiePoints;

    private boolean hasStarted;

    public Tournament(int ID, String name, Player[] players, TournamentType type) {
        this.ID = ID;
        this.name = name;
        this.players = new IDList<>();
        this.matches = new IDList<>();
        this.leaderboard = new Leaderboard<>();
        this.scoreChanges = new ArrayList<>();

        this.winPoints = type.getWinPoints();
        this.tiePoints = type.getTiePoints();

        Arrays.stream(players).forEach(this::registerPlayer);
    }

    public void registerPlayer(Player player) {
        if (!players.isRegistered(player) && !hasStarted) {
            players.register(player);
            leaderboard.register(player);
        }
    }

    public void submit(Match match) {
        if (canSubmitResult(match)) {
            leaderboard.changePoints(match.getScoreChanges(winPoints, tiePoints));
            matches.register(match);
        }
    }

    public void submit(MatchRecord[] records) {
        for (MatchRecord record : records) {
            submit(record);
        }
    }

    public void changeResult(int matchID, Optional<Player> newWinner) {
        if ()
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
        return arePlayersValid(match) && !matches.isRegistered(match) && !hasStarted;
    }

    private boolean arePlayersValid(Match match) {
        for (int i = 0; i < match.getPlayers().length - 1; i++) {
            Player p1 = match.getPlayers()[i];
            Player p2 = match.getPlayers()[i+1];
            if (
                    p1 == null
                    || p2 == null
                    || p1.isSame(p2)
                    || !players.isRegistered(p1)
                    || !players.isRegistered(p2)
            ) {
                return false;
            }
        }
        return true;
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
