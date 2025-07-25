package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DaggerlanceBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(DaggerlanceBlocks.LANCIUM_BLOCK)
                .add(DaggerlanceBlocks.LANCIUM_BRICKS)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB)
                .add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(DaggerlanceBlocks.LANCIUM_BLOCK);


        getOrCreateTagBuilder(BlockTags.STAIRS).add(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).add(DaggerlanceBlocks.LANCIUM_BRICK_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS).add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
        getOrCreateTagBuilder(BlockTags.WALLS).add(DaggerlanceBlocks.LANCIUM_BRICK_WALL);
    }
}
