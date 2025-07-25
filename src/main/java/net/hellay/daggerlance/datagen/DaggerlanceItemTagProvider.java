package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public DaggerlanceItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(DaggerlanceItems.DAGGERLANCE);
        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(DaggerlanceItems.LANCIUM_INGOT);
    }
}
