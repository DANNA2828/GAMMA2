package work.tournaments;

public enum TournamentType {

    CLASSIC(3, 1),
    SPRINT(5, 2);

    private final int winPoints;
    private final int tiePoints;


    TournamentType(int winPoints, int tiePoints) {
        this.winPoints = winPoints;
        this.tiePoints = tiePoints;
    }

    public int getWinPoints() {
        return winPoints;
    }

    public int getTiePoints() {
        return tiePoints;
    }

}
