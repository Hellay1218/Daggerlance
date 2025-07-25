package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceLootTableProvider extends FabricBlockLootTableProvider {
    public DaggerlanceLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(DaggerlanceBlocks.LANCIUM_BLOCK);
        addDrop(DaggerlanceBlocks.LANCIUM_BRICKS);
        addDrop(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
        addDrop(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
        addDrop(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
    }
}
