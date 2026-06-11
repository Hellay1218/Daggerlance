package net.hellay.daggerlance.init;


import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.hellay.daggerlance.item.RuneItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Weapon;

import java.util.function.Function;

public class DaggerlanceItems {

    public static final Item LANCIUM_INGOT = register("lancium_ingot", properties ->
            new Item(
                    properties.trimMaterial(DaggerlanceTrimMaterials.LANCIUM).fireResistant()), new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final Item DAGGERLANCE = register("daggerlance", properties -> new DaggerlanceItem(properties
            .rarity(Rarity.RARE)
            .component(DataComponents.TOOL, DaggerlanceItem.createToolComponent())
            .component(DaggerlanceDataComponents.DAGGERLANCE_RUNE, ItemStack.EMPTY)
            .repairable(DaggerlanceItems.LANCIUM_INGOT)
            .attributes(DaggerlanceItem.createAttributeModifiers())
            .enchantable(15)
            .component(DataComponents.WEAPON, new Weapon(1))
            .stacksTo(1)), new Item.Properties()
    );

    // runes

    public static final Item IMPACT_RUNE = register("impact_rune", properties -> new RuneItem(properties,DaggerlanceItem.IMPACT_RUNE_ID),new Item.Properties());
    public static final Item BLANK_RUNE = register("blank_rune", properties -> new RuneItem(properties,"blank"),new Item.Properties());

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Daggerlance.id(name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void init() {
    }

}
