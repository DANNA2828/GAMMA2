package work2.actions;

import work2.Combatant;
import work2.stats.Stats;

public class ArcaneBolt {

    public static boolean qualifies(Combatant acting) {
        return acting.getStats().getMana().getCurrent() >= 10 && !acting.getStats().getHealth().isDead();
    }

    public static void affectActing(Combatant acting) {
        acting.getStats().affectBy(new Stats(0, 0, -10));
    }

    public static void affectTarget(Combatant target) {
        target.getStats().affectBy(new Stats(-18, 0, 0));
    }

}
