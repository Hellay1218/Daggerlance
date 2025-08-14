package net.hellay.daggerlance.mixin;

import net.hellay.daggerlance.init.DaggerlanceBlocks;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ItemStack drop = DaggerlanceItems.LANCIUM_INGOT.getDefaultStack();
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (itemEntity.getStack().getItem() == DaggerlanceItems.DAGGERLANCE && itemEntity.isOnFire()) {
            drop.setCount(itemEntity.getRandom().nextInt(4)+1);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
        else if (itemEntity.getStack().getItem() == DaggerlanceBlocks.LANCIUM_BLOCK.asItem() && itemEntity.isOnFire()) {
            drop.setCount(9);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
        else if (itemEntity.getStack().getItem() == DaggerlanceBlocks.LANCIUM_BRICKS.asItem() && itemEntity.isOnFire()) {
            drop.setCount(4);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
        else if (itemEntity.getStack().getItem() == DaggerlanceBlocks.LANCIUM_BRICK_STAIRS.asItem() && itemEntity.isOnFire()) {
            drop.setCount(7);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
        else if (itemEntity.getStack().getItem() == DaggerlanceBlocks.LANCIUM_BRICK_SLAB.asItem() && itemEntity.isOnFire()) {
            drop.setCount(3);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
        else if (itemEntity.getStack().getItem() == DaggerlanceBlocks.LANCIUM_BRICK_WALL.asItem() && itemEntity.isOnFire()) {
            drop.setCount(6);
            itemEntity.getWorld().spawnEntity(new ItemEntity(itemEntity.getWorld() , itemEntity.getX() , itemEntity.getY() , itemEntity.getZ() , drop));
            itemEntity.discard();
        }
    }
}