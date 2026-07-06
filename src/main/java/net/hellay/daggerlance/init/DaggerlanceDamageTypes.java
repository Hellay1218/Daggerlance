package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class DaggerlanceDamageTypes {

    public static final ResourceKey<DamageType> PARRY = ResourceKey.create(Registries.DAMAGE_TYPE, Daggerlance.id("parry"));

    public static void init() {}
}
