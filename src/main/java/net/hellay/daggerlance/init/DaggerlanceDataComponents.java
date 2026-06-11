package net.hellay.daggerlance.init;

import com.mojang.serialization.Codec;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.client.hud.DaggerlanceTooltipComponent;
import net.hellay.daggerlance.item.DaggerlanceItem;
import net.hellay.daggerlance.item.tooltip.DaggerlanceRuneTooltipComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

import java.util.function.UnaryOperator;

public class DaggerlanceDataComponents {
    public static final DataComponentType<ItemStack> DAGGERLANCE_RUNE = register("daggerlance_rune",
            builder -> builder.persistent(ItemStack.CODEC));

    public static final DataComponentType<String> DAGGERLANCE_RUNE_DATA = register("daggerlance_rune_data",
            builder -> builder.persistent(Codec.STRING));

    public static final DataComponentType<Integer> LANCIUM_BURN_DROP = register("lancium_burn_drop",
            builder -> builder.persistent(Codec.INT));

    public static final DataComponentType<DaggerlanceRuneTooltipComponent> RUNE_DISPLAY_COMPONENT = register("rune_display_component",
            daggerlanceRuneTooltipComponentBuilder -> daggerlanceRuneTooltipComponentBuilder.persistent(DaggerlanceRuneTooltipComponent.CODEC));

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Daggerlance.id(name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void init() {
    }
}