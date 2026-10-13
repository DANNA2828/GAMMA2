package work.tournaments;

import work.Comparable;
import work.WithID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Leaderboard<T extends Comparable<T> & WithID> {

    private final HashMap<T, Integer> leaderboard;

    public Leaderboard() {
        this.leaderboard = new HashMap<>();
    }

    public T getKey(T object) {
        for (T key : leaderboard.keySet()) {
            if (object.isSame(key)) {
                return key;
            }
        }
        return null;
    }

    public Integer getScore(T object) {
        return leaderboard.get(getKey(object));
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
                T upper = standings.get(j);
                T lower = standings.get(j+1);
                if (
                        getScore(upper) < getScore(lower) ||
                                (getScore(upper).equals(getScore(lower)) && upper.getID() > lower.getID())
                ) {
                    standings.set(j, lower);
                    standings.set(j+1, upper);
                }
            }
        }
        return standings;
    }

    public LinkedHashMap<T, Integer> getOrdered() {
        ArrayList<T> orderedKeys = getStandings();
        LinkedHashMap<T, Integer> ordered = new LinkedHashMap<>(orderedKeys.size());
        for (T key : orderedKeys) {
            ordered.putLast(key, getScore(key));
        }
        return ordered;
    }

}
