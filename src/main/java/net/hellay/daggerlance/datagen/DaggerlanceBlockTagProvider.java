package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public DaggerlanceBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DaggerlanceBlocks.LANCIUM_BLOCK)
                .add(DaggerlanceBlocks.LANCIUM_BRICKS)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);

        valueLookupBuilder(BlockTags.STAIRS).add(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
        valueLookupBuilder(BlockTags.WALLS).add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);

    }
}
