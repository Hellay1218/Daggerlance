package net.hellay.daggerlance.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;


public class DaggerlanceItem extends Item {
    public DaggerlanceItem(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.7F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("entity_interaction_range"), 0.75, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("entity_interaction_range"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )

                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 0, true);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        String skin = context.getStack().getOrDefault(DataComponentTypes.CUSTOM_MODEL_DATA , new CustomModelDataComponent(List.of(), List.of(), List.of(DaggerlanceItem.Skin.DEFAULT.getSkinName()), List.of())).getString(0);

        if (state.isOf(Blocks.ANVIL) || state.isOf(Blocks.CHIPPED_ANVIL) || state.isOf(Blocks.DAMAGED_ANVIL) || state.isOf(Blocks.SMITHING_TABLE)) {
            if (!context.getWorld().isClient) {
                context.getStack().set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(), List.of(), List.of(Skin.getNextSkin(Skin.skinFromString(skin)).getSkinName()), List.of()));
                context.getWorld().playSound(context.getPlayer(), context.getBlockPos(), SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS);

                return ActionResult.SUCCESS;
            }
            //context.getWorld().addParticleClient(new DaggerlanceSweepParticleEffect(Skin.getNextSkin(Skin.skinFromString(skin)).color), 1.0D , 1.0d , 1.0d , 0.1d , 0.1d , 0.1d);
            context.getWorld().playSound(context.getPlayer(), context.getBlockPos(), SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS);
        }
        return super.useOnBlock(context);
    }

    public enum Skin {
        DEFAULT(-1, null, "tooltip.daggerlance.lore.default"),
        GOLD(16100912, "tooltip.daggerlance.name.gold", null),
        MOON(13883641, "tooltip.daggerlance.name.moon", null),
        ROSE(16732311, "tooltip.daggerlance.name.rose", null),
        JADE(8056170, "tooltip.daggerlance.name.jade", null),
        VANA(10510945, "tooltip.daggerlance.name.vana", "tooltip.daggerlance.lore.vana");

        public final int color;
        public final @Nullable String tooltipName;
        public final @Nullable String lore;

        Skin(int color, @Nullable String tooltipName, @Nullable String lore) {
            this.color = color;
            this.lore = lore;
            this.tooltipName = tooltipName;
        }

        public String getSkinName() {
            if (this.name().toLowerCase(Locale.ROOT).equals("default")) {
                return "daggerlance";
            }
            return this.name().toLowerCase(Locale.ROOT);
        }

        public static Skin getNextSkin(Skin skin) {
            Skin[] values = values();
            return values[(skin.ordinal() + 1) % values.length];
        }

        public static Skin skinFromString(String name) {
            Skin[] var1 = values();

            for (Skin skin : var1) {
                if (skin.getSkinName().equalsIgnoreCase(name)) {
                    return skin;
                }
            }

            return Skin.DEFAULT;
        }

    }
}
