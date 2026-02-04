package net.errorz.memories.block;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.block.custom.Magmolts.Molt;
import net.errorz.memories.block.custom.Magmolts.MoltenEgg;
import net.errorz.memories.block.custom.Magmolts.MoltingCurtin;
import net.errorz.memories.block.custom.Nether.SoulBeetCrop;
import net.errorz.memories.block.custom.Nether.SoulGenusPlant;
import net.errorz.memories.block.custom.Overworld.AncientStemBody;
import net.errorz.memories.block.custom.Overworld.AncientStemHead;
import net.errorz.memories.block.custom.Overworld.SalviaFlower;
import net.errorz.memories.block.custom.Overworld.SnifferFlowers;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.AbstractBlock.Settings.copy;
import static net.minecraft.block.AbstractBlock.Settings.create;

public class MemoryBlocks {
    public static final Block MOLTEN_EGG = registerBlock("molten_egg",
            new MoltenEgg(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.BASALT).luminance(blockstate->5)));
    public static final Block MOLTEN_MOLT = registerBlock("molten_molt",
            new Molt(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK).strength(0.2f).mapColor(MapColor.BRIGHT_RED).noCollision().luminance(blockstate->5)));
    public static final Block COOLED_MOLT = registerBlock("cooled_molt",
            new Block(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).strength(4f).requiresTool().sounds(BlockSoundGroup.HONEY).luminance(blockstate->5)));
    public static final Block MOLTING_CURTAIN = registerBlock("molting_curtain", new MoltingCurtin(create().strength(2.5f, 2.5f).sounds(BlockSoundGroup.LANTERN).nonOpaque()));
    public static final Block ANCIENT_SALVIA = registerBlock("ancient_salvia",
            new SalviaFlower(AbstractBlock.Settings.create().luminance(blockstate->3).breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));
    public static final Block SOUL_GENUS = registerBlockWithoutBlockItem("soul_genus",
            new SoulGenusPlant(AbstractBlock.Settings.create().luminance(blockstate->5).breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));
    public static final Block SOUL_BEET = registerBlockWithoutBlockItem("soul_beet_b",
            new SoulBeetCrop(AbstractBlock.Settings.create().luminance(blockstate->5).breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));
    public static final Block ANTIQUE_ROSES = registerBlock("antique_roses",
            new SnifferFlowers(AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));
    public static final Block ANCIENT_STEM_HEAD = registerBlockWithoutBlockItem("ancient_stem_head",
            new AncientStemHead(AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));
    public static final Block ANCIENT_STEM_BODY = registerBlockWithoutBlockItem("ancient_stem_body",
            new AncientStemBody(AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.FLOWERING_AZALEA).noCollision()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AntiqueMemories.MOD_ID, name), block);
    }
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(AntiqueMemories.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(AntiqueMemories.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        AntiqueMemories.LOGGER.info("Registering Mod Blocks for " + AntiqueMemories.MOD_ID);

    }
}