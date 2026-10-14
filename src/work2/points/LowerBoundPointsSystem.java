package work2.points;

public class LowerBoundPointsSystem extends BoundPointsSystem{

    private final int min;

    public LowerBoundPointsSystem(int min, int current) {
        super(current);
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void bind() {
        current = Math.max(current, min);
    }
}
