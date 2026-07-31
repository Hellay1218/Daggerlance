package net.hellay.daggerlance.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.util.StringRepresentable;

public class DaggerlanceConfigMenu extends MidnightConfig {

    @Comment(centered = true, category = "client") public static String flash_config_title;

    @Client @Entry(category = "client")
    public static boolean render_parry_flash = true;

    @Condition(requiredOption = "daggerlance:render_parry_flash")
    @Client @Entry(category = "client", isColor = true)
    public static String parry_flash_colour = "#FFFFFF";

    @Comment(centered = true, category = "extra") public static String extra_configs;

    @Server @Entry
    public static double parry_damage_per_tick = 0.35;

    @Server @Entry
    public static int parry_timer_length = 15;

    @Server @Entry
    public static double parry_damage_modifer = 0.25;

    @Server @Entry
    public static ParryableSteps parryable = ParryableSteps.WHITELIST;

    public enum ParryableSteps implements StringRepresentable {
        ALL(0, "daggerlance.parry.all"),
        BLACKLIST(1, "daggerlance.parry.blacklist"),
        WHITELIST(2, "daggerlance.parry.whitelist");

        private final String translationKey;

        ParryableSteps(int id, String translationKey) {
            this.translationKey = translationKey;
        }

        @Override
        public String getSerializedName() {
            return this.translationKey;
        }
    }

}
