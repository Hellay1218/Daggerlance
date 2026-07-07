package net.hellay.daggerlance.item;

import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceParticles;
import net.hellay.daggerlance.item.tooltip.DaggerlanceRuneTooltipComponent;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class DaggerlanceItem extends SingleSlotAbilityItem {

    public final static CustomModelData DEFAULT_MODEL_DATA = new CustomModelData(List.of(), List.of(), List.of(DaggerlanceItem.Skin.DEFAULT.getSkinName()), List.of());
    public final static String IMPACT_RUNE_ID = "impact";
    public final static String FEEDBACK_RUNE_ID = "feedback";


    public DaggerlanceItem(Properties properties) {
        super(properties);
    }

    public static Skin getSkin(ItemStack itemStack) {
        String skin = itemStack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, DEFAULT_MODEL_DATA).getString(0);
        return Skin.skinFromString(skin);
    }

    public static void setSkin(ItemStack stack, Skin skin) {
        stack.set(DataComponents.CUSTOM_MODEL_DATA,new CustomModelData(List.of(),List.of(),List.of(skin.getSkinName()),List.of()));
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        return livingEntity instanceof Player player && !player.isCreative();
    }

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 7.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -2.7F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(Daggerlance.id("entity_interaction_range"), 0.75, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(Daggerlance.id("entity_interaction_range"), 1.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ).build();
    }

    public static Tool createToolComponent() {
        HolderGetter<Block> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(List.of(
                Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                Tool.Rule.overrideSpeed(holderGetter.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                Tool.Rule.overrideSpeed(holderGetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
        ), 1.0F, 2, true);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (hasRune(IMPACT_RUNE_ID,stack) && !player.getCooldowns().isOnCooldown(stack)) {
            Vec3 playerDelta = player.getDeltaMovement();
            Vec3 vel = player.getViewVector(1.0f).normalize().reverse().multiply(playerDelta.length(),playerDelta.length(),playerDelta.length()).multiply(3,2.5,3);

            player.getCooldowns().addCooldown(stack,20 * 5);
            player.playSound(SoundEvents.ANVIL_PLACE,1,0.08f);

            player.push(vel.x,vel.y,vel.z);
            player.needsSync = true;
            livingEntity.push(player.position().subtract(livingEntity.position()).multiply(-0.05,-0.05,-0.05));
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        return Optional.of(new DaggerlanceRuneTooltipComponent(itemStack));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (state.is(Blocks.ANVIL) || state.is(Blocks.CHIPPED_ANVIL) || state.is(Blocks.DAMAGED_ANVIL) || state.is(Blocks.SMITHING_TABLE)) {
            if (!level.isClientSide()) {
                setSkin(stack,Skin.getNextSkin(getSkin(stack)));
                level.playSound(context.getPlayer(), pos, SoundEvents.SMITHING_TABLE_USE, SoundSource.BLOCKS);

                return InteractionResult.SUCCESS;
            }
            level.playSound(context.getPlayer(), pos, SoundEvents.SMITHING_TABLE_USE, SoundSource.BLOCKS);
        }
        return super.useOn(context);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (getRune(stack) != "empty" && getRune(stack) != "blank") {
            if (getRune(stack).equals(FEEDBACK_RUNE_ID)) {
                player.startUsingItem(interactionHand);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 20;
    }

    @Override
    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        if (livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(itemStack, 20 * 4);
        }
        return super.releaseUsing(itemStack, level, livingEntity, i);
    }


    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @org.jspecify.annotations.Nullable EquipmentSlot equipmentSlot) {
        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getUseItemRemainingTicks() <= 1 && livingEntity.isUsingItem()) {
                    itemStack.releaseUsing(serverLevel,livingEntity,0);
            }
        }
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BLOCK;
    }

    public enum Skin {
        DEFAULT(-1, null, "tooltip.daggerlance.lore.default", DaggerlanceParticles.DAGGERLANCE_SWEEP_PARTICLE_TYPE),
        GOLD(16100912, "tooltip.daggerlance.name.gold", null, DaggerlanceParticles.ROYALTY_SWEEP_PARTICLE_TYPE),
        MOON(13883641, "tooltip.daggerlance.name.moon", null, DaggerlanceParticles.MOON_SWEEP_PARTICLE_TYPE),
        ROSE(16732311, "tooltip.daggerlance.name.rose", null, DaggerlanceParticles.ROSE_SWEEP_PARTICLE_TYPE),
        JADE(8056170, "tooltip.daggerlance.name.jade", null, DaggerlanceParticles.JADE_SWEEP_PARTICLE_TYPE),
        VANA(10510945, "tooltip.daggerlance.name.vana", "tooltip.daggerlance.lore.vana", DaggerlanceParticles.VANA_SWEEP_PARTICLE_TYPE);

        public final int color;
        public final @Nullable String tooltipName;
        public final @Nullable String lore;
        public final SimpleParticleType sweepParticle;

        Skin(int color, @Nullable String tooltipName, @Nullable String lore, SimpleParticleType sweepParticle) {
            this.color = color;
            this.lore = lore;
            this.tooltipName = tooltipName;
            this.sweepParticle = sweepParticle;
        }

        public SimpleParticleType getSweepParticle() {
            return this.sweepParticle;
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
