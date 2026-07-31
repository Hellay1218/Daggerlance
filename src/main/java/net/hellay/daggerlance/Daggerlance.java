package net.hellay.daggerlance;

import eu.midnightdust.lib.config.MidnightConfig;
import eu.midnightdust.lib.config.MidnightConfigScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.hellay.daggerlance.config.DaggerlanceConfigMenu;
import net.hellay.daggerlance.event.WitherSkeletonDeathEvent;
import net.hellay.daggerlance.init.*;
import net.hellay.daggerlance.networking.ParryS2CPayload;
import net.minecraft.client.gui.screens.Screen;
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
    public static final GameRule<Boolean> SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE = GameRuleBuilder.forBoolean(true).category(GameRuleCategory.DROPS).buildAndRegister(id("require_weapon_to_drop_daggerlance"));

    public static boolean enchancementLoaded = false;

    @Override
    public void onInitialize() {
        //Daggerlance.enchancementLoaded = FabricLoader.getInstance().isModLoaded("enchancement");

        // registries
        DaggerlanceItems.init();
        DaggerlanceBlocks.init();
        DaggerlanceItemGroups.init();
        DaggerlanceParticles.init();
        DaggerlanceDataComponents.init();
        DaggerlanceDamageTypes.init();
        DaggerlanceTags.init();

        // events
        WitherSkeletonDeathEvent.init();
        PayloadTypeRegistry.clientboundPlay().register(ParryS2CPayload.TYPE, ParryS2CPayload.CODEC);

        MidnightConfig.init(MOD_ID, DaggerlanceConfigMenu.class);
    }
}