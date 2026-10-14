package work2.stats;

import work2.points.CompleteBoundPointsSystem;

public class HealthSystem extends CompleteBoundPointsSystem {

    private boolean isDead;

    public HealthSystem(int maxHealth, int minHealth, int currentHealth) {
        super(maxHealth, minHealth, currentHealth);
        isDead = isZero();
    }

    @Override
    public void changeBy(int amount) {
        if (!isDead) {
            super.changeBy(amount);
            updateIsDead();
        }
    }

    public boolean isDead() {
        return isDead;
    }

    private void updateIsDead() {
        isDead = isZero();
    }

}
