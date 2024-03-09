package Floor.FEntities.FWeapon;

import Floor.FTools.BossList;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.entities.Lightning;
import mindustry.entities.Units;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Healthc;
import mindustry.gen.Nulls;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.Weapon;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class SuctionWeapon extends Weapon {
    public static final Map<Unit, Teamc> map = new HashMap<>();
    public static final Map<Unit, Float> Timer = new HashMap<>();
    public static final Map<Unit, Vec2> points = new HashMap<>();
    public float range = 100;
    public float time = 600;
    public int lightnings = 10;

    public SuctionWeapon(String s) {
        super(s);
    }

    @Override
    public void update(Unit unit, WeaponMount mount) {
        super.update(unit, mount);

        removeUnit();

        Timer.replaceAll((u, v) -> v + Time.delta);
        float timer = Timer.computeIfAbsent(unit, u -> 0F);
        Teamc baseTarget = map.computeIfAbsent(unit, u -> mount.target);

        if (baseTarget != mount.target) {
            if (mount.target != null && (points.get(unit) == null || !points.get(unit).within(mount.target, range / 2))) {
                points.put(unit, new Vec2(mount.target.x(), mount.target.y()));
                map.put(unit, mount.target);
                baseTarget = mount.target;
            } else if (mount.target == null) {
                map.put(unit, null);
                baseTarget = null;
            }
        }

        if (baseTarget instanceof Healthc h && !(h.dead() || h.health() <= 0)) {
            Teamc finalBaseTarget = baseTarget;
            Vec2 point = points.computeIfAbsent(unit, u -> new Vec2(finalBaseTarget.x(), finalBaseTarget.y()));
            float tx = point.x, ty = point.y;

            if (timer % 180 <= 0.5F) {
                for (int i = 0; i < lightnings; i++) {
                    Lightning.create(unit.team, bullet.lightningColor, bullet.lightningDamage / 10, tx, ty, (float) Mathf.random(360) + 1, (int) (range / 6));
                }
            }

            final boolean[] has = {false};
            Units.nearbyEnemies(unit.team, tx, ty, range, u -> {
                if (BossList.list.indexOf(u.type) < 0) {
                    has[0] = true;
                    if (!u.within(tx, ty, 20)) {
                        float ux = u.x, uy = u.y;
                        float len = (float) sqrt((ux - tx) * (ux - tx) + (uy - ty) * (uy - ty));
                        float power = abs(1 - ((len - 18F) / (range - 18F)));
                        Vec2 vec = new Vec2();
                        vec.set((tx - ux) * (1 - power), (ty - uy) * (1 - power));
                        vec.setLength(power * unit.hitSize * 4.5F / u.hitSize / u.hitSize);
                        u.vel.add(vec);
                    }
                }
            });
            if (!has[0]) {
                map.remove(unit);
                points.remove(unit);
            }
        }
    }

    @Override
    public void draw(Unit unit, WeaponMount mount) {
        Vec2 point = points.get(unit);
        if (point != null && ((map.get(unit)) instanceof Healthc h && !h.dead())) {
            float tx = point.x, ty = point.y;
            Draw.z(Layer.shields);
            Lines.stroke(1.5f);
            Draw.alpha(0.09f);
            Fill.circle(tx, ty, range);
        }
        super.draw(unit, mount);
    }

    public void removeUnit() {
        map.remove(null);
        Timer.remove(null);
        points.remove(null);
        map.remove(Nulls.unit);
        Timer.remove(Nulls.unit);
        points.remove(Nulls.unit);

        Seq<Unit> units = new Seq<>();
        for (Unit u : map.keySet()) {
            Teamc t = map.get(u);
            if (t == null || t instanceof Healthc h && (h.dead() || h.health() <= 0)) {
                units.add(u);
            }
        }
        for (Unit u : units) {
            map.remove(u);
            points.remove(u);
        }
    }
}