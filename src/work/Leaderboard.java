package work;

import java.util.HashMap;

public class Leaderboard <T> {

    private HashMap<T, Integer> leaderboard;

    public Leaderboard() {
    }

    public void addToLeaderboard(T object) {
        addPoints(object, 0);
    }

    public void addPoints(T object, int amount) {
        leaderboard.put(object, leaderboard.get(object) + amount);
    }

}
