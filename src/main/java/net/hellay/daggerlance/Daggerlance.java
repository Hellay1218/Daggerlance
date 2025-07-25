package net.hellay.daggerlance;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.hellay.daggerlance.event.WitherSkeletonDeathEvent;
import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItemGroups;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.event.DaggerlanceToolTipEvent;
import net.hellay.daggerlance.init.DaggerlanceParticles;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Daggerlance implements ModInitializer {
	public static final String MOD_ID = "daggerlance";
	public static final String MOD_NAME = "Daggerlance";

	public static final TagKey<Item> DAGGERLANCE_DROPPING_WEAPON = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "drops_daggerlance"));
//	public static final TagKey<Item> DROPS_LACNIUM_INGOT_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "drops_lancium_ingot"));
//	public static final TagKey<Block> DROPS_LACNIUM_INGOT_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "drops_lancium_ingot"));
	public static final GameRules.Key<GameRules.BooleanRule> SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE = GameRuleRegistry.register("requireWeaponToDropDaggerlance" , GameRules.Category.DROPS , GameRuleFactory.createBooleanRule(true));


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello fabric from " + MOD_NAME+"!");
		DaggerlanceItems.registerModItems();
		DaggerlanceBlocks.registerModBlocks();
		DaggerlanceItemGroups.registerModItemGroups();
		DaggerlanceParticles.registerModParticles();

		WitherSkeletonDeathEvent.init();
		DaggerlanceToolTipEvent.init();

	}
}