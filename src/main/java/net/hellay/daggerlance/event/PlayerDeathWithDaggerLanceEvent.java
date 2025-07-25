package net.hellay.daggerlance.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class PlayerDeathWithDaggerLanceEvent {
    public static void init() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            if(killedEntity instanceof PlayerEntity effected && entity instanceof PlayerEntity killer && killer.getMainHandStack().getItem() == DaggerlanceItems.DAGGERLANCE && !world.isClient){
                killer.sendMessage(Text.literal(effected.getName() + "Got Lanced by " + killer.getName()) , false);
            }
        });
    }
}
