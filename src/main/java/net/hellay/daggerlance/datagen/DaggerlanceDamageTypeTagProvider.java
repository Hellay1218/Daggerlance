package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.hellay.daggerlance.init.DaggerlanceDamageTypes;
import net.hellay.daggerlance.init.DaggerlanceTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class DaggerlanceDamageTypeTagProvider extends FabricTagsProvider<DamageType> {
    public DaggerlanceDamageTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_COOLDOWN).addOptionalElement(DaggerlanceDamageTypes.PARRY.identifier());
        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_ARMOR).addOptionalElement(DaggerlanceDamageTypes.PARRY.identifier());
        getOrCreateRawBuilder(DaggerlanceTags.UNPARRYABLE)
                .addOptionalTag(DamageTypeTags.BYPASSES_ARMOR.location())
                .addOptionalTag(DamageTypeTags.BYPASSES_INVULNERABILITY.location())
                .addOptionalTag(DamageTypeTags.IS_FALL.location())
                .addOptionalTag(DamageTypeTags.IS_FIRE.location())
                .addOptionalTag(DamageTypeTags.IS_DROWNING.location())
                .addOptionalTag(DamageTypeTags.BYPASSES_SHIELD.location())
                .addOptionalTag(DamageTypeTags.IS_LIGHTNING.location())
        ;

        getOrCreateRawBuilder(DaggerlanceTags.PARRYABLE).addOptionalTag(DamageTypeTags.IS_PLAYER_ATTACK.location()).addOptionalTag(DamageTypeTags.IS_MACE_SMASH.location()).addOptionalTag(DamageTypeTags.IS_PROJECTILE.location()).addOptionalElement(DamageTypes.MOB_ATTACK.identifier()).addOptionalElement(DamageTypes.SPEAR.identifier());
    }
}
