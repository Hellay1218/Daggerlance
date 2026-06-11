package net.hellay.daggerlance.mixin;

import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(WitherSkeleton.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeleton {


    protected WitherSkeletonEntityMixin(net.minecraft.world.entity.EntityType<? extends AbstractSkeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
            method = {"populateDefaultEquipmentSlots"},
            at = {@At("TAIL")}
    )
    protected void equipDaggerLanceOnWitherSkeleton(RandomSource randomSource, DifficultyInstance difficultyInstance, CallbackInfo ci) {
        if (random.nextFloat() > 0.9F) {
            ItemStack stack = new ItemStack(DaggerlanceItems.DAGGERLANCE);
            DaggerlanceItem.setSkin(stack,DaggerlanceItem.Skin.values()[random.nextInt(DaggerlanceItem.Skin.values().length)]);
            this.setItemSlot(EquipmentSlot.MAINHAND, stack);
            this.setDropChance(EquipmentSlot.MAINHAND, 0);
        }
    }

}
