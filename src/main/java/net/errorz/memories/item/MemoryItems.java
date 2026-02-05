package net.errorz.memories.item;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.block.MemoryBlocks;
import net.errorz.memories.entity.ModEntities;
import net.errorz.memories.item.brush.*;
import net.errorz.memories.item.custom.MoltingBlade;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MemoryItems {
    public static final Item SHINE_INGOT = registerItem("shine_ingot", new Item(new Item.Settings()));
    public static final Item MOLT_INGOT = registerItem("molting_ingot", new Item(new Item.Settings().fireproof()));
    public static final Item SOUL_PEPPER = registerItem("soul_pepper", new AliasedBlockItem(MemoryBlocks.SOUL_GENUS, new Item.Settings().food(ModFoodComponents.SOUL_PEPPER)));
    public static final Item SOUL_BEET = registerItem("soul_beet", new Item(new Item.Settings().food(ModFoodComponents.SOUL_PEPPER)));
    public static final Item SOUL_BEET_SEEDS = registerItem("soul_beet_seeds", new AliasedBlockItem(MemoryBlocks.SOUL_BEET, new Item.Settings()));
    public static final Item SHINE_BRUSH = registerItem("shine_brush",  (new MemoryBrushItem((new Item.Settings()).maxDamage(128), 2)));

    public static final Item MOLTING_BLADE = registerItem("molting_blade",
            new MoltingBlade(ModToolMaterials.MOLTING, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.MOLTING, 2, -2.3f))));

    public static final Item BASALT_BADGER_SPAWN_EGG = registerItem("badger_spawn_egg",
            new SpawnEggItem(ModEntities.BASALT_BADGER, 0x89878f, 0xe96748, new Item.Settings()));
    public static final Item MOLT_SPAWN_EGG = registerItem("molt_spawn_egg",
            new SpawnEggItem(ModEntities.MOLT, 0x323942, 0xe96748, new Item.Settings()));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AntiqueMemories.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AntiqueMemories.LOGGER.info("Registering Mod Items for " + AntiqueMemories.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(MemoryItems::addItemsToIngredientsTabGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(MemoryItems::addItemsToCombatTabGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(MemoryItems::addItemsToSpawnEggsTabGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(MemoryItems::addItemsToFoodTabGroup);

    }
    private static void addItemsToIngredientsTabGroup(FabricItemGroupEntries entries) {

        entries.addAfter(Items.GOLD_INGOT, MOLT_INGOT);

    }
    private static void addItemsToCombatTabGroup(FabricItemGroupEntries entries) {

        entries.addAfter(Items.NETHERITE_SWORD, MOLTING_BLADE);

    }
    private static void addItemsToSpawnEggsTabGroup(FabricItemGroupEntries entries) {

        entries.addAfter(Items.FOX_SPAWN_EGG, BASALT_BADGER_SPAWN_EGG);
        entries.addAfter(Items.PIGLIN_BRUTE_SPAWN_EGG, MOLT_SPAWN_EGG);
    }
    private static void addItemsToFoodTabGroup(FabricItemGroupEntries entries) {

        entries.addAfter(Items.CARROT, SOUL_PEPPER);
        entries.addAfter(Items.BEETROOT, SOUL_BEET);
    }
    private static void addItemsToNaturalTabGroup(FabricItemGroupEntries entries) {

        entries.addAfter(Items.BEETROOT_SEEDS, SOUL_BEET_SEEDS);
    }
}