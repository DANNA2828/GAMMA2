package work2.stats;

import work2.points.CompleteBoundPointsSystem;
import work2.points.LowerBoundPointsSystem;

public class CombatantStats {

    private final HealthSystem health;
    private final LowerBoundPointsSystem armor;
    private final CompleteBoundPointsSystem mana;

    public CombatantStats(
            Stats currentStats,
            int maxMana,
            int maxHealth
    ) {
        this.health = new HealthSystem(maxHealth, 0, currentStats.health());
        this.armor = new LowerBoundPointsSystem(0, currentStats.armor());
        this.mana = new CompleteBoundPointsSystem(maxMana, 0, currentStats.mana());
    }

    public void affectBy(Stats stats) {
        this.health.changeBy(stats.health());
        this.armor.changeBy(stats.armor());
        this.mana.changeBy(stats.mana());
    }

    public HealthSystem getHealth() {
        return health;
    }

    public LowerBoundPointsSystem getArmor() {
        return armor;
    }

    public CompleteBoundPointsSystem getMana() {
        return mana;
    }
}
