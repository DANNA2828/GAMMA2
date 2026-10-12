package work;

public class Player {

    private int ID;
    private String name;
    private double rating;

    public Player(int ID, String name, double rating) {
        this.ID = ID;
        this.name = name;
        this.rating = rating;
    }

    public boolean isSame(Player other) {
        return this.equals(other) || ID == other.getID();
    }

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
