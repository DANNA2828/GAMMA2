package work2.actions;

import work2.Combatant;
import work2.stats.Stats;

public class PhysicalStrike {

    public static boolean qualifies(Combatant acting) {
        return true;
    }

    public static void affectActing(Combatant acting) {
    }

    public static void affectTarget(Combatant target) {
        target.getStats().affectBy(new Stats(target.getStats().getArmor().getCurrent() - 12, 0, 0));
    }

}
