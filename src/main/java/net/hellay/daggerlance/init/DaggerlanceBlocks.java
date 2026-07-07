package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class DaggerlanceBlocks {

    public static final Block LANCIUM_BLOCK = register("lancium_block",Block::new,BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK),true,9);

    public static final Block LANCIUM_BRICKS = register("lancium_bricks", Block::new,BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.NETHERITE_BLOCK),true,4);

    public static final Block LANCIUM_BRICK_STAIRS = register("lancium_brick_stairs", properties -> new StairBlock(LANCIUM_BRICKS.defaultBlockState(), properties),BlockBehaviour.Properties.ofFullCopy(LANCIUM_BRICKS),true,6);

    public static final Block LANCIUM_BRICK_SLAB = register("lancium_brick_slab", SlabBlock::new,BlockBehaviour.Properties.ofFullCopy(LANCIUM_BRICKS),true,3);

    public static final Block LANCIUM_BRICK_WALL = register("lancium_brick_wall", WallBlock::new,BlockBehaviour.Properties.ofFullCopy(LANCIUM_BRICKS).forceSolidOn(),true,6);

    public static final Block LANCIUM_PILLAR = register("lancium_pillar",RotatedPillarBlock::new,BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.NETHERITE_BLOCK),true,6);

    /* - registry/helper functions - */

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem, int lanciumDropCount) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).component(DaggerlanceDataComponents.LANCIUM_BURN_DROP,lanciumDropCount).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Daggerlance.id(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Daggerlance.id(name));
    }

    public static void init() {

    }
}
