package net.errorz.memories.world;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.block.MemoryBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;


public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DAHLIA_KEY = registerKey("dahlia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DESERT_ROSE_KEY = registerKey("desert_rose");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SOUL_PEPPER_KEY = registerKey("soul_genus");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        register(context, DAHLIA_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(MemoryBlocks.ANCIENT_SALVIA)))));
        register(context, DESERT_ROSE_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(MemoryBlocks.ANTIQUE_ROSES)))));
        register(context, SOUL_PEPPER_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(MemoryBlocks.SOUL_GENUS)))));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(AntiqueMemories.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}