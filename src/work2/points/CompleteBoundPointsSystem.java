package work2.points;

public class CompleteBoundPointsSystem extends LowerBoundPointsSystem {

    private final int max;

    public CompleteBoundPointsSystem(int max, int min, int current) {
        super(min, current);
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void bind() {
        current = Math.min(current, max);
        super.bind();
    }
}
