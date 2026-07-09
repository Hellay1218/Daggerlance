package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class DaggerlanceModelProvider extends FabricModelProvider {

    public DaggerlanceModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(DaggerlanceBlocks.LANCIUM_BLOCK);

        blockModelGenerators.family(DaggerlanceBlocks.LANCIUM_BRICKS)
                .stairs(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS)
                .slab(DaggerlanceBlocks.LANCIUM_BRICK_SLAB)
                .wall(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.createFlatItemModel(DaggerlanceItems.LANCIUM_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DaggerlanceItems.BLANK_RUNE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(DaggerlanceItems.IMPACT_RUNE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(DaggerlanceItems.FEEDBACK_RUNE, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
