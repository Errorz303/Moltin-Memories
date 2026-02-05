package net.errorz.memories.util;

import net.errorz.memories.AntiqueMemories;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MemoryTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_MOLTING_TOOL = createTag("incorrect_for_molting_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AntiqueMemories.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MOLTING_INGOT_REPAIR = createTag("molting_ingot_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AntiqueMemories.MOD_ID, name));
        }
    }
}