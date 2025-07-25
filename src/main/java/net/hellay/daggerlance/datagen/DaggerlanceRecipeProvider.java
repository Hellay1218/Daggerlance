package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceRecipeProvider extends FabricRecipeProvider {
    public DaggerlanceRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                createShapeless(RecipeCategory.MISC , DaggerlanceItems.LANCIUM_INGOT)
                        .input(Items.NETHERITE_SCRAP , 4)
                        .input(Items.OBSIDIAN, 4)
                        .criterion(hasItem(Items.NETHERITE_SCRAP) , conditionsFromItem(Items.NETHERITE_SCRAP))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC , DaggerlanceBlocks.LANCIUM_BLOCK)
                        .input(DaggerlanceItems.LANCIUM_INGOT , 9)
                        .criterion(hasItem(DaggerlanceItems.LANCIUM_INGOT) , conditionsFromItem(DaggerlanceItems.LANCIUM_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_BRICKS)
                        .pattern("##")
                        .pattern("##")
                        .input('#' , DaggerlanceItems.LANCIUM_INGOT)
                        .criterion(hasItem(DaggerlanceItems.LANCIUM_INGOT) , conditionsFromItem(DaggerlanceItems.LANCIUM_INGOT))
                        .offerTo(exporter);

                createStairsRecipe(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS , Ingredient.ofItem(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .criterion(hasItem(DaggerlanceBlocks.LANCIUM_BRICKS) , conditionsFromItem(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .offerTo(exporter);

                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_BRICK_SLAB , Ingredient.ofItem(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .criterion(hasItem(DaggerlanceBlocks.LANCIUM_BRICKS) , conditionsFromItem(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.DECORATIONS,DaggerlanceBlocks.LANCIUM_BRICK_WALL)
                        .pattern("###")
                        .pattern("###")
                        .input('#' , DaggerlanceBlocks.LANCIUM_BRICKS)
                        .criterion(hasItem(DaggerlanceBlocks.LANCIUM_BRICKS) , conditionsFromItem(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "DaggerlanceRecipeProvider";
    }
}
