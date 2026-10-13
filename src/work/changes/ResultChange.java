package work.changes;

import work.matches.Match;
import work.tournaments.Player;

import java.util.HashMap;
import java.util.Optional;

public record ResultChange (int TournamentID, int matchID, OperationType operationType, HashMap<Player, Integer> scoreChanges) {

    public static Optional<ResultChange> fromMatches( int tournamentID, Match original, Match correction, OperationType operationType, int winPoints, int tiePoints) {
        Optional<HashMap<Player, Integer>> originalScoreChanges = original.getScoreChanges(correction, operationType, winPoints, tiePoints);
        return originalScoreChanges.map(
                scoreChanges -> new ResultChange(tournamentID, original.getID(), operationType, scoreChanges)
        );
    }

}
