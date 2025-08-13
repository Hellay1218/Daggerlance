package net.hellay.daggerlance.init;

import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static net.hellay.daggerlance.Daggerlance.MOD_ID;

public class DaggerlanceTrimMaterials {

    public static final RegistryKey<ArmorTrimMaterial> LANCIUM = RegistryKey.of(RegistryKeys.TRIM_MATERIAL , Identifier.of(MOD_ID , "lancium"));
    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey, Style style) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(
        ArmorTrimAssets.of(armorTrimKey.getValue().getPath()),
        Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));
        registerable.register(armorTrimKey, trimMaterial);
    }

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable ){
        register(registerable, LANCIUM, Style.EMPTY.withColor(TextColor.parse("#d59ae9").getOrThrow()));
    }


}
