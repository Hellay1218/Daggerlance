package net.hellay.daggerlance;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.hellay.daggerlance.datagen.*;
import net.hellay.daggerlance.init.DaggerlanceTrimMaterials;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class DaggerlanceDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DaggerlanceBlockTagProvider::new);
		pack.addProvider(DaggerlanceItemTagProvider::new);
		pack.addProvider(DaggerlanceLootTableProvider::new);
		pack.addProvider(DaggerlanceModelProvider::new);
		pack.addProvider(DaggerlanceRecipeProvider::new);
		pack.addProvider(DaggerlanceRegistriesProvider::new);
	}
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, DaggerlanceTrimMaterials::bootstrap);
		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
	}
}
