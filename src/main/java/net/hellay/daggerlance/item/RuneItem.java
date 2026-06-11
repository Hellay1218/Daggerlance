package net.hellay.daggerlance.item;

import net.hellay.daggerlance.init.DaggerlanceDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class RuneItem extends Item {
    public RuneItem(Properties properties, String rune) {
        super(properties.component(DaggerlanceDataComponents.DAGGERLANCE_RUNE_DATA, rune).stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
    }
}
