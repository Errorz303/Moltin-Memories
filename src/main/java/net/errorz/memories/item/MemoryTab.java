package net.errorz.memories.item;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.block.MemoryBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MemoryTab {
    public static final ItemGroup MEMORY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AntiqueMemories.MOD_ID, "memory_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(MemoryItems.MOLT_INGOT))
                    .displayName(Text.translatable("itemgroup.memories.memory_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(MemoryItems.MOLTING_BLADE);
                        entries.add(MemoryItems.MOLT_INGOT);
                        entries.add(MemoryItems.SOUL_BEET);
                        entries.add(MemoryItems.SOUL_PEPPER);
                        entries.add(MemoryBlocks.MOLTEN_MOLT);
                    }).build());

    public static void registerItemGroups() {
        AntiqueMemories.LOGGER.info("Registering Item Groups for " + AntiqueMemories.MOD_ID);
    }
}