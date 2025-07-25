package net.hellay.daggerlance.mixin;

import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    protected WitherSkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract void initEquipment(Random random, LocalDifficulty localDifficulty);

    @Inject(
            method = {"initEquipment"},
            at = {@At("TAIL")}
    )
    protected void equipDaggerLanceOnWitherSkeleton(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        if (random.nextFloat() > 0.9F) {
            ItemStack stack = new ItemStack(DaggerlanceItems.DAGGERLANCE);
            stack.set(DataComponentTypes.CUSTOM_MODEL_DATA , new CustomModelDataComponent(List.of() , List.of() , List.of(DaggerlanceItem.Skin.values()[random.nextInt(DaggerlanceItem.Skin.values().length)].getSkinName()) , List.of()));
            this.equipStack(EquipmentSlot.MAINHAND, stack);
            this.setEquipmentDropChance(EquipmentSlot.MAINHAND , 0);
        }

    }

}
