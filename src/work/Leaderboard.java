package work;

import java.util.HashMap;

public class Leaderboard <T> {

    private final HashMap<T, Integer> leaderboard;

    public Leaderboard() {
        this.leaderboard = new HashMap<>();
    }

    public void addToLeaderboard(T object) {
        addPoints(object, 0);
    }

    public void addPoints(T object, int amount) {
        leaderboard.put(object, leaderboard.get(object) + amount);
    }

}
