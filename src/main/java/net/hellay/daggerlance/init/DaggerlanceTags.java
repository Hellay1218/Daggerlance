package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;

public class DaggerlanceTags {
    public static final TagKey<DamageType> UNPARRYABLE = TagKey.create(Registries.DAMAGE_TYPE, Daggerlance.id("unparryable"));
    public static final TagKey<DamageType> PARRYABLE = TagKey.create(Registries.DAMAGE_TYPE, Daggerlance.id("parryable"));
    public static final TagKey<Item> DAGGERLANCE_DROPPING_WEAPON = TagKey.create(Registries.ITEM, Daggerlance.id("drops_daggerlance"));
    public static final TagKey<Item> LANCIUM_MATERIAL = TagKey.create(Registries.ITEM, Daggerlance.id("lancium_material"));
    public static final TagKey<Item> RUNE = TagKey.create(Registries.ITEM, Daggerlance.id("rune"));

    public static void init() {}

}
