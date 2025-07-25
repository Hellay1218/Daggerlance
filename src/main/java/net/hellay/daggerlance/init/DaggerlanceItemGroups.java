package net.hellay.daggerlance.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class DaggerlanceItemGroups {

    static {
        ItemGroup itemGroup = FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.daggerlance.daggerlance_group"))
                .icon(() -> new ItemStack(DaggerlanceItems.LANCIUM_INGOT))
                .entries((displayContext, entries) -> {
                    for(DaggerlanceItem.Skin skin : DaggerlanceItem.Skin.values()){
                        ItemStack stack = DaggerlanceItems.DAGGERLANCE.getDefaultStack();
                        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(), List.of(), List.of(skin.getSkinName()), List.of()));
                        entries.add(stack);
                    }
                    entries.add(DaggerlanceBlocks.LANCIUM_BLOCK);
                    entries.add(DaggerlanceBlocks.LANCIUM_BRICKS);
                    entries.add(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
                    entries.add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
                    entries.add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
                    entries.add(DaggerlanceItems.LANCIUM_INGOT);


                })
                .build();

        Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(Daggerlance.MOD_ID, "daggerlance_group"),
                itemGroup
        );
    }

    // called in the Daggerlance (Main) Class so the ItemGroups get registered (idk why im explaining these)
    public static void registerModItemGroups() {
        Daggerlance.LOGGER.info("Registering item group(s) for" + Daggerlance.MOD_NAME + "...");
    }

}
