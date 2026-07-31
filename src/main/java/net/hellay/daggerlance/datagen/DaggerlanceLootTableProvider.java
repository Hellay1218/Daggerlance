package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceLootTableProvider extends FabricBlockLootSubProvider {


    public DaggerlanceLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(DaggerlanceBlocks.LANCIUM_BLOCK);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICKS);
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
        add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB,createSlabItemTable(DaggerlanceBlocks.LANCIUM_BRICK_SLAB));
        dropSelf(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
        dropSelf(DaggerlanceBlocks.LANCIUM_PILLAR);
    }
}
