package net.errorz.memories.trim;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.item.MemoryItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> MAGMA = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(AntiqueMemories.MOD_ID, "magma"));
    public static final RegistryKey<ArmorTrimMaterial> SHINE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(AntiqueMemories.MOD_ID, "shine"));
    public static final RegistryKey<ArmorTrimMaterial> SCULK = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(AntiqueMemories.MOD_ID, "sculk"));
    public static final RegistryKey<ArmorTrimMaterial> OBSIDIAN = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(AntiqueMemories.MOD_ID, "cooled_molt"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, MAGMA, Registries.ITEM.getEntry(Items.MAGMA_CREAM), Style.EMPTY.withColor(TextColor.parse("#09505f").getOrThrow()), 0.8f);
        register(registerable, OBSIDIAN, Registries.ITEM.getEntry(Items.OBSIDIAN), Style.EMPTY.withColor(TextColor.parse("#486470").getOrThrow()), 0.8f);
        register(registerable, SCULK, Registries.ITEM.getEntry(Items.ECHO_SHARD), Style.EMPTY.withColor(TextColor.parse("#362975").getOrThrow()), 0.8f);
        register(registerable, SHINE, Registries.ITEM.getEntry(MemoryItems.SHINE_INGOT), Style.EMPTY.withColor(TextColor.parse("#FFA3F3").getOrThrow()), 0.8f);
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimMaterialKey, RegistryEntry<Item> item, Style style, float itemModelIndex) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimMaterialKey.getValue().getPath(), item, itemModelIndex, Map.of(), Text.translatable(Util.createTranslationKey("trim_material", armorTrimMaterialKey.getValue())).fillStyle(style));

        registerable.register(armorTrimMaterialKey, trimMaterial);
    }
}
