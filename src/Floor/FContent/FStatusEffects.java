package Floor.FContent;

import Floor.FType.FStatusEffect.ADH;
import Floor.FType.FStatusEffect.HighChangeStatus;
import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.StatusEffect;

public class FStatusEffects {
    public static StatusEffect StrongStop, boostSpeed, suppressII, slowII, HardHit, High_tension, fastII, High_tensionII,
            burningV, High_tensionIII;

    public static void load() {
        StrongStop = new StatusEffect("StrongStop") {{
            speedMultiplier = 0;
            buildSpeedMultiplier = 0;
            disarm = true;
        }};
        boostSpeed = new StatusEffect("boostSpeed") {{
            speedMultiplier = 15;
            show = false;
            permanent = false;
        }};
        suppressII = new StatusEffect("suppressII") {{
            speedMultiplier = 0.8F;
            reloadMultiplier = 0.55F;
            color = new Color(170, 170, 153, 255);
            effectChance = 1;
            effect = new MultiEffect(new ParticleEffect() {{
                baseLength = 0;
                length = 25;
                lifetime = 10;
                sizeFrom = 2;
                sizeTo = 0;
                colorFrom = color;
                colorTo = color;
            }}, new ParticleEffect() {{
                particles = 2;
                line = true;
                interp = Interp.slowFast;
                strokeFrom = 2;
                strokeTo = 0;
                lenFrom = 10;
                lenTo = 0;
                length = 23;
                baseLength = 0;
                lifetime = 10;
                colorFrom = new Color(170, 170, 0, 255);
                colorTo = color;
            }});
        }};
        slowII = new StatusEffect("slowII") {{
            speedMultiplier = 0.33F;
            color = new Color(0, 0, 0, 0);
            effectChance = 1;
            effect = Fx.none;
        }};
        HardHit = new StatusEffect("HardHit") {{
            damageMultiplier = 1.2F;
            speedMultiplier = 0.75F;
            healthMultiplier = 0.4F;
            reloadMultiplier = 0.5F;
            color = new Color(145, 75, 0, 255);
            effectChance = 1;
            effect = new MultiEffect(new ParticleEffect() {{
                baseLength = 0;
                length = 25;
                lifetime = 10;
                sizeFrom = 2;
                sizeTo = 0;
                colorFrom = color;
                colorTo = color;
            }}, new ParticleEffect() {{
                particles = 2;
                line = true;
                interp = Interp.slowFast;
                strokeFrom = 2;
                strokeTo = 0;
                lenFrom = 10;
                lenTo = 0;
                length = 23;
                baseLength = 0;
                lifetime = 10;
                colorFrom = color;
                colorTo = color;
            }});
        }};
        High_tension = new HighChangeStatus("High_tension") {{
            speedTo = 0.8f;
            reloadTo = 0.8f;
            healthTo = 0.9f;
        }};
        fastII = new StatusEffect("fastII") {{
            speedMultiplier = 2.4f;
            init(() -> opposite(StatusEffects.slow));
        }};
        High_tensionII = new HighChangeStatus("High_tensionII") {{
            speedTo = 0.5f;
            reloadTo = 0.5f;
            healthTo = 0.8f;
            damage = 6;
        }};
        High_tensionIII = new HighChangeStatus("High_tensionIII") {{
            speedTo = 0.1f;
            reloadTo = 0.1f;
            healthTo = 0.7f;
            damage = 90;
        }};
        burningV = new StatusEffect("burningII"){{
            damage = 4.5f;
        }};
    }
}