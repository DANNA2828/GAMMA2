package work2.actions;

import work2.Combatant;

import java.util.function.Consumer;
import java.util.function.Function;

public enum Action {

    PHYSICAL_STRIKE(PhysicalStrike::qualifies, PhysicalStrike::affectActing, PhysicalStrike::affectTarget),
    ARCANE_BOLT(ArcaneBolt::qualifies, ArcaneBolt::affectActing, ArcaneBolt::affectTarget),
    FIRST_AID(FirstAid::qualifies, FirstAid::affectActing, FirstAid::affectTarget);

    private final Function<Combatant, Boolean> qualifies;
    private final Consumer<Combatant> affectActing;
    private final Consumer<Combatant> affectTarget;

    Action(Function<Combatant, Boolean> qualifies, Consumer<Combatant> affectActing, Consumer<Combatant> affectTarget) {
        this.qualifies = qualifies;
        this.affectActing = affectActing;
        this.affectTarget = affectTarget;
    }

    public void act(Combatant acting, Combatant target) {
        if (qualifies.apply(acting)) {
            affectActing.accept(acting);
            affectTarget.accept(target);
        }
    }

}
