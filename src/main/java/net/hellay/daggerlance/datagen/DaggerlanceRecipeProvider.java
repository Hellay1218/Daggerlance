package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceRecipeProvider extends FabricRecipeProvider {

    public DaggerlanceRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "DaggerlanceRecipeProvider";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, DaggerlanceItems.LANCIUM_INGOT)
                        .requires(Items.NETHERITE_SCRAP, 4)
                        .requires(Items.OBSIDIAN, 4)
                        .unlockedBy(getHasName((Items.NETHERITE_SCRAP)), has(Items.NETHERITE_SCRAP))
                        .save(recipeOutput);

                shapeless(RecipeCategory.MISC, DaggerlanceBlocks.LANCIUM_BLOCK)
                        .requires(DaggerlanceItems.LANCIUM_INGOT, 9)
                        .unlockedBy(getHasName(DaggerlanceItems.LANCIUM_INGOT), has(DaggerlanceItems.LANCIUM_INGOT))
                        .save(recipeOutput);

                shaped(RecipeCategory.BUILDING_BLOCKS, DaggerlanceBlocks.LANCIUM_BRICKS)
                        .pattern("##")
                        .pattern("##")
                        .define('#', DaggerlanceItems.LANCIUM_INGOT)
                        .unlockedBy(getHasName(DaggerlanceItems.LANCIUM_INGOT), has(DaggerlanceItems.LANCIUM_INGOT))
                        .save(recipeOutput);

                stairBuilder(DaggerlanceBlocks.LANCIUM_BRICK_STAIRS, Ingredient.of(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .unlockedBy(getHasName(DaggerlanceBlocks.LANCIUM_BRICKS), has(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .save(recipeOutput);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, DaggerlanceBlocks.LANCIUM_BRICK_SLAB, Ingredient.of(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .unlockedBy(getHasName(DaggerlanceBlocks.LANCIUM_BRICKS), has(DaggerlanceBlocks.LANCIUM_BRICKS))
                        .save(recipeOutput);

                wall(RecipeCategory.BUILDING_BLOCKS, DaggerlanceBlocks.LANCIUM_BRICK_WALL, DaggerlanceBlocks.LANCIUM_BRICKS);

                shaped(RecipeCategory.MISC, DaggerlanceItems.BLANK_RUNE)
                        .pattern(" # ")
                        .pattern(" # ")
                        .define('#', DaggerlanceItems.LANCIUM_INGOT)
                        .unlockedBy(getHasName(DaggerlanceItems.LANCIUM_INGOT), has(DaggerlanceItems.LANCIUM_INGOT))
                        .save(recipeOutput);

                shaped(RecipeCategory.MISC, DaggerlanceBlocks.LANCIUM_PILLAR)
                        .pattern(" # ")
                        .pattern(" # ")
                        .define('#', DaggerlanceBlocks.LANCIUM_BRICK_SLAB)
                        .unlockedBy(getHasName(DaggerlanceBlocks.LANCIUM_BRICK_SLAB), has(DaggerlanceBlocks.LANCIUM_BRICK_SLAB))
                        .save(recipeOutput);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_PILLAR,DaggerlanceBlocks.LANCIUM_BRICKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_BRICK_SLAB,DaggerlanceBlocks.LANCIUM_BRICKS,2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_BRICK_WALL,DaggerlanceBlocks.LANCIUM_BRICKS,1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,DaggerlanceBlocks.LANCIUM_BRICK_STAIRS,DaggerlanceBlocks.LANCIUM_BRICKS,1);

            }
        };
    }
}
