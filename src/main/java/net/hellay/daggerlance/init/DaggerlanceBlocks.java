package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.hellay.daggerlance.Daggerlance.MOD_ID;

public class DaggerlanceBlocks {

    public static final Block LANCIUM_BLOCK = registerBlock("lancium_block" , Block::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK));
    public static final Block LANCIUM_BRICKS = registerBlock("lancium_bricks" , Block::new, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK));
    public static final Block LANCIUM_BRICK_STAIRS = registerBlock("lancium_brick_stairs" , settings -> new StairsBlock(LANCIUM_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(LANCIUM_BRICKS));
    public static final Block LANCIUM_BRICK_SLAB = registerBlock("lancium_brick_slab" , SlabBlock::new, AbstractBlock.Settings.copy(LANCIUM_BRICKS));
    public static final Block LANCIUM_BRICK_WALL = registerBlock("lancium_brick_wall" , WallBlock::new, AbstractBlock.Settings.copy(LANCIUM_BRICKS).solid());

    public static Block registerBlock(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = (Block) factory.apply(settings.registryKey(blockKey(id)));
        registerBlockItem(id , block);
        return Registry.register(Registries.BLOCK, blockKey(id), block);
    }
    private static void registerBlockItem(String name , Block block){
        Registry.register(Registries.ITEM , RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name)) , new BlockItem(block , new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name)))));
    }

    public static RegistryKey<Block> blockKey(String id){
        return RegistryKey.of(RegistryKeys.BLOCK , Identifier.of(MOD_ID , id));
    }

    public static void registerModBlocks(){
        Daggerlance.LOGGER.info("Registering Blocks for " + Daggerlance.MOD_NAME);
    }
}
