package Floor.FContent;

import Floor.FEntities.FBlock.KnockingTurret;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ReloadTurret;

public class FDefends {
    public static Block kt;

    public static void load() {
        kt = new KnockingTurret("kt") {{
            health = 114514;
            requirements(Category.effect, ItemStack.with(Items.copper, 1));
        }};
    }
}
