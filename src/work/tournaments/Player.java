package work.tournaments;

import work.Comparable;
import work.WithID;

public class Player implements Comparable<Player>, WithID {

    private int ID;
    private String name;
    private double rating;

    public Player(int ID, String name, double rating) {
        this.ID = ID;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public boolean isSame(Player other) {
        return this.equals(other) || ID == other.getID();
    }

    @Override
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

}
