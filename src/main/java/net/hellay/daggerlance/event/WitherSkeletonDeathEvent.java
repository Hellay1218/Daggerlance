package net.hellay.daggerlance.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.init.DaggerlanceTags;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class WitherSkeletonDeathEvent {
    public static void init() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((serverLevel, entity, livingEntity, damageSource) -> {
            if (livingEntity instanceof WitherSkeleton witherSkeleton && entity instanceof Player player && witherSkeleton.getMainHandItem().getItem() == DaggerlanceItems.DAGGERLANCE) {
                ItemStack playerMainHandItem = player.getMainHandItem();
                ItemStack playerOffHandItem = player.getOffhandItem();
                if ((!serverLevel.isClientSide() && serverLevel.getGameRules().get(Daggerlance.SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE)) && (playerMainHandItem.is(DaggerlanceTags.DAGGERLANCE_DROPPING_WEAPON) || playerOffHandItem.is(DaggerlanceTags.DAGGERLANCE_DROPPING_WEAPON))) {
                    witherSkeleton.spawnAtLocation(serverLevel,witherSkeleton.getMainHandItem());

                } else if ((!serverLevel.isClientSide() && !serverLevel.getGameRules().get(Daggerlance.SHOULD_REQUIRE_SPECIFIC_WEAPON_TO_DROP_DAGGERLANCE))) {
                    witherSkeleton.spawnAtLocation(serverLevel, witherSkeleton.getMainHandItem());
                }
            }
        });
    }
}
