package net.hellay.daggerlance.init;

import net.hellay.daggerlance.Daggerlance;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class DaggerlanceTrimMaterials {

    public static final ResourceKey<TrimMaterial> LANCIUM = ResourceKey.create(Registries.TRIM_MATERIAL, Daggerlance.id("lancium"));

    private static void register(BootstrapContext<TrimMaterial> registerable, ResourceKey<TrimMaterial> armorTrimKey, Style style) {
        TrimMaterial trimMaterial = new TrimMaterial(
                MaterialAssetGroup.create(armorTrimKey.identifier().getPath()),
                Component.translatable(Util.makeDescriptionId("trim_material", armorTrimKey.identifier())).withStyle(style));
        registerable.register(armorTrimKey, trimMaterial);
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> registerable) {
        register(registerable, LANCIUM, Style.EMPTY.withColor(TextColor.parseColor("#d59ae9").getOrThrow()));
    }
}