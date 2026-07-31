package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.init.DaggerlanceTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public DaggerlanceItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ItemTags.SWORDS)
                .add(DaggerlanceItems.DAGGERLANCE);
        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(DaggerlanceItems.LANCIUM_INGOT);
        valueLookupBuilder(DaggerlanceTags.LANCIUM_MATERIAL).add(DaggerlanceItems.DAGGERLANCE, DaggerlanceItems.LANCIUM_INGOT,DaggerlanceItems.BLANK_RUNE,DaggerlanceItems.IMPACT_RUNE, DaggerlanceItems.FEEDBACK_RUNE, DaggerlanceBlocks.LANCIUM_BLOCK.asItem(),DaggerlanceBlocks.LANCIUM_BRICK_STAIRS.asItem(),DaggerlanceBlocks.LANCIUM_BRICKS.asItem(), DaggerlanceBlocks.LANCIUM_BRICK_WALL.asItem(),DaggerlanceBlocks.LANCIUM_BRICK_SLAB.asItem(),DaggerlanceBlocks.LANCIUM_PILLAR.asItem());
        valueLookupBuilder(DaggerlanceTags.RUNE).add(DaggerlanceItems.IMPACT_RUNE).add(DaggerlanceItems.FEEDBACK_RUNE);
    }
}
