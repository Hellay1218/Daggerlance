package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class DaggerlanceModelProvider extends FabricModelProvider {
    public DaggerlanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(DaggerlanceBlocks.LANCIUM_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(DaggerlanceBlocks.LANCIUM_BRICKS)
                .stairs(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS)
                .slab(DaggerlanceBlocks.LANCIUM_BRICK_SLAB)
                .wall(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DaggerlanceItems.LANCIUM_INGOT , Models.GENERATED);
    }
}
