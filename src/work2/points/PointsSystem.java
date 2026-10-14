package work2.points;

public class PointsSystem {

    protected int current;

    public PointsSystem(int current) {
        this.current = current;
    }

    public void changeBy(int amount) {
        current += amount;
    }

    public boolean isZero() {
        return getCurrent() == 0;
    }

    public int getCurrent() {
        return current;
    }

}
