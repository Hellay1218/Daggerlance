package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceLootTableProvider extends FabricBlockLootTableProvider {

    public DaggerlanceLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(DaggerlanceBlocks.LANCIUM_BLOCK);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICKS);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
        dropSelf(DaggerlanceBlocks.LANCIUM_PILLAR);
    }
}
