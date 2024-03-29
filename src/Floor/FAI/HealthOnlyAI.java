package Floor.FAI;

import Floor.FContent.FUnits;
import mindustry.entities.Units;
import mindustry.entities.units.AIController;
import mindustry.gen.Teamc;

public class HealthOnlyAI extends AIController {
    private Teamc t;

    @Override
    public Teamc target(float x, float y, float range, boolean air, boolean ground) {
        t = null;
        Units.nearby(unit.team, x, y, range, u -> {
            if (u.damaged()) {
                t = u;
            }
        });
        if (t == null) {
            Units.nearby(unit.team, x, y, range, u -> t = u.type == FUnits.rejuvenate ? t : u);
        }
        return t;
    }

    @Override
    public void updateMovement() {
        target = target(unit.x, unit.y, 10000, true, true);
        if (target != null) {
            unit.lookAt(target);
            moveTo(target, 24);
        }
    }
}
