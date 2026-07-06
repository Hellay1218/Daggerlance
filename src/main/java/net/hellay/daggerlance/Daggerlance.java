package net.hellay.daggerlance;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.hellay.daggerlance.event.WitherSkeletonDeathEvent;
import net.hellay.daggerlance.init.*;
import net.hellay.daggerlance.networking.ParryS2CPayload;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Daggerlance implements ModInitializer {
    public static final String MOD_ID = "daggerlance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    public static final TagKey<Item> DAGGERLANCE_DROPPING_WEAPON = TagKey.create(Registries.ITEM, id("drops_daggerlance"));
    public static final TagKey<Item> LANCIUM_MATERIAL = TagKey.create(Registries.ITEM, id("lancium_material"));
    public static final TagKey<Item> RUNE = TagKey.create(Registries.ITEM, id("rune"));

    public static final GameRule<Boolean> SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE = GameRuleBuilder.forBoolean(true).category(GameRuleCategory.DROPS).buildAndRegister(id("require_weapon_to_drop_daggerlance"));


    @Override
    public void onInitialize() {
        // registries
        DaggerlanceItems.init();
        DaggerlanceBlocks.init();
        DaggerlanceItemGroups.init();
        DaggerlanceParticles.init();
        DaggerlanceDataComponents.init();
        DaggerlanceDamageTypes.init();

        // events
        WitherSkeletonDeathEvent.init();
        PayloadTypeRegistry.playS2C().register(ParryS2CPayload.TYPE, ParryS2CPayload.CODEC);
    }
}