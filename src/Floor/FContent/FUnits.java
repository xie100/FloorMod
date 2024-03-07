package Floor.FContent;

import Floor.FAI.*;
import Floor.FEntities.FAbility.HitDamageAbility;
import Floor.FEntities.FAbility.StrongMinerAbility;
import Floor.FEntities.FBulletType.SqrtDamageBullet;
import Floor.FEntities.FUnit.F.ENGSWEISUnitEntity;
import Floor.FEntities.FUnit.F.TileMiner;
import Floor.FEntities.FUnit.F.TileSpawnerUnit;
import Floor.FEntities.FUnitType.ChainUnitType;
import Floor.FEntities.FUnitType.ENGSWEISUnitType;
import Floor.FEntities.FUnit.F.ChainLegUnit;
import Floor.FEntities.FUnit.Override.FMechUnit;
import Floor.FEntities.FUnit.Override.FUnitEntity;
import Floor.FEntities.FBulletType.PercentBulletType;
import Floor.FEntities.FBulletType.PercentEmpBulletType;
import Floor.FEntities.FUnitType.TileMinerUnitType;
import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.ObjectSet;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.CommandAI;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.abilities.ShieldRegenFieldAbility;
import mindustry.entities.abilities.SpawnDeathAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class FUnits {
    public static UnitType a, velocity, AVelocity, Velocity, crazy, barb, b, transfer, Hammer, Buying, Transition;

    public static void load() {
        Velocity = new UnitType("Velocity") {{
            hidden = true;
            constructor = FUnitEntity::create;
            speed = 10;
        }};
        AVelocity = new UnitType("AVelocity") {
            {
                constructor = FMechUnit::create;
                health = 50000;
                armor = 12;
                speed = 0;
                rotateSpeed = 0;
                baseRotateSpeed = 0;
                hitSize = 20;
                hidden = true;
                createScorch = false;
                createWreck = false;
                abilities.add(
                        new SpawnDeathAbility() {{
                            unit = Velocity;
                            spread = 0;
                        }}
                );
                weapons.add(new Weapon() {
                    {
                        controllable = false;
                        autoTarget = true;
                        alwaysShooting = true;
                        aiControllable = false;
                        shoot = new ShootBarrel() {{
                            shots = 1;
                            shotDelay = 0;
                        }};
                        reload = 2000;
                        bullet = new BasicBulletType() {{
                            hitEffect = Fx.massiveExplosion;
                            lifetime = 0;
                            damage = 0;
                            splashDamage = 5000;
                            splashDamageRadius = 160;
                        }};
                    }
                });
                weapons.add(new Weapon() {{
                    controllable = false;
                    autoTarget = true;
                    alwaysShooting = true;
                    aiControllable = false;
                    shootSound = Sounds.explosion;
                    shoot = new ShootBarrel() {{
                        firstShotDelay = 1800;
                    }};
                    reload = 2000;
                    bullet = new BasicBulletType() {{
                        hitEffect = Fx.massiveExplosion;
                        killShooter = true;
                        lifetime = 0;
                        damage = 0;
                        splashDamage = 5000;
                        splashDamageRadius = 160;
                    }};
                }});
            }
        };
        a = new ChainUnitType("a") {{
            constructor = ChainLegUnit::create;
            flying = false;
            speed = 0.8F;
            health = 900000;
            hitSize = 13;
            buildRange = 100;
            buildSpeed = 10;
            weapons.add(new Weapon("a-weapon") {{
                top = false;
                shootY = 3f;
                reload = 9f;
                ejectEffect = Fx.none;
                recoil = 1f;
                x = 7f;
                shootSound = Sounds.flame;

                bullet = new PercentBulletType() {{
                    lifetime = 20;
                    firstPercent = true;
                    percent = 2.5F;
                    width = 15;
                    height = 15;
                    speed = 10;
                    damage = 0;
                    WS = true;
                    splashDamageRadius = 0;
                }};
            }});
            engines.add(
                    new UnitEngine(
                            (float) (hitSize * Math.sin(Math.toRadians(90)) / 2.0F),
                            (float) (hitSize * Math.cos(Math.toRadians(90)) / 2.0F),
                            3, 0
                    )
            );
            engines.add(
                    new UnitEngine(
                            (float) (hitSize * Math.sin(Math.toRadians(180)) / 2.0F),
                            (float) (hitSize * Math.cos(Math.toRadians(180)) / 2.0F),
                            3, -90
                    )
            );
            engines.add(
                    new UnitEngine(
                            (float) (hitSize * Math.sin(Math.toRadians(270)) / 2.0F),
                            (float) (hitSize * Math.cos(Math.toRadians(270)) / 2.0F),
                            3, 180
                    )
            );
        }};
        velocity = new ENGSWEISUnitType("velocity") {
            {
                constructor = FUnitEntity::create;
                defend = 60;
                power = 50;
                flying = true;
                health = 202125;
                armor = 30;
                speed = 5;
                hitSize = 35;
                createScorch = false;
                createWreck = false;
                abilities.add(new SpawnDeathAbility(AVelocity, 1, 0));
                abilities.add(new HitDamageAbility(37, 0, -1, false, 30, 5));
                weapons.add(new Weapon() {{
                    reload = 300;
                    controllable = false;
                    mirror = false;
                    alwaysShooting = true;
                    shootSound = Sounds.none;
                    bullet = new EmpBulletType() {{
                        despawnEffect = Fx.none;
                        hitEffect = Fx.none;
                        width = 0;
                        height = 0;
                        speed = 0;
                        damage = 0;
                        lifetime = 0;
                        radius = 56;
                        splashDamage = 20000;
                        splashDamageRadius = 56;
                        splashDamagePierce = true;
                        shootEffect = Fx.none;
                        powerSclDecrease = 0;
                        unitDamageScl = 1;
                    }};
                }});
                weapons.add(new Weapon() {
                    {
                        reload = 50;
                        mirror = true;
                        x = 0;
                        y = 0;
                        shoot = new ShootBarrel() {{
                            barrels = new float[]{0, -20, 70};
                        }};
                        bullet = new BasicBulletType() {{
                            sprite = "Floor-Jamming_bomb";
                            width = 24;
                            height = 55;
                            trailLength = 23;
                            trailWidth = 5;
                            speed = 11;
                            damage = 300;
                            lifetime = 100;
                            homingRange = 1000;
                            homingDelay = 30;
                            homingPower = 0.4F;
                            hitEffect = Fx.none;
                            keepVelocity = false;
                            despawnEffect = Fx.none;
                            fragBullets = 1;
                            fragRandomSpread = 0;
                            fragVelocityMax = 7;
                            fragVelocityMin = 7;
                            fragBullet = new BasicBulletType() {{
                                damage = 10;
                                lifetime = 120;
                                width = 24;
                                height = 55;
                                bulletInterval = 10;
                                intervalBullets = 5;
                                intervalRandomSpread = 10;
                                intervalBullet = new BasicBulletType() {{
                                    damage = 10;
                                    speed = 12;
                                    trailChance = 1;
                                    trailEffect = Fx.artilleryTrail;
                                }};
                            }};
                        }};
                    }
                });
                weapons.add(new Weapon() {{
                    reload = 50;
                    mirror = true;
                    x = 0;
                    y = 0;
                    shoot = new ShootBarrel() {{
                        barrels = new float[]{0, -25, 50};
                    }};
                    bullet = new MissileBulletType() {{
                        sprite = "Fire_bomb";
                        width = 15;
                        height = 40;
                        trailLength = 20;
                        trailWidth = 4;
                        speed = 7;
                        damage = 300;
                        lifetime = 210;
                        homingRange = 1000;
                        homingDelay = 40;
                        homingPower = 0.3F;
                        hitEffect = Fx.none;
                        splashDamage = 0;
                        splashDamageRadius = 156;
                        splashDamagePierce = true;
                        statusDuration = 1200;
                        keepVelocity = false;
                        status = new StatusEffect("HD") {{
                            damageMultiplier = 1.5F;
                            speedMultiplier = 0.5F;
                            healthMultiplier = 0.5F;
                            reloadMultiplier = 0.5F;
                            color = Color.valueOf("914B00FF");
                            effectChance = 1;
                            effect = new MultiEffect() {
                                {
                                    effects = new Effect[2];
                                    effects[0] = new ParticleEffect() {{
                                        particles = 6;
                                        baseLength = 0;
                                        length = 25;
                                        lifetime = 10;
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                        colorFrom = Color.valueOf("914B00FF");
                                        colorTo = colorFrom;
                                    }};
                                    effects[1] = new ParticleEffect() {{
                                        particles = 2;
                                        interp = Interp.slowFast;
                                        line = true;
                                        lifetime = 10;
                                        strokeFrom = 2;
                                        strokeTo = 2;
                                        length = 2;
                                        lenTo = 0;
                                        baseLength = 0;
                                        colorFrom = Color.valueOf("914B00FF");
                                        colorTo = colorFrom;
                                    }};
                                }
                            };
                        }};
                        fragBullets = 4;
                        fragVelocityMax = 0;
                        fragVelocityMin = 0;
                        fragBullet = new BasicBulletType() {{
                            damage = 0;
                            lifetime = 0;
                            puddles = 35;
                            puddleRange = 35;
                            puddleAmount = 25;
                            puddleLiquid = Liquids.oil;
                        }};
                        puddles = 35;
                        puddleRange = 35;
                        puddleAmount = 15;
                        puddleLiquid = Liquids.slag;
                    }};
                }});
                weapons.add(new Weapon() {{
                    reload = 50;
                    mirror = true;
                    x = 0;
                    y = 0;
                    shoot = new ShootBarrel() {{
                        barrels = new float[]{0, -30, 30};
                    }};
                    bullet = new MissileBulletType() {{
                        sprite = "High_explosive_projectile";
                        width = 15;
                        height = 40;
                        trailLength = 20;
                        trailWidth = 4;
                        speed = 6;
                        damage = 0;
                        lifetime = 300;
                        homingRange = 1000;
                        homingDelay = 50;
                        homingPower = 0.2F;
                        splashDamage = 300;
                        splashDamageRadius = 156;
                        splashDamagePierce = false;
                        statusDuration = 1200;
                        hitSound = Sounds.explosion;
                        keepVelocity = false;
                        despawnEffect = new WaveEffect() {{
                            strokeFrom = 14;
                            strokeTo = 10;
                            lifetime = 160;
                            sizeTo = 168;
                            colorFrom = Color.valueOf("110000AA");
                            colorTo = colorFrom;
                        }};
                        hitEffect = despawnEffect;
                    }};
                }});
                weapons.add(new Weapon() {
                    {
                        reload = 240;
                        bullet = new PercentEmpBulletType() {
                            {
                                width = 0;
                                height = 0;
                                speed = 0.5F;
                                damage = 20000;
                                lifetime = 1200;
                                radius = 78;
                                splashDamageRadius = 0;
                                shootEffect = Fx.none;
                                powerSclDecrease = 0;
                                unitDamageScl = 1;
                                homingRange = 1000;
                                homingDelay = 50;
                                homingPower = 0.2F;
                                keepVelocity = false;
                                lightColor = Color.valueOf("8D0000FF");
                                scaleLife = false;
                                shrinkY = 0;
                                trailLength = 0;
                                trailChance = 1;
                                WL = true;
                                lightningPercent = 12;
                                lightning = 12;
                                lightningLength = 10;
                                trailEffect = new ParticleEffect() {{
                                    particles = 80;
                                    cone = 360;
                                    lifetime = 4;
                                    line = true;
                                    lenFrom = 7;
                                    lenTo = 7;
                                    strokeFrom = 1;
                                    strokeTo = 1;
                                    cap = false;
                                    colorFrom = Color.valueOf("914B00FF");
                                    colorTo = colorFrom;
                                }};
                                parts.add(new ShapePart() {{
                                    circle = true;
                                    radius = 11.5F;
                                    radiusTo = 11.5F;
                                    color = Color.valueOf("914B00FF");
                                }});
                                parts.add(new HoverPart() {{
                                    radius = 56;
                                    color = Color.valueOf("914B00FF");
                                    phase = 150;
                                    sides = 18;
                                }});
                            }
                        };
                    }
                });
            }
        };
        crazy = new ENGSWEISUnitType("crazy") {{
            constructor = ENGSWEISUnitEntity::create;
            aiController = StrongBoostAI::new;
            defaultCommand = new UnitCommand("crazy", "crazy", u -> new StrongBoostAI());
            commands = new UnitCommand[]{UnitCommand.moveCommand, defaultCommand};
            immunities.add(StatusEffects.slow);
            immunities.add(StatusEffects.unmoving);
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.sporeSlowed);

            Health2 = 3000;
            Speed1 = 3;

            HitReload = 15;
            percent = 2.5F;
            changeHel = 100;
            damage = 200 * 1.4F;
            minSpeed = 5;
            firstPercent = true;

            defend = 100;
            power = 2;
            circleTarget = true;
            flying = true;
            targetAir = targetGround = true;
            maxRange = range = 40;
            rotateSpeed = 360;
            speed = 7;
            drag = 1F;
            accel = 0.5F;
            health = 6600;
            armor = 7;
        }};
        barb = new ENGSWEISUnitType("barb") {{
            constructor = ENGSWEISUnitEntity::create;
            aiController = StrongBoostAI::new;
            commands = new UnitCommand[]{UnitCommand.moveCommand, FCommands.STB};
            flying = true;
            targetAir = targetGround = true;

            Speed1 = 0.8F;
            damage = 0;
            percent = 10;
            changeHel = -1;
            firstPercent = true;
            reload = 3600;
            minSpeed = 7;
            delay = 90;
            HitReload = 90;

            range = 40;
            maxRange = range;
            speed = 1.3F;
            rotateSpeed = 5;
            faceTarget = true;
            health = 140;
            armor = 2;
            engineOffset = 7;
            engineSize = 2.5F;
            itemCapacity = 30;
            hitSize = 5;
            weapons.add(new Weapon() {{
                reload = 30;
                y = 3.8F;
                x = 2.7F;
                top = false;
                bullet = new BasicBulletType() {{
                    speed = 3.7F;
                    damage = 15;
                    lifetime = 60;
                }};
            }});
        }};
        transfer = new TileMinerUnitType("transfer") {{
            constructor = TileMiner::create;
            defaultCommand = new UnitCommand("TileMine", "TileMine", TileMinerAI::new);
            aiController = TileMinerAI::new;
            commands = new UnitCommand[]{defaultCommand, new UnitCommand("TilePut", "TilePut", TilePutAI::new)};
            controller = u -> !playerControllable || (u.team.isAI() && !u.team.rules().rtsAi) ? aiController.get() : new PoseBridgeCommand();
            hidden = true;
            isEnemy = false;
            useUnitCap = false;

            flying = true;
            health = 100;
            speed = 1.7F;
            armor = 2;
            drag = 0.8F;
            accel = 0.8F;
            rotateSpeed = 4;

            mineFloor = true;
            mineWalls = true;
            mineRange = 0;
            mineSpeed = 1;
            mineTier = 5;
        }};
        Buying = new ENGSWEISUnitType("Buying") {{
            constructor = TileSpawnerUnit::create;
            aiController = StrongBoostAI::new;
            commands = new UnitCommand[]{UnitCommand.moveCommand, FCommands.STB};

            minSpeed = 5;
            reload = 3600;
            HitReload = 3600;
            percent = 30;
            firstPercent = true;
            Speed1 = 0.3F;
            Health2 = 2000;

            defend = 15;
            power = 5;

            rotateSpeed = 5.4f;
            buildSpeed = 3f;

            drownTimeMultiplier = 4f;

            hitSize = 24f;
            flying = true;
            speed = 0.4f;
            engineOffset = 9;
            engineSize = 3;
            accel = 0.9F;
            drag = 0.9F;
            maxRange = range = 40;

            health = 2000;
            armor = 9f;

            abilities.add(new StrongMinerAbility(transfer, 2400, 1, 1));
            abilities.add(new ShieldRegenFieldAbility(25, 900, 90, range));

            weapons.add(new Weapon() {{
                range = 150;
                reload = 180;
                y = 3.8F;
                x = 2.7F;
                top = false;
                alternate = false;
                shoot = new ShootBarrel() {{
                    shots = 30;
                    firstShotDelay = 60;
                    shotDelay = 1;
                }};
                bullet = new BasicBulletType() {{
                    homingDelay = 45;
                    homingPower = 0.1F;
                    homingRange = 1000;
                    inaccuracy = 30;
                    weaveRandom = true;
                    speed = 3.7F;
                    damage = 1;
                    lifetime = 240;
                    splashDamage = 150;
                    splashDamageRadius = 25.5F;
                    trailChance = 1F;
                }};
            }});
        }};
        Hammer = new ENGSWEISUnitType("Hammer") {{
            constructor = ENGSWEISUnitEntity::create;
            aiController = StrongBoostAI::new;
            commands = new UnitCommand[]{UnitCommand.moveCommand, FCommands.STB};

            health = 250;
            speed = 1.2F;
            range = maxRange = 36;
            armor = 4;
            accel = 0.9F;
            drag = 0.9F;
            flying = true;

            percent = 20;
            firstPercent = true;
            Health2 = 350;
            minSpeed = 8;
            Speed1 = 1.0F;
            HitReload = 3600;
            reload = 3600;

            defend = 100;
            power = 2;

            abilities.add(new ForceFieldAbility(hitSize * 2, 1, 100, 600));

            weapons.add(new Weapon() {{
                reload = 42;
                x = y = 0;
                shoot = new ShootSpread() {{
                    shots = 8;
                    spread = 22.5F;
                }};
                bullet = new BasicBulletType() {{
                    damage = 28;
                    lifetime = 90;
                    speed = 4;
                }};
            }});
        }};
        Transition = new ENGSWEISUnitType("Transition") {{
            constructor = ENGSWEISUnitEntity::create;
            aiController = StrongBoostAI::new;
            commands = new UnitCommand[]{UnitCommand.moveCommand, FCommands.STB};

            health = 22000;
            speed = 2F;
            range = maxRange = 72;
            armor = 14;
            accel = 0.9F;
            drag = 0.9F;
            flying = true;
            hitSize = 40;

            percent = 75;
            firstPercent = true;
            Health2 = 18000;
            minSpeed = 8;
            Speed1 = 1.3F;
            HitReload = 3600;
            reload = 3600;

            defend = 20;
            power = 20;

            weapons.add(new Weapon() {{
                range = 320;
                reload = 780;
                mirror = false;
                shoot = new ShootBarrel(){{
                    firstShotDelay = 120;
                }};
                bullet = new SqrtDamageBullet() {{
                    damage = 600;
                    speed = 0;
                    lifetime = 480;
                    halfWidth = 120;
                    sqrtLength = 320;
                    shootEffect = Fx.heal;
                }};
            }});
            weapons.add(new Weapon() {{
                reload = 2;
                alternate = false;
                bullet = new BasicBulletType() {{
                    damage = 20;
                    speed = 7;
                    lifetime = 90;
                }};
            }});
            weapons.add(new Weapon() {{
                reload = 150;
                alternate = false;
                bullet = new PercentBulletType() {{
                    percent = 7.5F;
                    firstPercent = true;
                    speed = 5.5F;
                    lifetime = 120;
                    trailChance = 1;
                    trailColor = Pal.redLight;
                    hitEffect = Fx.dynamicWave;
                }};
            }});
        }};
    }
}