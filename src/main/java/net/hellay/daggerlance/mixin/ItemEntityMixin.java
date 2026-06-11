package net.hellay.daggerlance.mixin;

import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceDataComponents;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ItemStack drop = DaggerlanceItems.LANCIUM_INGOT.getDefaultInstance();
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        ItemStack item = itemEntity.getItem();
        if (itemEntity.getItem().is(Daggerlance.LANCIUM_MATERIAL)) {
            if (itemEntity.isOnFire() && (item.has(DaggerlanceDataComponents.LANCIUM_BURN_DROP) || item.is(DaggerlanceItems.DAGGERLANCE))) {
                int count = item.is(DaggerlanceItems.DAGGERLANCE) ? itemEntity.getRandom().nextIntBetweenInclusive(3,5) : item.getOrDefault(DaggerlanceDataComponents.LANCIUM_BURN_DROP,0);
                drop.setCount(count);
                itemEntity.level().addFreshEntity(new ItemEntity(itemEntity.level(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), drop));
                itemEntity.discard();

            } else if (item.getItem() == DaggerlanceItems.BLANK_RUNE && itemEntity.level().getBlockState(itemEntity.blockPosition()).getBlock() instanceof FireBlock) {
                if (itemEntity.level() instanceof ServerLevel serverLevel) {
                    AABB box = itemEntity.getBoundingBox().inflate(3);
                    List<ItemEntity> entities = serverLevel.getEntitiesOfClass(ItemEntity.class,box);

                    for (ItemEntity entity : entities) {
                        if (entity.getItem().is(Items.BLAZE_ROD) && entity.isOnFire()) {
                            itemEntity.level().removeBlock(itemEntity.blockPosition(),false);
                            entity.discard();
                            Containers.dropItemStack(itemEntity.level(),itemEntity.getX(),itemEntity.getY(),itemEntity.getZ(),new ItemStack(DaggerlanceItems.IMPACT_RUNE));
                            serverLevel.sendParticles(ParticleTypes.SMALL_FLAME,entity.getX(),entity.getY(),entity.getZ(),12,serverLevel.getRandom().nextInt() / 20.0,0,serverLevel.getRandom().nextInt() / 20.0,0);
                            serverLevel.playSound(itemEntity,itemEntity.getX(),itemEntity.getY(),itemEntity.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,2,2);
                            serverLevel.playSound(itemEntity,itemEntity.getX(),itemEntity.getY(),itemEntity.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS,2,2);

                            itemEntity.discard();
                        }
                    }
                }
            }
        }
    }
}