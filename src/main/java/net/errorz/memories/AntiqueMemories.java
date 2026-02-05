package net.errorz.memories;

import net.errorz.memories.block.MemoryBlocks;
import net.errorz.memories.entity.ModEntities;
import net.errorz.memories.entity.custom.BasaltBadgerEntity;
import net.errorz.memories.entity.custom.MoltingEntity;
import net.errorz.memories.item.MemoryItems;
import net.errorz.memories.item.MemoryTab;
import net.errorz.memories.world.ModLootTableModifiers;
import net.errorz.memories.world.gen.BasaltSpawner;
import net.errorz.memories.world.gen.ModFlowerGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiqueMemories implements ModInitializer {

    public static final String MOD_ID = "memories";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final boolean FARMERS_DELIGHT_INSTALLED = FabricLoader.getInstance().isModLoaded("farmersdelight");

	@Override
	public void onInitialize() {

        MemoryBlocks.registerModBlocks();
        MemoryItems.registerModItems();
        MemoryTab.registerItemGroups();
        ModEntities.registerModEntities();
        ModLootTableModifiers.modifyLootTables();

        FabricDefaultAttributeRegistry.register(ModEntities.BASALT_BADGER, BasaltBadgerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.MOLT, MoltingEntity.createAttributes());

        BasaltSpawner.addSpawns();
        ModFlowerGeneration.generateFlowers();
        LOGGER.info("Hello Fabric world!");
	}
    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}