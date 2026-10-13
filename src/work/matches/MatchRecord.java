package work.matches;

import java.util.Optional;

public record MatchRecord(int matchID, int TournamentID, int player1ID, int player2ID, Optional<Integer> winnerNum) {}
