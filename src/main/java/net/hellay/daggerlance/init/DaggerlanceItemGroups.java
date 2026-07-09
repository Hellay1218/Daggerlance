package net.hellay.daggerlance.init;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomModelData;

import java.util.List;

public class DaggerlanceItemGroups {

    static {
        CreativeModeTab tab = FabricCreativeModeTab.builder()
                .title(Component.translatable("itemgroup.daggerlance.daggerlance_group"))
                .icon(() -> new ItemStack(DaggerlanceItems.LANCIUM_INGOT))
                .displayItems((displayContext, entries) -> {
                    for (DaggerlanceItem.Skin skin : DaggerlanceItem.Skin.values()) {
                        ItemStack stack = DaggerlanceItems.DAGGERLANCE.getDefaultInstance();
                        stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(), List.of(), List.of(skin.getSkinName()), List.of()));
                        entries.accept(stack);
                    }
                    entries.accept(DaggerlanceBlocks.LANCIUM_BLOCK);
                    entries.accept(DaggerlanceBlocks.LANCIUM_BRICKS);
                    entries.accept(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
                    entries.accept(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
                    entries.accept(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
                    entries.accept(DaggerlanceBlocks.LANCIUM_PILLAR);
                    entries.accept(DaggerlanceItems.LANCIUM_INGOT);
                    entries.accept(DaggerlanceItems.BLANK_RUNE);
                    entries.accept(DaggerlanceItems.IMPACT_RUNE);
                    entries.accept(DaggerlanceItems.FEEDBACK_RUNE);
                })
                .build();

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                Daggerlance.id("daggerlance_group"),
                tab
        );
    }

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.insertAfter(Items.NETHERITE_INGOT, DaggerlanceItems.LANCIUM_INGOT);
            entries.accept(DaggerlanceItems.BLANK_RUNE);
            entries.accept(DaggerlanceItems.IMPACT_RUNE);
            entries.accept(DaggerlanceItems.FEEDBACK_RUNE);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.insertAfter(Items.NETHERITE_SWORD, DaggerlanceItems.DAGGERLANCE);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.insertAfter(Items.NETHERITE_BLOCK,DaggerlanceBlocks.LANCIUM_BLOCK);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.insertBefore(Items.AMETHYST_BLOCK,DaggerlanceBlocks.LANCIUM_PILLAR,DaggerlanceBlocks.LANCIUM_BRICKS, DaggerlanceBlocks.LANCIUM_BRICK_STAIRS,DaggerlanceBlocks.LANCIUM_BRICK_WALL,DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
        });
    }

}
