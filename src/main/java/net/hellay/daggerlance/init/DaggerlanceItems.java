package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.hellay.daggerlance.Daggerlance.MOD_ID;

import java.util.function.Function;

public class DaggerlanceItems {

    public static final Item LANCIUM_INGOT = registerItem("lancium_ingot" , Item::new , new Item.Settings()
            .trimMaterial(DaggerlanceTrimMaterials.LANCIUM)
            .fireproof());
    public static final Item DAGGERLANCE = registerItem("daggerlance", DaggerlanceItem::new , new Item.Settings()
            .rarity(Rarity.RARE)
            .component(DataComponentTypes.TOOL, DaggerlanceItem.createToolComponent())
            .repairable(DaggerlanceItems.LANCIUM_INGOT)
            .attributeModifiers(DaggerlanceItem.createAttributeModifiers())
            .enchantable(15)
            .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
            .maxCount(1)
            //.fireproof()

    );
    public static Item registerItem(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = (Item)factory.apply(settings.registryKey(itemKeyOf(id)));
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, itemKeyOf(id), item);
    }

    //Gives Back the Key For Registering An Item
    public static RegistryKey<Item> itemKeyOf(String id){
        return RegistryKey.of(RegistryKeys.ITEM , Identifier.of(MOD_ID , id));
    }

    // called in the Daggerlance (Main) Class so the Items get registered (idk why im explaining this)
    public static void registerModItems() {
        Daggerlance.LOGGER.info("Registering items for" + Daggerlance.MOD_NAME + "...");
    }

}
