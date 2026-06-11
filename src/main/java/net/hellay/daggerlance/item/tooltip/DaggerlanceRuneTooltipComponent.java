package net.hellay.daggerlance.item.tooltip;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public record DaggerlanceRuneTooltipComponent(ItemStack stack) implements TooltipComponent {
    public static final Codec<DaggerlanceRuneTooltipComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("stack").forGetter(DaggerlanceRuneTooltipComponent::stack)
    ).apply(instance, DaggerlanceRuneTooltipComponent::new));

}
