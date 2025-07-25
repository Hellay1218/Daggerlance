package net.hellay.daggerlance.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DaggerlanceToolTipEvent {

    public static void init() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, tooltip) -> {
            if (itemStack.isOf(DaggerlanceItems.DAGGERLANCE)) {
                String model = itemStack.getOrDefault(DataComponentTypes.CUSTOM_MODEL_DATA , new CustomModelDataComponent(List.of(), List.of(), List.of(DaggerlanceItem.Skin.DEFAULT.getSkinName()), List.of())).getString(0);
                DaggerlanceItem.Skin skin = DaggerlanceItem.Skin.skinFromString(model);
                if (skin.lore != null) {
                    if(Screen.hasShiftDown() || skin.lore.equals("tooltip.daggerlance.lore.default") ){
                        MutableText translatable = Text.translatable(skin.lore);
                        String[] var8 = translatable.getString().split("\n");

                        for (int i = 1; i < var8.length+1; i++) {
                            tooltip.add(1 , Text.literal(var8[var8.length-i]).setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
                        }
                    }
                    else{
                        tooltip.add(1,Text.literal(Text.translatable("tooltip.daggerlance.lore.hidden").getString()).setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
                    }
                }
                if (skin.tooltipName != null) {
                    tooltip.add(1,Text.literal(Text.translatable(skin.tooltipName).getString()).setStyle(Style.EMPTY.withColor(skin.color)));
                }
            }
        });
    }


}
