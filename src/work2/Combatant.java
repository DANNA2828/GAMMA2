package work2;

import work2.actions.Action;
import work2.stats.CombatantStats;

public class Combatant {

    private final CombatantStats stats;

    public Combatant(CombatantStats stats) {
        this.stats = stats;
    }

    public CombatantStats getStats() {
        return stats;
    }

    public void act(Combatant other, Action action) {
        action.act(this, other);
    }

}
