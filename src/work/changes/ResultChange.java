package work.changes;

import work.matches.Match;

import java.util.HashMap;
import java.util.Optional;

public record ResultChange<T> (int TournamentID, int matchID, OperationType operationType, HashMap<T, Integer> scoreChanges) {

    public static <T> Optional<ResultChange<T>> fromMatches(Match original, Match correction, OperationType operationType, int TournamentID) {
        if (!original.hasPotentialCorrection(correction, operationType)) {
            return Optional.empty();
        }

    }

}
