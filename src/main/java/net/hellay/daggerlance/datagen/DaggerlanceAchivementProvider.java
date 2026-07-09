package net.hellay.daggerlance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DaggerlanceAchivementProvider extends FabricAdvancementProvider {

    public DaggerlanceAchivementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        HolderLookup<EntityType<?>> entityHolderLookup = provider.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderLookup<Item> itemHolderLookup = provider.lookupOrThrow(Registries.ITEM);


        AdvancementHolder DAGGERLANCE = Advancement.Builder.advancement()
                .display(
                        DaggerlanceItems.DAGGERLANCE,
                        Component.literal("Daggerlance"),
                        Component.translatable("advancements.task.daggerlance.backstabbed"),
                        Daggerlance.id("block/lancium_bricks"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("daggerlance", InventoryChangeTrigger.TriggerInstance.hasItems(DaggerlanceItems.DAGGERLANCE))
                .save(consumer, "daggerlance/root");
        AdvancementHolder LANCIUM = Advancement.Builder.advancement()
                .parent(DAGGERLANCE)
                .display(
                        DaggerlanceItems.LANCIUM_INGOT,
                        Component.literal("Lancium"),
                        Component.translatable("advancements.task.daggerlance.lancium"),
                        Daggerlance.id("block/lancium_bricks"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("daggerlance", InventoryChangeTrigger.TriggerInstance.hasItems(DaggerlanceItems.LANCIUM_INGOT))
                .save(consumer, "daggerlance/lancium");


        Advancement.Builder.advancement()
                .parent(LANCIUM)
                .display(
                        DaggerlanceItems.DAGGERLANCE,
                        Component.literal("Backstabbed"),
                        Component.translatable("advancements.challenge.daggerlance.kill_wither_skeleton"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(50))
                .addCriterion(
                        "killed_wither_skeleton_with_daggerlance",
                        KilledTrigger.TriggerInstance.playerKilledEntity(
                                EntityPredicate.Builder.entity().of(entityHolderLookup, EntityType.WITHER_SKELETON),
                                DamageSourcePredicate.Builder.damageType().source(EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(itemHolderLookup,DaggerlanceItems.DAGGERLANCE))))
                        )
                )
                .save(consumer, "daggerlance/backstabbed");

        Advancement.Builder.advancement()
                .parent(LANCIUM)
                .display(
                        DaggerlanceItems.BLANK_RUNE,
                        Component.literal("Fusion"),
                        Component.translatable("advancements.task.daggerlance.rune"),
                        Identifier.withDefaultNamespace("block/lancium_bricks"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("rune_fusion", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(itemHolderLookup,Daggerlance.RUNE)))
                .save(consumer, "daggerlance/fusion");
    }
}
