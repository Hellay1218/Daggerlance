package net.hellay.daggerlance.item;

import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceDataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


    // All rights reserved (C) InfinityFarzad


public class SingleSlotAbilityItem extends Item {

    public SingleSlotAbilityItem(Properties settings) {
        super(settings.component(DaggerlanceDataComponents.DAGGERLANCE_RUNE, ItemStack.EMPTY));
    }

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction clickType, Player player, SlotAccess cursorStackReference) {
        ItemStack stored = stack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE, ItemStack.EMPTY);
        if (slot.getItem().get(DaggerlanceDataComponents.DAGGERLANCE_RUNE) == null || !slot.allowModification(player)) {
            return false;
        } else {
            if (!player.getCooldowns().isOnCooldown(stack) && stored.isEmpty() && !cursorStackReference.get().isEmpty() && (Daggerlance.enchancementLoaded ? clickType.equals(ClickAction.SECONDARY) : clickType.equals(ClickAction.PRIMARY)) && shouldITakeAndShoveThisItemInMyItemSlot(cursorStackReference.get())) {
                stack.set(DaggerlanceDataComponents.DAGGERLANCE_RUNE, cursorStackReference.get());
                if (cursorStackReference.get().getCount() > 1) {
                    ItemStack newStack = cursorStackReference.get();
                    newStack.shrink(1);
                    cursorStackReference.set(newStack);
                }
                cursorStackReference.set(ItemStack.EMPTY);

                onContentChanged(player);
                playSlotEmptySound(player,stack);
                player.getCooldowns().addCooldown(stack, 10);
                return true;
            } else if (!player.getCooldowns().isOnCooldown(stack) && !stored.isEmpty() && cursorStackReference.get().isEmpty() && clickType == ClickAction.SECONDARY) {
                cursorStackReference.set(stored);
                stack.set(DaggerlanceDataComponents.DAGGERLANCE_RUNE, ItemStack.EMPTY);
                onContentChanged(player);
                playSlotingSound(player,stack);
                player.getCooldowns().addCooldown(stack, 10);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction clickType, Player player) {
        ItemStack stored = stack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE, ItemStack.EMPTY);
        if (slot.getItem().get(DaggerlanceDataComponents.DAGGERLANCE_RUNE) == null) {
            return false;
        } else {
            if (stored.isEmpty() && clickType.equals(ClickAction.SECONDARY)) {
                return true;
            } else if (!stored.isEmpty() && clickType.equals(ClickAction.PRIMARY)) {
                return true;
            } else {
                return super.overrideStackedOnOther(stack, slot, clickType, player);
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return super.isFoil(itemStack) || itemStack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE,ItemStack.EMPTY) != ItemStack.EMPTY;
    }

    private static void playSlotEmptySound(Entity entity, ItemStack stack) {
        entity.playSound(SoundEvents.STONE_PLACE, 1.0F, 1.0F);
        entity.playSound(SoundEvents.ANCIENT_DEBRIS_BREAK, 1.0F, 1.0F);
        entity.playSound(SoundEvents.ENCHANTMENT_TABLE_USE);
    }

    private static void playSlotingSound(Entity entity, ItemStack stack) {
        entity.playSound(SoundEvents.ANCIENT_DEBRIS_BREAK, 1.0F, 1.0F);
        entity.playSound(SoundEvents.SCULK_BLOCK_PLACE, 1.0F, 1.0F);
        entity.playSound(SoundEvents.ENCHANTMENT_TABLE_USE);
    }

    private void onContentChanged(Player user) {
        AbstractContainerMenu screenHandler = user.containerMenu;
        screenHandler.slotsChanged(user.getInventory());

    }

    public static String getRune(ItemStack stack) {
        return stack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE,ItemStack.EMPTY).getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE_DATA, "empty");
    }

    public static boolean hasRune(String rune, ItemStack stack) {
        return getRune(stack).equals(rune);
    }

    public boolean shouldITakeAndShoveThisItemInMyItemSlot(ItemStack stack) {
        return stack.has(DaggerlanceDataComponents.DAGGERLANCE_RUNE_DATA);
    }
}