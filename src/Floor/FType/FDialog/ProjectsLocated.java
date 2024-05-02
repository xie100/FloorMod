package Floor.FType.FDialog;

import arc.struct.Seq;
import mindustry.entities.abilities.Ability;
import mindustry.type.Weapon;
import mindustry.ui.dialogs.BaseDialog;

import java.util.HashMap;

import static Floor.FContent.FItems.*;

public class ProjectsLocated extends BaseDialog {
    public static float maxSize = 0;
    public static float freeSize = 0;
    public static final HashMap<String, heavyGetter> heavies = new HashMap<>();
    public static final HashMap<String, levelGetter> levels = new HashMap<>();
    public static final HashMap<String, Integer> maxLevel = new HashMap<>();
    public static final Seq<weaponPack> weapons = new Seq<>();
    public static final Seq<abilityPack> abilities = new Seq<>();

    public ProjectsLocated(String title, DialogStyle style) {
        super(title, style);
    }

    public ProjectsLocated(String title) {
        super(title);
    }

    static {
        heavies.put("damage", i -> i * 1f);
        heavies.put("pass", i -> i * 1f);
        heavies.put("prices", i -> i * 1.5f);
        heavies.put("splash", i -> i * 1.5f);
        heavies.put("lightning", i -> i * 1.2f);
        heavies.put("percent", i -> i * 1.2f);
        heavies.put("number", i -> i * 1.8f);
        heavies.put("frags", i -> i * 1.8f);

        levels.put("damage", f -> f <= 0 ? 0 : f <= 12 ? 1 : f <= 24 ? 2 : f <= 48 ? 3 : f <= 96 ? 4 : f <= 192 ? 5 : 6);
        levels.put("pass", f -> f <= 0 ? 0 : f <= 15 ? 1 : f <= 35 ? 2 : f <= 60 ? 3 : f <= 90 ? 4 : f <= 120 ? 5 : 6);
        levels.put("prices", f -> f <= 0 ? 0 : f <= 1 ? 1 : f <= 2 ? 2 : f <= 4 ? 3 : f <= 6 ? 4 : f <= 10 ? 5 : 6);
        levels.put("splash", f -> f <= 0 ? 0 : f <= 1.5 ? 1 : f <= 2.1 ? 2 : f <= 3.5 ? 3 : f <= 6 ? 4 : f <= 9.5 ? 5 : 6);
        levels.put("lightning", f -> f <= 0 ? 0 : f <= 1 ? 1 : f <= 2 ? 2 : f <= 4 ? 3 : f <= 6 ? 4 : f <= 10 ? 5 : 6);
        levels.put("percent", f -> f <= 0 ? 0 : f <= 0.1 ? 1 : f <= 0.15 ? 2 : f <= 0.3 ? 3 : f <= 0.45 ? 4 : f <= 0.65 ? 5 : 6);
        levels.put("number", f -> f <= 0 ? 0 : f <= 1 ? 1 : f <= 2 ? 2 : f <= 4 ? 3 : f <= 6 ? 4 : f <= 10 ? 5 : 6);
        levels.put("frags", f -> f <= 0 ? 0 : f <= 1 ? 1 : f <= 2 ? 2 : f <= 4 ? 3 : f <= 6 ? 4 : f <= 10 ? 5 : 6);

        updateMaxLevel();
        updateHeavy();
    }

    public static float getHeavy(String type, float val) {
        return heavies.get(type).get(levels.get(type).get(val));
    }

    public static boolean couldUse(String type, float val) {
        if (maxLevel.isEmpty()) {
            updateMaxLevel();
        }
        return levels.get(type).get(val) <= maxLevel.computeIfAbsent(type, name -> 0);
    }

    public static void updateHeavy() {
        maxSize = 0;
        for (int i = allSize.length; i > 0; i--) {
            if (allSize[i - 1].unlocked()) {
                maxSize = 4 + (i == 1 ? 2 : i == 2 ? 5 : i == 3 ? 8 : i == 4 ? 12 : i == 5 ? 16 : i == 6 ? 20 : i == 7 ? 25 :
                        i == 8 ? 30 : i == 9 ? 35 : 40);
                break;
            }
        }
        freeSize = maxSize;
        for (weaponPack wp : weapons) {
            freeSize -= wp.heavy;
        }
        for (abilityPack ap : abilities) {
            freeSize -= ap.heavy;
        }
    }

    public static void updateMaxLevel() {
        //damage
        maxLevel.put("damage", 0);
        for (int i = allDamage.length; i > 0; i--) {
            if (allDamage[i - 1].unlocked()) {
                maxLevel.put("bulletBase", i);
                break;
            }
        }

        //splash
        maxLevel.put("splash", 0);
        for (int i = allSplash.length; i > 0; i--) {
            if (allSplash[i - 1].unlocked()) {
                maxLevel.put("splash", i);
                break;
            }
        }

        //prices
        maxLevel.put("prices", 0);
        for (int i = allPrices.length; i > 0; i--) {
            if (allPrices[i - 1].unlocked()) {
                maxLevel.put("prices", i);
                break;
            }
        }

        //knock
        maxLevel.put("knock", 0);
        for (int i = allKnock.length; i > 0; i--) {
            if (allKnock[i - 1].unlocked()) {
                maxLevel.put("knock", i);
                break;
            }
        }

        //percent
        maxLevel.put("percent", 0);
        for (int i = allPercent.length; i > 0; i--) {
            if (allPercent[i - 1].unlocked()) {
                maxLevel.put("percent", i);
                break;
            }
        }
    }

    public interface heavyGetter {
        float get(int lev);
    }

    public interface levelGetter {
        int get(float val);
    }

    public static class weaponPack {
        Weapon weapon;
        float heavy = 0;
    }

    public static class abilityPack {
        Ability ability;
        float heavy = 0;
    }
}
