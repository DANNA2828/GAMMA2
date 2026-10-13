package work;

import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard<T extends Comparable<T>> {

    private final HashMap<T, Integer> leaderboard;

    public Leaderboard() {
        this.leaderboard = new HashMap<>();
    }

    public Integer getScore(T object) {
        for (T key : leaderboard.keySet()) {
            if (object.isSame(key)) {
                return leaderboard.get(key);
            }
        }
        return null;
    }

    public void addToLeaderboard(T object) {
        addPoints(object, 0);
    }

    public void addPoints(T object, int amount) {
        leaderboard.put(object, (getScore(object) != null ? getScore(object) : 0) + amount);
    }

    public ArrayList<T> getStandings() {
        ArrayList<T> standings = new ArrayList<>(leaderboard.keySet());
        for (int i = 0; i < standings.size() - 1; i++) {
            for (int j = 0; j < standings.size() - 1 - i; j++) {
                if (getScore(standings.get(j)) < getScore(standings.get(j+1))) {
                    T temp = standings.get(j);
                    standings.set(j, standings.get(j+1));
                    standings.set(j+1, temp);
                }
            }
        }
        return standings;
    }

}
