package net.hellay.daggerlance.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.hellay.daggerlance.client.hud.DaggerlanceTooltipComponent;
import net.hellay.daggerlance.init.DaggerlanceDataComponents;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.hellay.daggerlance.item.RuneItem;
import net.hellay.daggerlance.item.tooltip.DaggerlanceRuneTooltipComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class DaggerlanceToolTipEvent {

    public static void init() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, tooltip) -> {
            Minecraft client = Minecraft.getInstance();
            if (itemStack.is(DaggerlanceItems.DAGGERLANCE)) {
                String model = itemStack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, DaggerlanceItem.DEFAULT_MODEL_DATA).getString(0);
                DaggerlanceItem.Skin skin = DaggerlanceItem.Skin.skinFromString(model);
                if (skin.lore != null) {
                    if (client.hasShiftDown() || skin.lore.equals("tooltip.daggerlance.lore.default")) {
                        MutableComponent translatable = Component.translatable(skin.lore);
                        String[] var8 = translatable.getString().split("\n");

                        for (int i = 1; i < var8.length + 1; i++) {
                            tooltip.add(1, Component.literal(var8[var8.length - i]).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                        }
                    } else {
                        tooltip.add(1, Component.literal(Component.translatable("tooltip.daggerlance.lore.hidden").getString()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                    }
                }
                if (skin.tooltipName != null) {
                    tooltip.add(1, Component.literal(Component.translatable(skin.tooltipName).getString()).setStyle(Style.EMPTY.withColor(skin.color)));
                }
            }

            if (itemStack.getItem() instanceof RuneItem) {
                String runeId = itemStack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE_DATA,"empty");
                tooltip.add(1, Component.translatable("tooltip.daggerlance.rune." + runeId).withStyle(ChatFormatting.GRAY));

                if (Minecraft.getInstance().hasShiftDown()) {
                    tooltip.add(2, Component.translatable("tooltip.daggerlance.rune." + runeId + ".explain").withStyle(ChatFormatting.DARK_GRAY));
                } else {
                    tooltip.add(2, Component.translatable("tooltip.daggerlance.lore.hidden").withStyle(ChatFormatting.DARK_GRAY));
                }

            }
        });

        TooltipComponentCallback.EVENT.register((a) -> a instanceof DaggerlanceRuneTooltipComponent(
                net.minecraft.world.item.ItemStack stack
        ) ? new DaggerlanceTooltipComponent(stack) : null);

    }


}
