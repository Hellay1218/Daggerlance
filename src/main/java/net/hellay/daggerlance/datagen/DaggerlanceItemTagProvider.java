package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public DaggerlanceItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ItemTags.SWORDS)
                .add(DaggerlanceItems.DAGGERLANCE);
        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(DaggerlanceItems.LANCIUM_INGOT);
        valueLookupBuilder(Daggerlance.LANCIUM_MATERIAL).add(DaggerlanceItems.DAGGERLANCE, DaggerlanceItems.LANCIUM_INGOT,DaggerlanceItems.BLANK_RUNE,DaggerlanceItems.IMPACT_RUNE, DaggerlanceBlocks.LANCIUM_BLOCK.asItem(),DaggerlanceBlocks.LANCIUM_BRICK_STAIRS.asItem(),DaggerlanceBlocks.LANCIUM_BRICKS.asItem(), DaggerlanceBlocks.LANCIUM_BRICK_WALL.asItem(),DaggerlanceBlocks.LANCIUM_BRICK_SLAB.asItem(),DaggerlanceBlocks.LANCIUM_PILLAR.asItem());

    }
}
