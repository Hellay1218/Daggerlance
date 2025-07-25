package net.hellay.daggerlance.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class WitherSkeletonDeathEvent {
    public static void init() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            if(killedEntity instanceof WitherSkeletonEntity witherSkeleton && entity instanceof PlayerEntity player && witherSkeleton.getMainHandStack().getItem() == DaggerlanceItems.DAGGERLANCE){
                ItemStack playerMainHandItem = player.getMainHandStack();
                ItemStack playerOffHandItem = player.getOffHandStack();
                if((!world.isClient() && world.getGameRules().getBoolean(Daggerlance.SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE)) && (playerMainHandItem.isIn(Daggerlance.DAGGERLANCE_DROPPING_WEAPON) || playerOffHandItem.isIn(Daggerlance.DAGGERLANCE_DROPPING_WEAPON))){
                    witherSkeleton.dropStack(world ,witherSkeleton.getMainHandStack());
                }

                else if ((!world.isClient() && !world.getGameRules().getBoolean(Daggerlance.SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE))){
                    witherSkeleton.dropStack(world, witherSkeleton.getMainHandStack());
                }
            }
        });
    }
}
