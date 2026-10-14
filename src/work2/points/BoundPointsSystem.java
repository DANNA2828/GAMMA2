package work2.points;

public abstract class BoundPointsSystem extends PointsSystem implements BoundSystem{

    public BoundPointsSystem(int current) {
        super(current);
        bind();
    }

    @Override
    public void changeBy(int amount) {
        super.changeBy(amount);
        bind();
    }
}
